package com.lucien.seckill.service.impl;

import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.GoodsDO;
import com.lucien.seckill.entity.po.Goods;
import com.lucien.seckill.entity.vo.GoodsVO;
import com.lucien.seckill.exception.NotModifiableException;
import com.lucien.seckill.mapper.GoodsMapper;
import com.lucien.seckill.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucien.seckill.service.StockClientService;
import com.lucien.seckill.service.TimeClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucien
 * @since 2020-07-24
 */

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    final private GeneralConvertor convertor;
    final private TimeClientService timeClientService;
    final private StockClientService stockClientService;

    @Transactional
    @CachePut(value = "seckill-goods", key = "#goodsDo.goodsId")
    @Override
    public GoodsVO updateGoods(GoodsDO goodsDo) {
        /*
        开始秒杀前五分钟禁止修改
         */
        LocalDateTime now = timeClientService.getTime();
        LocalDateTime startTime = queryGoods(goodsDo.getGoodsId()).getStartTime().minusMinutes(5);
        if (now.compareTo(startTime) >= 0){
            throw new NotModifiableException();
        }
        Goods goods = convertor.convertor(goodsDo, Goods.class);
        updateById(goods);
        // TODO: 2020/7/30 redis
        GoodsVO goodsVO = convertor.convertor(goods, GoodsVO.class);
        log.info("更新Goods，goodsId : {}", goods.getGoodsId());
        return goodsVO;
    }

    @Transactional
    @CacheEvict(value = "seckill-goods", key = "#goodsId")
    @Override
    public Boolean removeGoods(Integer goodsId) {
        /*
        开始秒杀前五分钟禁止修改
         */
        LocalDateTime now = timeClientService.getTime();
        LocalDateTime startTime = queryGoods(goodsId).getStartTime().minusMinutes(5);
        if (now.compareTo(startTime) >= 0){
            throw new NotModifiableException();
        }
        log.info("删除Goods，goodsId为: {}", goodsId);
        return removeById(goodsId);
    }

    @Cacheable(value = "seckill-goods", key = "#goodsId")
    @Override
    public GoodsVO queryGoods(Integer goodsId) {
        log.info("查找Goods，goodsId为: {}", goodsId);
        Goods goods = getById(goodsId);
        GoodsVO goodsVO = this.convertor.convertor(goods, GoodsVO.class);
        Integer stockNum = stockClientService.queryStockByCache(goodsId);
        goodsVO.setStockNum(stockNum);
        return goodsVO;
    }


    @Override
    public GoodsVO addGoods(GoodsDO goodsDo) {
        Goods goods = convertor.convertor(goodsDo, Goods.class);
        save(goods);
        log.info("添加Goods，goodsId为: {}", goods.getGoodsId());
        return convertor.convertor(goods, GoodsVO.class);
    }

}

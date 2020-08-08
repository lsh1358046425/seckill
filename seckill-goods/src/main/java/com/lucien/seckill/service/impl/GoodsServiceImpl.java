package com.lucien.seckill.service.impl;

import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.GoodsDO;
import com.lucien.seckill.entity.dto.StockDTO;
import com.lucien.seckill.entity.po.Goods;
import com.lucien.seckill.entity.vo.StockVO;
import com.lucien.seckill.exception.NotGoodsException;
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
import org.springframework.data.redis.core.RedisTemplate;
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
    final private RedisTemplate<Object, Object> redisTemplate;

    @Transactional
    @CachePut(value = "seckill-goods", key = "#goodsDo.goodsId")
    @Override
    public GoodsDO updateGoods(GoodsDO goodsDo) {
        /*
        开始秒杀前五分钟禁止修改
         */
        LocalDateTime now = timeClientService.getTime();
        LocalDateTime startTime = queryGoods(goodsDo.getGoodsId()).getStartTime().minusMinutes(5);
        if (now.compareTo(startTime) >= 0){
            throw new NotModifiableException();
        }
        Goods goods = convertor.convertor(goodsDo, Goods.class);
        redisTemplate.delete("seckill-stock::" + goods.getGoodsId());
        updateById(goods);
        GoodsDO goodsDO = convertor.convertor(goods, GoodsDO.class);
        log.info("更新Goods，goodsId : {}", goods.getGoodsId());
        return goodsDO;
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
        redisTemplate.delete("seckill-stock::" + goodsId);
        log.info("删除Goods，goodsId为: {}", goodsId);
        return removeById(goodsId);
    }

    @Cacheable(value = "seckill-goods", key = "#goodsId")
    @Override
    public GoodsDO queryGoods(Integer goodsId) {
        log.info("查找Goods，goodsId为: {}", goodsId);
        Goods goods = getById(goodsId);
        if (null == goods) {
            throw new NotGoodsException();
        }
        return convertor.convertor(goods, GoodsDO.class);
    }

    @Override
    // TODO: 2020/8/7 全局事务
    public GoodsDO addGoods(GoodsDO goodsDo) {
        Goods goods = convertor.convertor(goodsDo, Goods.class);
        save(goods);
        StockDTO stockDTO = convertor.convertor(goods, StockDTO.class);
        stockDTO.setStockNum(goodsDo.getStockNum());
        StockVO stockVO = stockClientService.addStock(stockDTO);
        log.info("添加Goods，goodsId为: {}", goods.getGoodsId());
        return convertor.convertor(goods, GoodsDO.class).setStockNum(stockVO.getStockNum());
    }

}

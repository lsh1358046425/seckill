package com.lucien.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.StockDO;
import com.lucien.seckill.entity.po.Stock;
import com.lucien.seckill.mapper.StockMapper;
import com.lucien.seckill.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucien
 * @since 2020-07-24
 */

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    final private RedisTemplate<String, Object> redisTemplate;
    final private GeneralConvertor convertor;
    final private StockMapper stockMapper;

    @Override
    public Integer queryStockByCache(Integer goodsId){
        String key = "seckill-stock::" + goodsId;
        Object stockNum = redisTemplate.opsForValue().get(key);

        if (null == stockNum){
            QueryWrapper<Stock> wrapper = new QueryWrapper<>();
            wrapper.eq("goods_id", goodsId);
            Stock stock = getOne(wrapper);
            redisTemplate.watch(key);
            redisTemplate.multi();
            redisTemplate.opsForValue().set(key,stock.getStockNum(), Duration.ZERO);
            List<Object> res = redisTemplate.exec();
            if (res.size() == 0){
                log.info("商品库存已恢复，商品ID : {}", goodsId);
            }else {
                log.info("恢复商品库存成功，商品ID : {}", goodsId);
            }
            stockNum = redisTemplate.opsForValue().get(key);
        }

        return (Integer) stockNum;
    }

    @Transactional
    @Override
    public StockDO addStock(StockDO stockDO) {
        Stock stock = convertor.convertor(stockDO, Stock.class);
        save(stock);
        String key = "seckill-stock::" + stock.getGoodsId();
        redisTemplate.opsForValue().set(key, stock.getStockNum(), Duration.ZERO);
        log.info("添加库存成功，商品ID : {}", stock.getGoodsId());
        return convertor.convertor(stock, StockDO.class);
    }

    @Transactional
    @Override
    public Integer decrStock(Integer goodsId) {
        Integer count = stockMapper.decrStockByGoodsId(goodsId);
        if (count > 0){
            log.info("同步扣库存成功，商品ID : {}", goodsId);
        }
        return count;
    }

}

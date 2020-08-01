package com.lucien.seckill.service.impl;

import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.StockDO;
import com.lucien.seckill.entity.po.Stock;
import com.lucien.seckill.entity.vo.StockVO;
import com.lucien.seckill.mapper.StockMapper;
import com.lucien.seckill.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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

    @Override
    public Integer queryStockByCache(Integer GoodsId){
        String key = "seckill-stock::" + GoodsId;
        Object stockNum = redisTemplate.opsForValue().get(key);

        if (null == stockNum){
            Stock stock = getById(GoodsId);
            redisTemplate.watch(key);
            redisTemplate.multi();
            redisTemplate.opsForValue().set(key,stock.getStockNum());
            List<Object> res = redisTemplate.exec();
            if (res.size() == 0){
                log.info("商品库存已恢复，商品ID : {}", GoodsId);
            }else {
                log.info("恢复商品库存成功，商品ID : {}", GoodsId);
            }
            stockNum = redisTemplate.opsForValue().get(key);
        }

        return (Integer) stockNum;
    }

    public StockVO addStock(StockDO stockDO) {
        Stock stock = convertor.convertor(stockDO, Stock.class);
        save(stock);
        return convertor.convertor(stock, StockVO.class);
    }

}

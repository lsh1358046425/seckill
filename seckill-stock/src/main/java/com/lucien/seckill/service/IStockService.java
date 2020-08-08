package com.lucien.seckill.service;

import com.lucien.seckill.entity.domain.StockDO;
import com.lucien.seckill.entity.po.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucien
 * @since 2020-07-24
 */
public interface IStockService extends IService<Stock> {

    Integer queryStockByCache(Integer goodsId);

    StockDO addStock(StockDO stockDO);

    Integer decrStock(Integer goodsId);
}

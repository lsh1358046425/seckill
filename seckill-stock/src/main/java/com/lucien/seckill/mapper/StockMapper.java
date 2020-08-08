package com.lucien.seckill.mapper;

import com.lucien.seckill.entity.po.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucien
 * @since 2020-07-24
 */
public interface StockMapper extends BaseMapper<Stock> {

     Integer decrStockByGoodsId(Integer goodsId);

}

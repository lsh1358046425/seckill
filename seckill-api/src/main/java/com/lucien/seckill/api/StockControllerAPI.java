package com.lucien.seckill.api;

import com.lucien.seckill.entity.dto.StockDTO;
import com.lucien.seckill.entity.vo.StockVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/3 16:42
 */

@Api(value = "Stock Api",tags = "Stock Api")
public interface StockControllerAPI {

    @ApiOperation(value = "获取库存通过Redis缓存")
    Integer queryStockByCache(@PathVariable Integer goodsId);

    @ApiOperation(value = "添加缓存")
    StockVO addStock(StockDTO stockDTO);

}

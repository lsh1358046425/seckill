package com.lucien.seckill.api;

import com.lucien.seckill.entity.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/25 18:13
 */

@Api(value = "Goods Api",tags = "Goods Api")
public interface GoodsController {

    @ApiOperation(value = "获取商品详情")
    ResponseVO queryGoods(@PathVariable Integer goodsId);
}

package com.lucien.seckill.api;

import com.lucien.seckill.entity.dto.GoodsDTO;
import com.lucien.seckill.entity.po.Goods;
import com.lucien.seckill.entity.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/25 18:13
 */

@Api(value = "Goods Api",tags = "Goods Api")
public interface GoodsControllerAPI {

    @ApiOperation(value = "获取商品详情")
    ResponseVO queryGoods(@PathVariable Integer goodsId);

    @ApiOperation(value = "添加商品")
    ResponseVO addGoods(@RequestBody GoodsDTO goodsDTO);

    @ApiOperation(value = "查询商品")
    Goods getGoodsById(@PathVariable Integer goodsId);
}

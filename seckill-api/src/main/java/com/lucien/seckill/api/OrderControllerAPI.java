package com.lucien.seckill.api;

import com.lucien.seckill.entity.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/3 16:43
 */

@Api(value = "Order Api",tags = "Order Api")
public interface OrderControllerAPI {

    @ApiOperation(value = "创建订单")
    ResponseVO createOrder(@PathVariable Integer goodsId);

}

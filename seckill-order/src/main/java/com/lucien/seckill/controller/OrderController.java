package com.lucien.seckill.controller;

import com.lucien.seckill.api.OrderControllerAPI;
import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.OrderDO;
import com.lucien.seckill.entity.po.Goods;
import com.lucien.seckill.entity.vo.OrderVO;
import com.lucien.seckill.entity.vo.ResponseVO;
import com.lucien.seckill.service.GoodsClientService;
import com.lucien.seckill.service.impl.OrderServiceImpl;
import com.lucien.seckill.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/7 11:38
 */

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController implements OrderControllerAPI {

    final private OrderServiceImpl orderService;
    final private GeneralConvertor convertor;
    final private GoodsClientService goodsClientService;

    @PostMapping("/createOrder/{goodsId}")
    public ResponseVO createOrder(@PathVariable Integer goodsId){
        OrderDO orderDO = orderService.createOrder(goodsId);
        OrderVO orderVO = convertor.convertor(orderDO, OrderVO.class);
        Goods goods = goodsClientService.getGoodsById(goodsId);
        orderVO.setGoods(goods);
        return ResponseUtils.ok(orderVO);
    }

}

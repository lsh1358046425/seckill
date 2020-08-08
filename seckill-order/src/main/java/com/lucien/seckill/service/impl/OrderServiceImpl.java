package com.lucien.seckill.service.impl;

import com.google.common.collect.Lists;
import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.OrderDO;
import com.lucien.seckill.entity.enums.OrderStatusEnum;
import com.lucien.seckill.entity.po.Order;
import com.lucien.seckill.mapper.OrderMapper;
import com.lucien.seckill.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    final private RabbitTemplate rabbitTemplate;
    final private RedisTemplate<Object, Object> redisTemplate;
    final private GeneralConvertor convertor;
    final static private DefaultRedisScript<String> redisScript;

    static {
        redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("script.lua"));
        redisScript.setResultType(String.class);
    }

    @Transactional
    public OrderDO createOrder(Integer goodsId){
        Order order = new Order();
        order.setGoodsId(goodsId).setOrderStatus(OrderStatusEnum.NOT_PAY.getCode());
        save(order);
        redisTemplate.execute(redisScript, Lists.newArrayList("seckill-stock::" + goodsId));
        rabbitTemplate.convertAndSend("exchange.direct.seckill.stock", "stock", goodsId);
        log.info("下单成功，商品ID : {}", goodsId);
        return convertor.convertor(order, OrderDO.class);
    }

}

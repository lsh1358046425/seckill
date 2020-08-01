package com.lucien.seckill.service.impl;

import com.lucien.seckill.entity.po.Order;
import com.lucien.seckill.mapper.OrderMapper;
import com.lucien.seckill.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucien
 * @since 2020-07-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}

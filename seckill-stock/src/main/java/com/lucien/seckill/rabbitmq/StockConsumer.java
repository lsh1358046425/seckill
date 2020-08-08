package com.lucien.seckill.rabbitmq;

import com.lucien.seckill.service.impl.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/5 23:46
 */

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockConsumer {

    final private StockServiceImpl stockService;

    @RabbitHandler
    @RabbitListener(queues = "queue.direct.seckill.stock")
    public void consumerStock(Integer goodsId,Message message, Channel channel) throws IOException {
        stockService.decrStock(goodsId);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

}

package com.lucien.seckill.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/6 17:30
 */

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitConfiguration {

    private static final String DIRECT_QUEUE = "queue.direct.seckill.stock";
    private static final String DIRECT_EXCHANGE = "exchange.direct.seckill.stock";
    private static final String ROUTING_KEY = "stock";
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue stockDirectQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }

    @Bean
    public DirectExchange stockDirectExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(stockDirectQueue()).
                to(stockDirectExchange()).with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息丢失 : exchange({}),route({}),replyCode({}),replyText({}),message : {}",exchange,routingKey,replyCode,replyText,message);
        });

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                log.info("消息发送成功 : correlationData({}),ack({}),cause({})",correlationData, ack, cause);
            }else{
                log.info("消息发送失败 : correlationData({}),ack({}),cause({})",correlationData, ack, cause);
            }
        });
        return rabbitTemplate;
    }
}

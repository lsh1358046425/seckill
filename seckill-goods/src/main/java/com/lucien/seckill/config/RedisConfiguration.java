package com.lucien.seckill.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/19 23:02
 */

@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
        /*
        value使用fastJsonRedisSerializer
         */
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        /*
        key使用StringRedisSerializer
         */
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        /*
        生成一个默认配置，通过config对象即可对缓存进行自定义配置
         */
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        /*
        设置缓存的默认过期时间，也是使用Duration设置
         */
        config = config.entryTtl(Duration.ofMinutes(1));
        /*
        设置一个初始化的缓存空间set集合
         */
        Set<String> cacheNames =  new HashSet<>();
        cacheNames.add("seckill-goods");
        /*
        对每个缓存空间应用不同的配置
         */
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("seckill-goods", config.entryTtl(Duration.ZERO));
        /*
        使用自定义的缓存配置初始化一个cacheManager
        注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
         */
        return RedisCacheManager.builder(factory)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
    }
}

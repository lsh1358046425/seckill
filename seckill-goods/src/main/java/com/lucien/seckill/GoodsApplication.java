package com.lucien.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/18 22:18
 */

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@MapperScan(basePackages = "com.lucien.seckill.mapper")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}

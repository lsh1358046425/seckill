package com.lucien.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/21 21:36
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.lucien.seckill.mapper")
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}

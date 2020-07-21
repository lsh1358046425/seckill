package com.lucien.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/21 16:24
 */

@SpringBootApplication
@EnableDiscoveryClient
public class TimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeApplication.class, args);
    }
}

package com.lucien.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/21 16:26
 */

@RestController
@Slf4j
public class TimeController {
    @GetMapping("/getTime")
    public Date getTime(){
        return new Date();
    }
}

package com.lucien.seckill.controller;

import com.lucien.seckill.api.TimeControllerApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/21 16:26
 */

@RestController
@RequestMapping("/time")
public class TimeController implements TimeControllerApi {

    @GetMapping("/getTime")
    public LocalDateTime getTime(){
        return LocalDateTime.now();
    }
}

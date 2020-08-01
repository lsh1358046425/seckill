package com.lucien.seckill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/30 19:49
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TimeClientService {

    final private RestTemplate restTemplate;

    @Value("${service-url.time-service}")
    private String uri;

    public LocalDateTime getTime(){
        return restTemplate.getForObject(uri + "/time/getTime", LocalDateTime.class);
    }

}

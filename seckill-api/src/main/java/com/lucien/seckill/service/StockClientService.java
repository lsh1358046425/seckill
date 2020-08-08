package com.lucien.seckill.service;

import com.lucien.seckill.entity.dto.StockDTO;
import com.lucien.seckill.entity.vo.StockVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/27 17:25
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockClientService {

    final private RestTemplate restTemplate;

    @Value("${service-url.stock-service}")
    private String uri;

    public Integer queryStockByCache(Integer goodsId){
        return restTemplate.getForObject(uri + "/stock/queryStockByCache/{goodsId}", Integer.class, goodsId);
    }

    public StockVO addStock(StockDTO stockDTO){
        return restTemplate.postForObject(uri + "/stock/addStock", stockDTO, StockVO.class);
    }

}

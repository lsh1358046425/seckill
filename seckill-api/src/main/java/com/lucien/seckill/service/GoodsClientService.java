package com.lucien.seckill.service;

import com.lucien.seckill.entity.po.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/7 20:22
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsClientService {

    final private RestTemplate restTemplate;

    @Value("${service-url.goods-service}")
    private String uri;

    public Goods getGoodsById(Integer goodsId) {
        return restTemplate.getForObject(uri + "/goods//getGoodsById/{goodsId}", Goods.class, goodsId);
    }

}

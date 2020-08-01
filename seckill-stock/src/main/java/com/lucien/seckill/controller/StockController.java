package com.lucien.seckill.controller;

import com.lucien.seckill.service.impl.StockServiceImpl;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/27 22:18
 */


@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockController {

    final private StockServiceImpl stockService;

    @GetMapping("/queryStockByCache/{goodsId}")
    public Integer queryStockByCache(@PathVariable Integer goodsId){
        return stockService.queryStockByCache(goodsId);
    }

}

package com.lucien.seckill.controller;

import com.lucien.seckill.api.StockControllerAPI;
import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.StockDO;
import com.lucien.seckill.entity.dto.StockDTO;
import com.lucien.seckill.entity.vo.StockVO;
import com.lucien.seckill.service.impl.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/27 22:18
 */


@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockController implements StockControllerAPI {

    final private StockServiceImpl stockService;
    final private GeneralConvertor convertor;

    @GetMapping("/queryStockByCache/{goodsId}")
    public Integer queryStockByCache(@PathVariable Integer goodsId){
        return stockService.queryStockByCache(goodsId);
    }

    @PostMapping("/addStock")
    public StockVO addStock(@RequestBody StockDTO stockDTO) {
        StockDO stockDO = convertor.convertor(stockDTO, StockDO.class);
        return convertor.convertor(stockService.addStock(stockDO), StockVO.class);
    }


}

package com.lucien.seckill.controller;

import com.lucien.seckill.entity.GeneralConvertor;
import com.lucien.seckill.entity.domain.GoodsDO;
import com.lucien.seckill.entity.dto.GoodsDTO;
import com.lucien.seckill.entity.vo.GoodsVO;
import com.lucien.seckill.entity.vo.ResponseVO;
import com.lucien.seckill.service.StockClientService;
import com.lucien.seckill.service.impl.GoodsServiceImpl;
import com.lucien.seckill.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/25 18:12
 */

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsController {

    final private GoodsServiceImpl goodsService;
    final private GeneralConvertor convertor;


    @GetMapping("/queryGoods/{goodsId}")
    public ResponseVO queryGoods(@PathVariable Integer goodsId) {
        GoodsVO goodsVo = goodsService.queryGoods(goodsId);
        return ResponseUtils.ok(goodsVo);
    }

    @PostMapping("/addGoods")
    public ResponseVO addGoods(@RequestBody GoodsDTO goodsDTO) {
        GoodsDO goodsDo = convertor.convertor(goodsDTO, GoodsDO.class);
        GoodsVO goodsVo = convertor.convertor(goodsService.addGoods(goodsDo), GoodsVO.class);
        return ResponseUtils.ok(goodsVo);
    }
}

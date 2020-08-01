package com.lucien.seckill.service;

import com.lucien.seckill.entity.domain.GoodsDO;
import com.lucien.seckill.entity.po.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucien.seckill.entity.vo.GoodsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucien
 * @since 2020-07-24
 */
public interface IGoodsService extends IService<Goods> {
    GoodsVO updateGoods(GoodsDO goodsDo);

    Boolean removeGoods(Integer goodsId);

    GoodsVO queryGoods(Integer goodsId);

    GoodsVO addGoods(GoodsDO goodsDo);
}

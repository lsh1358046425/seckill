package com.lucien.seckill.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lucien.seckill.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/30 22:50
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockDO对象", description="")
public class StockDO implements Serializable {

    private Integer goodsId;

    private Integer stockNum;

}
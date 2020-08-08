package com.lucien.seckill.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/3 17:11
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockDTO对象", description="")
public class StockDTO implements Serializable {

    private Integer stockId;

    private Integer goodsId;

    private Integer stockNum;

}

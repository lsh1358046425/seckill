package com.lucien.seckill.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/30 23:23
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockVO对象", description="")
public class StockVO implements Serializable {

    private Integer stockId;

    private Integer goodsId;

    private Integer stockNum;

}

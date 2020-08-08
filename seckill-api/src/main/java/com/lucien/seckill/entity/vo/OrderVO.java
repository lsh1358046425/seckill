package com.lucien.seckill.entity.vo;

import com.lucien.seckill.entity.po.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lucien
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderVO对象", description="")
public class OrderVO implements Serializable {

    private Integer orderId;

    private Goods goods;

    @ApiModelProperty(value = "-2:超时 -1:取消 0:未支付 1:已支付")
    private Integer orderStatus;

}

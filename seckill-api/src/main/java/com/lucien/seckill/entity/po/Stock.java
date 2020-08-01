package com.lucien.seckill.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lucien.seckill.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lucien
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Stock对象", description="")
public class Stock extends BaseEntity implements Serializable {

    @TableId(value = "stock_id", type = IdType.AUTO)
    private Integer stockId;

    private Integer goodsId;

    private Integer stockNum;
    
}

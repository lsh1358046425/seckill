package com.lucien.seckill.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/7 17:55
 */

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    TIMEOUT(-2, "超时"),
    CANCEL(-1, "已取消"),
    NOT_PAY(0, "未支付"),
    PAID(1, "已支付");

    private Integer code;

    private String message;
}

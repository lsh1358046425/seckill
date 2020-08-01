package com.lucien.seckill.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/26 14:38
 */

@Getter
@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(1, "未知错误"),
    COMMON_ERROR(2, "禁止修改");

    private Integer code;

    private String message;
}

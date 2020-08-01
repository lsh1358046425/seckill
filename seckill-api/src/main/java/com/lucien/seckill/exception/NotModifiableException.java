package com.lucien.seckill.exception;

import com.lucien.seckill.entity.enums.ResponseEnum;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/30 20:34
 */
public class NotModifiableException extends RuntimeException {

    public ResponseEnum getResponseEnum() {
        return ResponseEnum.COMMON_ERROR;
    }

}

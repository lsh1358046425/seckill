package com.lucien.seckill.exception;

import com.lucien.seckill.entity.enums.ResponseEnum;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/8/3 18:06
 */
public class NotGoodsException extends RuntimeException {

    public ResponseEnum getResponseEnum() {
        return ResponseEnum.NOT_GOODS_ERROR;
    }

}

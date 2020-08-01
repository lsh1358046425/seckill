package com.lucien.seckill.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/26 14:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO implements Serializable {
    private Integer code;

    private String massage;

    private Object data;

    private Long count;
}

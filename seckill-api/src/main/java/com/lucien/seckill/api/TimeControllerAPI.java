package com.lucien.seckill.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/24 16:52
 */

@Api(value = "Time Api",tags = "Time Api")
public interface TimeControllerAPI {
    @ApiOperation(value = "获取时间")
    LocalDateTime getTime();
}

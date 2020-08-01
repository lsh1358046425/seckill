package com.lucien.seckill.util;

import com.lucien.seckill.entity.vo.ResponseVO;
import com.lucien.seckill.entity.enums.ResponseEnum;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/26 14:31
 */

public class ResponseUtils {

    /**
     * 带数据和条数的ok
     * @param data
     * @param count
     * @return ResponseBody
     */
    public static ResponseVO ok(Object data, Long count){
        ResponseVO responseVo = new ResponseVO();
        responseVo.setData(data);
        responseVo.setCount(count);
        responseVo.setCode(ResponseEnum.SUCCESS.getCode());
        responseVo.setMassage(ResponseEnum.SUCCESS.getMessage());
        return responseVo;
    }

    /**
     * 带数据的ok
     * @param data
     * @return ResponseBody
     */
    public static ResponseVO ok(Object data){
        ResponseVO responseVo = new ResponseVO();
        responseVo.setData(data);
        responseVo.setCode(ResponseEnum.SUCCESS.getCode());
        responseVo.setMassage(ResponseEnum.SUCCESS.getMessage());
        return responseVo;
    }

    /**
     * 指定错误
     * @param error
     * @return ResponseBody
     */
    public static ResponseVO error(ResponseEnum error){
        ResponseVO responseVo = new ResponseVO();
        responseVo.setCode(error.getCode());
        responseVo.setMassage(error.getMessage());
        return responseVo;
    }

    /**
     * 未知错误
     * @return ResponseBody
     */
    public static ResponseVO error(){
        ResponseVO responseVo = new ResponseVO();
        responseVo.setCode(ResponseEnum.UNKNOWN_ERROR.getCode());
        responseVo.setMassage(ResponseEnum.UNKNOWN_ERROR.getMessage());
        return responseVo;
    }
}

package com.lucien.seckill.handler;

import com.lucien.seckill.entity.vo.ResponseVO;
import com.lucien.seckill.exception.NotModifiableException;
import com.lucien.seckill.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/26 14:58
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO exceptionHandler(HttpServletRequest request, Exception e){
        log.error("发生异常！", e);
        return ResponseUtils.error();
    }

    /**
     * 处理通用异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotModifiableException.class)
    @ResponseBody
    public ResponseVO NotModifiableExceptionHandler(HttpServletRequest request, NotModifiableException e){
        log.error(e.getResponseEnum().getMessage(), e);
        return ResponseUtils.error(e.getResponseEnum());
    }
}

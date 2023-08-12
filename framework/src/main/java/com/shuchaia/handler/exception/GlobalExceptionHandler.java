package com.shuchaia.handler.exception;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 处理自定义异常
 * @Author shuchaia
 * @Date 2023/7/4 17:55
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("出现了异常！{}", e);
        // 响应给前端
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public ResponseResult bindExceptionHandler(BindException e) {
        log.error("出现了异常！{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e) {
        // 打印异常信息
        log.error("出现了异常！{}", e);
        // 响应给前端
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

}

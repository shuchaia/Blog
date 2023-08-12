package com.shuchaia.exception;

import com.shuchaia.enums.AppHttpCodeEnum;
import lombok.Data;

/**
 * @ClassName SystemException
 * @Description 自定义异常类
 * @Author shuchaia
 * @Date 2023/7/4 17:51
 * @Version 1.0
 */
public class SystemException extends RuntimeException{
    private int code;

    private String msg;

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

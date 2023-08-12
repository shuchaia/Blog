package com.shuchaia.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuchaia.enums.AppHttpCodeEnum;

import java.io.Serializable;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/26 20:15
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private T data;
    private String msg;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ResponseResult errorResult(int code, String msg){
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }


    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static ResponseResult okResult(){
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS,AppHttpCodeEnum.SUCCESS.getMsg());
        return result;
    }

    public static ResponseResult okResult(int code, String msg){
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }

    public static ResponseResult okResult(Object data){
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS,AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg) {
        return okResult(enums.getCode(), msg);
    }

    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

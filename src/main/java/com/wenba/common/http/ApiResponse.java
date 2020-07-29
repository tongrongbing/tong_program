package com.wenba.common.http;

import com.wenba.common.enums.APIStatus;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 13:36
 **/
public class ApiResponse<T> {
    private String message;
    private Integer code;
    private T data;

    public ApiResponse(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static ApiResponse ok() {
        return new ApiResponse("ok",200,null);
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse("ok",200,data);
    }

    public static ApiResponse error() {
        return new ApiResponse("error",500,"");
    }

    public static ApiResponse error(String message,Integer code) {
        return new ApiResponse(message,code,"");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}

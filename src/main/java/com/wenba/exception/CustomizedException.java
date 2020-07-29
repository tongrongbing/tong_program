package com.wenba.exception;

import com.wenba.common.enums.APIStatus;

/**
 * @description: 自定义异常
 * @author: tongrongbing
 * @date: 2020-07-10 18:08
 **/
public class CustomizedException extends RuntimeException{

    private String message;

    private Integer code;

    public CustomizedException(String message,Integer code){
        this.message = message;
        this.code = code;
    }

    public CustomizedException(APIStatus status){
        this(status.getMessage(),status.getCode());
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

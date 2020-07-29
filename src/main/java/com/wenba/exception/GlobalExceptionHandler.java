package com.wenba.exception;

import com.alibaba.fastjson.JSON;
import com.wenba.common.enums.APIStatus;
import com.wenba.common.http.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 18:07
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomizedException.class)
    @ResponseBody
    public ApiResponse exceptionHandle(HttpServletRequest request,CustomizedException ex){
        logger.error("出现业务异常信息-->{}",ex.getMessage());
        return ApiResponse.error(ex.getMessage(),ex.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse exceptionHandle(HttpServletRequest request, MethodArgumentNotValidException ex){
        logger.error("出现参数绑定异常信息-->{}",ex.getBindingResult().getFieldErrors());
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        Map<String,Object> errorMap = new HashMap<>();
        for(FieldError fieldError : errorList){
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return ApiResponse.error(JSON.toJSONString(errorMap), APIStatus.BAD_PARAM.getCode());
    }
}

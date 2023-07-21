package com.example.demo.exception;

import com.example.demo.entity.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Object customHandler(CustomException e){
        e.printStackTrace();
        return new ResultCode(e.getMessage(), ResultCode.FAIL);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e){
        e.printStackTrace();
        return new ResultCode("System error", ResultCode.FAIL);
    }
}
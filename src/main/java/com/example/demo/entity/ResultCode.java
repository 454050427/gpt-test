package com.example.demo.entity;

import lombok.Data;

@Data
public class ResultCode {
    //generate message, code, data
    private String message;
    private int code;
    private Object data;

    //generate code value
    public static final int SUCCESS = 200;
    public static final int FAIL = 400;

    //generate constructor
    public ResultCode(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    //generate constructor without data
    public ResultCode(String message, int code) {
        this.message = message;
        this.code = code;
    }
}

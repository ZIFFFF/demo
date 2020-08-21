package com.example.demo.vo;

/**
 * view object 视图返回对象，前端接收的数据对象
 * */


public class ResultVO {

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

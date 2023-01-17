package com.example.demo.response;

public enum ResponseState {
    SUCCESS(200, "成功"),
    FAILED(400, "失败"),
    LOGIN_FAILED(499, "登录失败");

    private int code;
    private String msg;

    ResponseState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

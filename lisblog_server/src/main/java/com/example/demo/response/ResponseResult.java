package com.example.demo.response;


public class ResponseResult {
    private int code;
    private String message;
    private Object data;

    private ResponseResult(int status, String message, Object data) {
        this.code = status;
        this.data = data;
        this.message = message;
    }

    public ResponseResult(ResponseState responseState) {
        this.code = responseState.getCode();
        this.message = responseState.getMsg();
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseState.SUCCESS);
    }

    public static ResponseResult success(Object object) {
        ResponseResult responseResult = new ResponseResult(ResponseState.SUCCESS);
        responseResult.setData(object);
        return responseResult;
    }

    public static ResponseResult error() {
        return error("error");
    }

    public static ResponseResult error(String message) {
        ResponseResult responseResult = new ResponseResult(ResponseState.FAILED);
        responseResult.setMessage(message);
        return responseResult;
    }

    public static ResponseResult error(int code, String msg, Object data) {
        return new ResponseResult(code, msg, data);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}

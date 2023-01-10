package com.example.demo.Model;

import lombok.Data;

@Data
public class HttpResponse {
    private int status;
    private Object data;
    private String message;

    private HttpResponse(int status, String message, Object data) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public static HttpResponse success() {
        return new HttpResponse(200, "ok", null);
    }

    public static HttpResponse success(Object object) {
        return new HttpResponse(200, "ok", object);
    }

    public static HttpResponse error() {
        return error("error");
    }

    public static HttpResponse error(String message) {
        return new HttpResponse(400, message, null);
    }

    public static HttpResponse error(int code, String msg, Object data) {
        return new HttpResponse(code, msg, data);
    }


    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}

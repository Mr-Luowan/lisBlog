package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponse {
    private int status;
    private Object data;
    private String message;

    private HttpResponse(int status, Object data, String message) {
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
        return new HttpResponse(200, null, "ok");
    }

    public static HttpResponse success(Object object) {
        return new HttpResponse(200, object, "ok");
    }

    public static HttpResponse error() {
        return new HttpResponse(400, null, "error");
    }
}

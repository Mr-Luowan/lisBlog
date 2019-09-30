package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponse {
    private int status;
    private Object data;
    private String message;

    public static HttpResponse success() {
        HttpResponse httpResponse = new HttpResponse(200, null, "ok");
        return httpResponse;
    }

    public static HttpResponse success(Object object) {
        HttpResponse httpResponse = new HttpResponse(200, object, "ok");
        return httpResponse;
    }

    public static HttpResponse error() {
        HttpResponse httpResponse = new HttpResponse(400, null, "error");
        return httpResponse;
    }
}

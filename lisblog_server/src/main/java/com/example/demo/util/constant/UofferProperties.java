package com.example.demo.util.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UofferProperties {


    @Value("${ShiroAnonUrl}")
    String ShiroAnonUrl;

    /**
     * 获取免认证接口 url
     */
    public String getShiroAnonUrl() {
        return ShiroAnonUrl;
    }
}

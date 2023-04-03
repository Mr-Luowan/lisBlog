package com.example.demo.util.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UofferProperties {

    @Value("${custom_prop}")
    String customProp;

    @Value("${jwtTokenSecret}")
    private String jwtTokenSecret;

    @Value("${tokenExpiredMs}")
    private long tokenExpiredMs;

    public String getCustomProp() {
        return customProp;
    }

    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public long getTokenExpiredMs() {
        return tokenExpiredMs;
    }
}

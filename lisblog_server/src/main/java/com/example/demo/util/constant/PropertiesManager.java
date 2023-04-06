package com.example.demo.util.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesManager {


    @Value("${file.saveFilePath}")
    public  String saveFilePath;

    @Value("${file.staticAccessPath}")
    public String staticAccessPath;

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

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public String getStaticAccessPath() {
        return staticAccessPath;
    }
}

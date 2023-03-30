package com.example.demo.util.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UofferProperties {

    @Value("${custom_prop}")
    String customProp;

    public String getCustomProp() {
        return customProp;
    }
}

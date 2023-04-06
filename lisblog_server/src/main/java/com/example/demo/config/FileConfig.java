package com.example.demo.config;

import com.example.demo.util.constant.PropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {


    @Autowired
    PropertiesManager propertiesManager;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(propertiesManager.getStaticAccessPath()).addResourceLocations(propertiesManager.getSaveFilePath());
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

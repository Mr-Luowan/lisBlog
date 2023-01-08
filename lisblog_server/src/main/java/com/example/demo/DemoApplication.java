package com.example.demo;

import com.example.demo.util.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public  Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @Bean
    public SnowflakeIdWorker getSnowflakeIdWorker() {
        return new SnowflakeIdWorker(10,10);
    }

}

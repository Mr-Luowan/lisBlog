package com.example.demo.controller;

import com.example.demo.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public ResponseResult hello() {
        return ResponseResult.success("访问成功");
    }
}

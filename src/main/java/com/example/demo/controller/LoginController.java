package com.example.demo.controller;

import com.example.demo.domain.HttpResponse;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public HttpResponse login(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password) {
        if (username.equals("pf") && password.equals("1")) {
            HttpResponse response = new HttpResponse();
            response.setStatus(200);
            response.setData(new User(1, "lpf", "1"));
            return response;
        }
        return null;
    }
    @RequestMapping(value = "/findAll")
    public HttpResponse findAll() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(200);
        httpResponse.setData(userMapper.findAll());
        return httpResponse;
    }
}

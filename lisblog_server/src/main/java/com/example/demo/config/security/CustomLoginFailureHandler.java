package com.example.demo.config.security;

import com.example.demo.response.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponseResult res = ResponseResult.error();
        // 根据异常设置失败信息
        if (exception instanceof LockedException) {
            res.setMessage("账户被锁定");
        } else if (exception instanceof CredentialsExpiredException) {
            res.setMessage("密码过期");
        } else if (exception instanceof AccountExpiredException) {
            res.setMessage("账户过期");
        } else if (exception instanceof DisabledException) {
            res.setMessage("账户被禁用");
        } else if (exception instanceof BadCredentialsException) {
            res.setMessage("用户名或者密码输入错误");
        }

        // 以 JSON 格式写入 response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(res.toString());
        writer.flush();
        System.out.println("认证成功");
    }
}

package com.example.demo.config.security;

import com.example.demo.response.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 构造一个统一返回格式对象
        ResponseResult responseResult = ResponseResult.success("认证成功");
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            // 根据用户信息，使用 JWT 工具类构建 Token
            // ...
            // 存到返回内容中
        }
        // 以 JSON 格式写入 response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(responseResult.toString());
        writer.flush();
        System.out.println("认证成功");
    }
}

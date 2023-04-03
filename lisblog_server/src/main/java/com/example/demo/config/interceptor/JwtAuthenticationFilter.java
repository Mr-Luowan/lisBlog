package com.example.demo.config.interceptor;

import com.example.demo.Model.User;
import com.example.demo.config.security.CustomUserDetailsService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    String token_header = "token";
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomUserDetailsService userService;

    @Override

    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 取出 header 中的 token 进行校验
        String authHeader = httpServletRequest.getHeader(token_header);
        if (authHeader != null && !StringUtil.isEmpty(authHeader)) {
            String username = jwtUtils.getUserNameFromToken(authHeader);
            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 根据 username 查询用户，可以从缓存、数据库中获取
                UserDetails userDetails = userService.loadUserByUsername(username);
                // 校验
                if (!jwtUtils.isTokenExpired(authHeader)) {
                    // 构建 authentication
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    // 设置 details，其中包含地址、session 等
                    authentication.setDetails(new
                            WebAuthenticationDetails(httpServletRequest));
                    // 设置 authentication 到上下文对象中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

}

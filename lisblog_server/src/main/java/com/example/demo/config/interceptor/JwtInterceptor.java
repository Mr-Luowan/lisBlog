package com.example.demo.config.interceptor;

import com.example.demo.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private JwtUtils jwtUtils;

    @Autowired
    public JwtInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * 只处理token信息，对具体业务逻辑不做判断
     * 虽然所有路径都拦截，但是对于未登录进行查看操作时，token不存在，直接放行了
     * 登录状态token存在，检查合法性正确后放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取头信息，用户登录成功后前端访问后台接口在http请求头中添加头信息
        final String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header) && header.startsWith("q")) {
            final String token = header.substring(10);
            Claims claims = jwtUtils.getClaimsFromToken(token);
            CharSequence role = (CharSequence) claims.get("role");
            if (role != null) {
                request.setAttribute("role", claims.get("role"));
            }
        }
        return true;
    }
}

package com.example.demo.shiro;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONUtil;
import com.example.demo.Model.HttpResponse;
import com.example.demo.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;

@Component
public class JwtFilter extends AuthenticatingFilter {
    private static final String TAG = "JwtFilter";
    @Autowired
    JwtUtils jwtUtils;


    @Autowired
    Logger logger;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (jwt.isEmpty()) {
            logger.error(TAG + "  ___" + "createToken  jwt is null !");
            return null;
        }
        logger.error(TAG + "  ___" + "createToken from jwt = " + jwt);
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        logger.error(TAG + "  ___" + "onAccessDenied jwt = " + jwt);
        if (StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
                logger.error(TAG + "  ___" + "onAccessDenied jwt 有效期 = " + claim.getExpiration());
                throw new ExpiredCredentialsException("Token已失效，请重新登录");
            }
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServerResponse = (HttpServletResponse) response;
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        HttpResponse httpResponse = HttpResponse.error(throwable.getMessage());
        String json = JSONUtil.toJsonStr(httpResponse);
        logger.error(TAG + "  ___" + "onLoginFailure json = " + json);
        try {
            httpServerResponse.getWriter().print(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        logger.error(TAG + "  ___" + "preHandle json = " + request.getRemoteAddr());
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}

package com.example.demo.shiro;

import cn.hutool.json.JSONUtil;
import com.example.demo.Model.HttpResponse;
import com.example.demo.util.SpringContextUtil;
import com.example.demo.util.constant.BlogConstant;
import com.example.demo.util.constant.UofferProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    private static final String TAG = "JwtFilter";


//    @Override
//    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String jwt = request.getHeader("Authorization");
//        if (jwt.isEmpty()) {
//            log.error(TAG + "  ___" + "createToken  jwt is null !");
//            return null;
//        }
//        log.error(TAG + "  ___" + "createToken from jwt = " + jwt);
//        return new JwtToken(jwt);
//    }

    /**
     * 在登录的情况下会走此方法，此方法返回true直接访问控制器
     * 如果isAccessAllowed方法返回True，则不会再调用onAccessDenied方法，
     * 如果isAccessAllowed方法返回Flase,则会继续调用onAccessDenied方法。
     * 而onAccessDenied方法里面则是具体执行登陆的地方。
     * 由于我们已经登陆，所以此方法就会返回True(filter放行),所以上面的onPreHandle方法里面的onAccessDenied方法就不会被执行。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        UofferProperties uofferProperties = SpringContextUtil.getBean(UofferProperties.class);
        String[] anonUrls = StringUtils.split(uofferProperties.getShiroAnonUrl(), ",");
        if (anonUrls == null) {
            //如果没有配置 所有的请求都放行
            return true;
        }
        boolean match = false;
        for (String anonUrl : anonUrls) {
            if (pathMatcher.matches(anonUrl, httpServletRequest.getRequestURI())) {
                match = true;
            }
        }
        if (match) {
            log.error(TAG + "  ___" + "匹配通过不做拦截");
            return true;
        }
        log.error(TAG + "  ___" + "匹配不通过");
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(BlogConstant.TOKEN); //得到token
        JwtToken jwtToken = new JwtToken(token); // 解密token
        try {
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request, response).login(jwtToken);
            log.error("调用subject login方法");
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * executeLogin执行结果是false，则将执行sendChallenge方法,建议重写此方法，否则默认将返回空白界面
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        log.debug("Authentication required: sending 401 Authentication challenge response.");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpResponse.getWriter()) {
            HttpResponse failed = HttpResponse.error(401, "失败", "");
            String result = JSONUtil.toJsonStr(failed);
            out.print(result);
        } catch (IOException e) {
            log.error("sendChallenge error：", e);
        }
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(BlogConstant.TOKEN);
        return token != null;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServerResponse = (HttpServletResponse) response;
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        HttpResponse httpResponse = HttpResponse.error(throwable.getMessage());
        String json = JSONUtil.toJsonStr(httpResponse);
        log.error(TAG + "  ___" + "onLoginFailure json = " + json);
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
        log.error(TAG + "  ___" + "preHandle json = " + request.getRemoteAddr());
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}

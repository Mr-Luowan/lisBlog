package com.example.demo.controller;


import com.example.demo.Model.HttpResponse;
import com.example.demo.Model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
import com.example.demo.util.SnowflakeIdWorker;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final String TAG = "UserController   ";

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    Logger logger;

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;


    @PostMapping
    public HttpResponse register(User user) {
        return null;
    }


    @PostMapping("/login")
    public HttpResponse login(User user, HttpServletResponse httpServletResponse) {
        User dbUser = userService.findUserByName(user.getUserName());
        if (dbUser == null) {
            logger.error(TAG + "没找到用户");
            return HttpResponse.error("没找到用户");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            logger.error(TAG + "密码错误");
            return HttpResponse.error("密码错误");
        }
//        String jwt = jwtUtils.generateToken(dbUser.getId());
//        httpServletResponse.setHeader("Authorization", jwt);
//        httpServletResponse.setHeader("Access-control-Expose-Headers", "Authorization");
        Map<String, Object> data = new HashMap<>(10);
        data.put("roles", dbUser.getRoles());
        data.put("id", dbUser.getId());
        data.put("userName", dbUser.getUserName());
        return HttpResponse.success();
    }

    // 验证用户是否登录，等同于方法subject.isAuthenticated() 结果为true时
    @RequiresAuthentication
    @GetMapping("/logout")
    public HttpResponse logout() {
        return HttpResponse.success("退出成功");
    }


    @RequiresAuthentication
    @GetMapping("/{id}")
    public HttpResponse deleteUser(@PathVariable("id") String userId) {
        logger.info(TAG + "需要删除的用户Id   " + userId);
        return HttpResponse.success("删除成功");
    }








}

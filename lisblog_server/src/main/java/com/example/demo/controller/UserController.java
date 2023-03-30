package com.example.demo.controller;


import com.example.demo.response.ResponseResult;
import com.example.demo.Model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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

    /**
     * 初始化管理员账号
     */
    @ApiOperation("初始化管理员账号")
    @PostMapping("/admin_account")
    public ResponseResult initManagerAccount(User user) {
        return userService.initManagerAccount(user);
    }

    @PostMapping("/register")
    public ResponseResult register(User user) {
        return userService.register(user);
    }

    /**
     * 登录
     * @param captcha 验证码
     */
    @PostMapping("/{captcha}")
    public ResponseResult login(@PathVariable("captcha") String captcha, User user) {
        return userService.login(user);
    }

    /**
     * 获取验证码
     */
    @GetMapping("/captcha")
    public ResponseResult getCaptcha() {
        return null;
    }

    /**
     * 发送邮件
     * @param emailAddress 邮件地址
     */
    @GetMapping("/verify_code")
    public ResponseResult sendVerifyCode(@RequestParam("email") String emailAddress) {
        return null;
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public ResponseResult updatePassword(User user) {
        return null;
    }

    /**
     * 获取作者信息
     */
    @GetMapping("/{userId}")
    public ResponseResult getUserInfo(@PathVariable("userId") String userId) {
        return null;
    }

    /**
     * 修改用户信息
     */
    @PutMapping
    public ResponseResult updateUserInfo(User user) {
        return null;
    }

    @PostMapping("/login")
    public ResponseResult login(User user, HttpServletResponse httpServletResponse) {
        User dbUser = userService.findUserByName(user.getUserName());
        if (dbUser == null) {
            return ResponseResult.error("没找到用户");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            return ResponseResult.error("密码错误");
        }
        Map<String, Object> data = new HashMap<>(10);
        data.put("roles", dbUser.getRoles());
        data.put("id", dbUser.getId());
        data.put("userName", dbUser.getUserName());
        return ResponseResult.success();
    }

    @GetMapping("/logout")
    public ResponseResult logout() {
        return ResponseResult.success("退出成功");
    }


    @GetMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id") String userId) {
        return ResponseResult.success("删除成功");
    }


}

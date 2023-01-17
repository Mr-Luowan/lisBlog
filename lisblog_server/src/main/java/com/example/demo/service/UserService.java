package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.Model.User;
import com.example.demo.response.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
public interface UserService extends IService<User> {

    User findUserByName(String userName);

    ResponseResult initManagerAccount(User user);

    ResponseResult register(User user);

    ResponseResult login(User user);
}

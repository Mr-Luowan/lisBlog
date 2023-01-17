package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Model.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.ResponseResult;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
import com.example.demo.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public ResponseResult initManagerAccount(User user) {
        log.error("初始化管理员账户");
        return ResponseResult.success();
    }

    @Override
    public ResponseResult register(User user) {
        return null;
    }

    @Override
    public ResponseResult login(User user) {
        User dbUser = findUserByName(user.getUserName());
        if (dbUser == null) {
            log.error("没找到用户");
            return ResponseResult.error("没找到用户");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            log.error("密码错误");
            return ResponseResult.error("密码错误");
        }
        Map<String, Object> data = new HashMap<>(10);
        data.put("roles", dbUser.getRoles());
        data.put("id", dbUser.getId());
        data.put("userName", dbUser.getUserName());
        String token = jwtUtils.generateToken(data);
        return ResponseResult.success(token);
    }

    @Override
    public User findUserByName(String userName) {
        if (this.baseMapper != null) {
            return this.baseMapper.findUserByName(userName);
        } else {
            return null;
        }
    }

}

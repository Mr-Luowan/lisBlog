package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Model.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
public interface UserMapper extends BaseMapper<User> {

    User findUserByName(String userName);
}

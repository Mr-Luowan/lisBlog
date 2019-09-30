package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@ResponseBody
public interface UserMapper {
//    @Insert("insert into user values (#{id}, #{username},#{password})")
//    void insert(User user);

    @Select("select * from user")
    List<User> findAll();
}

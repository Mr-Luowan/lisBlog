package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.util.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    JwtUtils jwtUtils;

    @Test
    public void testDate() {
        String replace = LocalDate.now().toString().replace("-", "");
        System.out.println(replace);
    }

    @Test
    public void generatePwd() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String admin = bCryptPasswordEncoder.encode("admin");
        if (admin.matches("admin")) {
            System.out.println("验证成功");
        } else {
            System.out.println("验证失败");
        }
    }

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUserName("zhangsan");
        String token = jwtUtils.generateToken(user);

        String userNameFromToken = jwtUtils.getUserNameFromToken(token);
        System.out.println(userNameFromToken);
        assert ("zhangsan".equals(userNameFromToken));
    }

}

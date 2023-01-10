package com.example.demo;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateJwtTest {
    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", "111");
        map.put("userName", "222");
        map.put("roles", "333");
//        generateToken(map);

        parseToken("eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6IjEiLCJpZCI6IjEiLCJ1c2VyTmFtZSI6ImxsbCIsImV4cCI6MTY3MzM5MTM1OH0.2CLb9kdZZJIy__Cph7eT5U6ZU5ojFzKQdGEirkSYCVfXp3oFXPUcM7kURUWmRn2vcW_2M3FTzg8-ze9EzUdFHg");
    }

    private static String parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("f55583bcff5d5b659bfaeb97a06ca2ca")
                    .parseClaimsJws(token)
                    .getBody();
            String id = (String) claims.get("id");
            String name = (String) claims.get("userName");
            String role = (String) claims.get("roles");
            System.out.println("id = " + id);
            System.out.println("userName = " + name);
            System.out.println("roles = " + role);
        } catch (Exception e) {
            System.out.println("JWT格式验证失败:" +  e);
        }
        return "";
    }

    private static String generateToken(Map<String, Object> map) {
        long now = 10 * 60 * 1000;
        String jwt = "";
        jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + now))
                .signWith(SignatureAlgorithm.HS512, "f55583bcff5d5b659bfaeb97a06ca2ca")
                .compact();
        System.out.println("生成的token = " + jwt);
        return jwt;
    }

    public static String generateMD5Key(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

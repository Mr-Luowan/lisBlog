package com.example.demo.util;


import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * 生成和校验jwt的工具类，其中有些jwt相关的密钥信息是从项目配置文件中配置的
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "lisblog.jwt")
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(String userID) {
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userID)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {

        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}

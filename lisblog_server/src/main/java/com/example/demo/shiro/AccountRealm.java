package com.example.demo.shiro;

import com.example.demo.Model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
import com.example.demo.util.RedisUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AccountRealm extends AuthorizingRealm {

    private static final String TAG = "AccountRealm";

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    Logger logger;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }



    /**
     * 只有当需要检测用户权限的时候才会调用此方法
     * 授权模块，获取用户角色和权限。
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        logger.error(TAG + " ___ doGetAuthorizationInfo run !  token is = "  + token);
        Claims claimsFromToken = jwtUtils.getClaimsFromToken(token.toString());
        String userId = (String) claimsFromToken.get("id");

//        Integer userId = JWTUtil.getUserId(token.toString());
//
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//
//        // 获取用户角色集
//        Set<String> roleSet = roleService.selectRolePermissionByUserId(userId);
//        simpleAuthorizationInfo.setRoles(roleSet);
//
//        // 获取用户权限集
//        Set<String> permissionSet = menuService.findUserPermissionsByUserId(userId);
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
//        return simpleAuthorizationInfo;
        return null;
    }

    /**
     * 用户认证:编写shiro判断逻辑，进行用户认证
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String userId = (String) jwtUtils.getClaimsFromToken((String) jwtToken.getPrincipal()).get("id");
        logger.error(TAG + " ___ doGetAuthenticationInfo jwtToken  = " + jwtToken.getPrincipal());
        logger.error(TAG + " ___ userId form Token  = " + userId);
        User user = userService.getById(userId);
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (user.getState().equals("0")) {
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());

//        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
//        String token = (String) authenticationToken.getCredentials();
//        String encryptToken = UofferUtil.encryptToken(token); //加密token
//        String username = JWTUtil.getUsername(token); //从token中获取username
//        Integer userId = JWTUtil.getUserId(token);    //从token中获取userId
//
//        // 通过redis查看token是否过期
//        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
//        String ip = IPUtil.getIpAddr(request);
//        String encryptTokenInRedis = redisUtil.get(Constant.RM_TOKEN_CACHE + encryptToken + StringPool.UNDERSCORE + ip);
//        if (!token.equalsIgnoreCase(UofferUtil.decryptToken(encryptTokenInRedis))) {
//            throw new AuthenticationException("token已经过期");
//        }
//
//        // 如果找不到，说明已经失效
//        if (StringUtils.isBlank(encryptTokenInRedis)) {
//            throw new AuthenticationException("token已经过期");
//        }
//
//        if (StringUtils.isBlank(username)) {
//            throw new AuthenticationException("token校验不通过");
//        }
//
//        // 通过用户id查询用户信息
//        SysUser user = userService.getById(userId);
//
//        if (user == null) {
//            throw new AuthenticationException("用户名或密码错误");
//        }
//        if (!JWTUtil.verify(token, username, user.getPassword())) {
//            throw new AuthenticationException("token校验不通过");
//        }
//        return new SimpleAuthenticationInfo(token, token, "febs_shiro_realm");
    }
}

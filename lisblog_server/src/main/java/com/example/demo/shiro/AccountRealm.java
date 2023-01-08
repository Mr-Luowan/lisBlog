package com.example.demo.shiro;

import com.example.demo.Model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
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
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;


    @Autowired
    Logger logger;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.error(TAG + " ___ doGetAuthorizationInfo run !  " );
        Set<String> realmNames = principalCollection.getRealmNames();
        if (realmNames != null && !realmNames.isEmpty()) {
            for (String realmName : realmNames) {
                logger.error(TAG + " ___ doGetAuthorizationInfo realmName  " + realmName);
            }
        }
        return null;
    }

    /**
     * 登录验证，失败的话返回null
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        logger.error(TAG + " ___ doGetAuthenticationInfo jwtToken  = " + jwtToken.getPrincipal());
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (user.getState().equals("0")) {
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}

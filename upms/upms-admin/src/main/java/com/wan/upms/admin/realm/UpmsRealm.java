package com.wan.upms.admin.realm;

import com.wan.common.util.MD5Util;
import com.wan.upms.dao.model.UpmsUser;
import com.wan.upms.dao.model.UpmsUserExample;
import com.wan.upms.rpc.api.UpmsUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by w1992wishes on 2017/8/26.
 */
public class UpmsRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(UpmsRealm.class);

    @Autowired
    private UpmsUserService upmsUserService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 当前用户
        UpmsUser upmsUser = (UpmsUser) principalCollection.getPrimaryPrincipal();
        logger.info("授权：upmsUser={}", upmsUser);

        // 全部权限 TODO
        Set<String> permissions = new HashSet<>();
        permissions.add("*:*:*");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        logger.info("认证：username={}, password={}", username, password);

        // 查询用户信息
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(username);
        UpmsUser upmsUser = upmsUserService.selectFirstByExample(upmsUserExample);

        if (null == upmsUser) {
            throw new UnknownAccountException("帐号不存在！");
        }
        if (!upmsUser.getPassword().equals(MD5Util.MD5(password + upmsUser.getSalt()))) {
            throw new IncorrectCredentialsException("密码错误！");
        }
        if (upmsUser.getStatus() == -1) {
            throw new LockedAccountException("账号已被锁定！");
        }

        return new SimpleAuthenticationInfo(upmsUser, password, getName());
    }

}
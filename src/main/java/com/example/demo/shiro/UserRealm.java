package com.example.demo.shiro;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user = sysUserMapper.selectByNo(token.getUsername());
        if (null == user) {
            //工号不存在
            throw new AccountException("账号或密码不正确");
        } else if (user.getStatus() == 0) {
            throw new AccountException("该账号禁止登录");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getUsername());
    }

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SysUser token = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String userId = token.getNo();

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();

//        roleSet.add()
//        info.setRoles();
        //添加资源的授权字符串
        info.addStringPermission("user:add");
        return info;
    }
}

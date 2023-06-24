package org.scy.conifg;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.scy.pojo.User;

import java.util.Set;

/**我的reaml配置**/
public class MyRealms extends AuthorizingRealm {

    // 重写认证方法  根据业务需求实现
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名和密码
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        // 根据用户名查询数据库或其他存储，获取用户信息
        User user = getUserByUsername(username);

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }

        // 校验密码
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }

        // 认证通过，返回认证信息
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }

    // 重写授权方法  根据业务需求实现
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录用户的用户名
        String username = (String) principalCollection.getPrimaryPrincipal();

        // 根据用户名查询数据库或其他存储，获取用户角色和权限信息
        Set<String> roles = getUserRoles(username);
        Set<String> permissions = getUserPermissions(username);

        // 创建授权信息对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    // 根据用户名查询用户信息
    private User getUserByUsername(String username) {
        // 实现根据用户名查询数据库或其他存储的逻辑
        // 返回用户信息，包括用户名和密码等
        return null;
    }

    // 根据用户名查询用户角色
    private Set<String> getUserRoles(String username) {
        // 实现根据用户名查询数据库或其他存储的用户角色的逻辑
        // 返回用户角色的集合
        return null;
    }

    // 根据用户名查询用户权限
    private Set<String> getUserPermissions(String username) {
        // 实现根据用户名查询数据库或其他存储的用户权限的逻辑
        // 返回用户权限的集合
        return null;
    }}

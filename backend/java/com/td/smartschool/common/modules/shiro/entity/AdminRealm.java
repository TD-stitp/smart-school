package com.td.smartschool.common.modules.shiro.entity;

import com.td.smartschool.admin.pojo.Admin;
import com.td.smartschool.admin.service.AdminService;
import com.td.smartschool.common.modules.shiro.multiple.CustomizedToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/14 21:59
 */
public class AdminRealm extends AuthorizingRealm {

    private final String REALM_NAME = "adminRealm";
    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin admin = adminService.getAdmin((String) principalCollection.getPrimaryPrincipal());
        if (admin != null) {
            info.addRole("admin");
            info.addStringPermission("admin:" + admin.getPermission());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomizedToken token = (CustomizedToken) authenticationToken;
        String account = token.getUsername();
        Admin admin = adminService.getAdmin(account);
        if (admin == null) {
            throw new UnknownAccountException("用户不存在");
        }
        return new SimpleAuthenticationInfo(account, admin.getPassword(), REALM_NAME);
    }
}

package com.td.smartschool.common.modules.shiro.entity;

import com.td.smartschool.admin.pojo.User;
import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.admin.service.UserService;
import com.td.smartschool.customer.common.utils.Education;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2021/9/25 10:56
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    TableService tableService;
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        String account = (String) session.getAttribute("account");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = userService.getUser((String) principalCollection.getPrimaryPrincipal());
        if (user != null) {
            info.addRole(user.getRole());
            info.addStringPermission(user.getPermission());
        }
        if (account != null) {
            info.addRole("electricity");
            info.addStringPermission("electricity:login");
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Education education = new Education();
        JSONObject jsonObject = education.login(token.getUsername(), String.valueOf(token.getPassword()));
        if (!jsonObject.getString("code").equals(Education.SUCCESS_CODE)) {
            throw new IncorrectCredentialsException("用户名或密码不正确");
        }
        tableService.createFollow(token.getUsername());
        SecurityUtils.getSubject().getSession().setAttribute("username", token.getUsername());
        System.out.println(new Date() + "  " + token.getUsername() + "登陆教务系统");
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), token.getPassword(), "");
    }
}

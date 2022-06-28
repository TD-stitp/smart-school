package com.td.smartschool.common.modules.shiro.multiple;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/14 22:00
 */
public class CustomizedToken extends UsernamePasswordToken {


    //登录类型，判断是普通用户登录，教师登录还是管理员登录
    private String loginType;

    public CustomizedToken(final String username, final String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}

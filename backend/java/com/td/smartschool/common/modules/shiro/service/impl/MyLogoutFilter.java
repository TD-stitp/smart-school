package com.td.smartschool.common.modules.shiro.service.impl;


import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/16 9:51
 */
public class MyLogoutFilter extends LogoutFilter {

    @Override
    public boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("自定义logout");
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 注销教务系统session
        session.removeAttribute("BIGipServerpool_xk");
        session.removeAttribute("JSESSIONID");
        session.removeAttribute("username");
        // 注销一卡通系统session
        session.removeAttribute("ASP.NET_SessionID");
        session.removeAttribute("pageToken");
        session.removeAttribute("ticket");
        session.removeAttribute("account");
        try {
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public MyLogoutFilter logoutFilter() {
        return new MyLogoutFilter();
    }
}

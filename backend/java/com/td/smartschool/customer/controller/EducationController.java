package com.td.smartschool.customer.controller;

import com.td.smartschool.admin.service.UserService;
import com.td.smartschool.common.modules.shiro.entity.LoginType;
import com.td.smartschool.common.modules.shiro.multiple.CustomizedToken;
import com.td.smartschool.customer.common.utils.Education;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2021/10/10 19:51
 */

@RestController
@RequestMapping("/api/education")
@AllArgsConstructor
public class EducationController {

    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();

    private final UserService userService;

    @GetMapping("/toLogin")
    public boolean toLogin() {
        Session session = SecurityUtils.getSubject().getSession();
        return session.getAttribute("username") != null;
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        JSONObject result = new JSONObject()
                .put("msg", "请勿重复登录")
                .put("code", "404");
        if (!subject.isAuthenticated()) {
            CustomizedToken token = new CustomizedToken(username, password, USER_LOGIN_TYPE);
            try {
                subject.login(token);
                result.put("msg", "登录成功");
                result.put("code", Education.SUCCESS_CODE);
                if (userService.getUser(username) != null) {
                    result.put("flag", "1");
                } else {
                    result.put("flag", "0");
                }
            } catch (AuthenticationException e) {
                result.put("msg", e.getMessage());
                result.put("code", Education.FAIL_CODE);
            } finally {
                result.put("token", subject.getSession().getId());
            }
        }
        return result.toString();
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            return new JSONObject()
                    .put("code", Education.SUCCESS_CODE)
                    .put("msg", "注销成功")
                    .toString();
        } else {
            return new JSONObject()
                    .put("code", Education.FAIL_CODE)
                    .put("msg", "请先登录")
                    .toString();
        }
    }

    @GetMapping("/queryClasses")
    public String queryClasses(@RequestParam("year") int year, @RequestParam("semester") int semester) {
        String xnm = String.valueOf(year);
        String xqm = "3";
        if (semester == 2) {
            xqm = "12";
        }
        return new Education().queryClasses(xnm, xqm).toString();
    }

    @GetMapping("/queryExamInfo")
    public String queryExamInfo(@RequestParam("year") String year, @RequestParam("semester") String semester) {
        String xqm = "3";
        if ("2".equals(semester)) {
            xqm = "12";
        }
        return new Education().queryExamInfo(year, xqm).toString();
    }

    @GetMapping("/queryScore")
    public String queryScore(@RequestParam("year") int year, @RequestParam("semester") int semester) {
        String xnm = String.valueOf(year);
        String xqm = "3";
        if (semester == 2) {
            xqm = "12";
        }
        return new Education().queryScore(xnm, xqm).toString();
    }

    @GetMapping("/queryMessage")
    public String queryMessage(@RequestParam("flag") String flag) {
        Education education = new Education();
        return education.queryMessage(flag).toString();
    }

    @GetMapping("/updatePassword")
    public String updatePassword(@RequestParam("oldPassword") String old, @RequestParam("newPassword") String newPassword, @RequestParam("newAgain") String newAgain) {
        Education education = new Education();
        JSONObject result = education.updatePassword(old, newPassword, newAgain);
        if ("true".equals(result.getString("flag"))) {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            System.out.println(new Date(System.currentTimeMillis()) + "用户" + username + "修改了密码");
            SecurityUtils.getSubject().logout();
        }

        return result.toString();
    }

    @GetMapping("/preCourses")
    public String preCourses() {
        Education edu = new Education();
        return edu.preCourses().toString();
    }

    @GetMapping("/selectedCourses")
    public String selectedCourses() {
        Education edu = new Education();
        return edu.selectedCourses().toString();
    }

    @GetMapping("/selectCourses")
    public String selectCourses(@RequestParam("kcyxb_id") String kcyxb_id) {
        Education edu = new Education();
        return edu.selectCourses(kcyxb_id).toString();
    }

    @GetMapping("/unselectCourses")
    public String unselectCourses(@RequestParam("xsyxb_id") String xsyxb_id) {
        Education edu = new Education();
        return edu.unselectCourses(xsyxb_id).toString();
    }
}

package com.td.smartschool.admin.controller;

import com.td.smartschool.admin.pojo.Admin;
import com.td.smartschool.admin.pojo.AdminPermission;
import com.td.smartschool.admin.service.AdminBeReviewedService;
import com.td.smartschool.admin.service.AdminService;
import com.td.smartschool.common.modules.shiro.entity.LoginType;
import com.td.smartschool.common.modules.shiro.multiple.CustomizedToken;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/14 22:50
 */
@CrossOrigin(origins = {"http://localhost:8081", "null"})
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();

    private final AdminService adminService;
    private final AdminBeReviewedService adminBeReviewedService;

    @CrossOrigin
    @RequestMapping("/register")
    public String register(Admin admin) {
        return adminBeReviewedService.addBeReviewed(admin).toString();
    }

    @RequestMapping("/agree")
    public String agree(@RequestParam("account") String account) {
        return adminBeReviewedService.agree(account).toString();
    }

    @RequestMapping("/disagree")
    public String disagree(@RequestParam("account") String account) {
        return adminBeReviewedService.disagree(account).toString();
    }

    @RequestMapping("/setPermission")
    public String setPermission(@RequestParam("account") String account, AdminPermission permission) {
        return adminService.setPermission(account, permission).toString();
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(Admin admin) {
        return adminService.addAdmin(admin).toString();
    }

    @RequestMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam("account") String account) {
        return adminService.deleteAdmin(account).toString();
    }

    @RequestMapping("/getAdmins")
    public String getAdmins() {
        return adminService.getAdmins().toString();
    }

    @RequestMapping("/getBeRevieweds")
    public String getBeRevieed() {
        return adminBeReviewedService.getBeRevieweds().toString();
    }

    @RequestMapping("/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        JSONObject result = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                CustomizedToken customizedToken = new CustomizedToken(account, password, ADMIN_LOGIN_TYPE);
                subject.login(customizedToken);
                result.put("code", "200");
                result.put("msg", "登录成功");
                result.put("nickname", adminService.getAdmin(account).getNickname());
            } catch (Exception e) {
                result.put("code", "202");
                result.put("msg", "账号或密码错误");
            }
        } else {
            result.put("code", "202");
            result.put("msg", "请勿重复登录");
        }
        return result.toString();
    }

    @RequestMapping("/logout")
    public String logout() {
        JSONObject result = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            result.put("msg", "注销成功");
        } else {
            result.put("msg", "请先登录");
        }
        return result.toString();
    }

}
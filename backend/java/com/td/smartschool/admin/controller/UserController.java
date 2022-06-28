package com.td.smartschool.admin.controller;

import com.td.smartschool.admin.pojo.User;
import com.td.smartschool.admin.service.UserBeReviewedService;
import com.td.smartschool.admin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/25 22:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    UserService userService;
    UserBeReviewedService beReviewedService;

    @GetMapping("/apply")
    public String grant(User user) {
        return beReviewedService.addBeReviewed(user).toString();
    }

    @GetMapping("/getUsers")
    public String getUsers() {
        return userService.getUsers().toString();
    }

    @GetMapping("/addUser")
    public String addUser(User user) {
        return userService.addUser(user).toString();
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("account") String account) {
        return userService.deleteUser(account).toString();
    }

    @GetMapping("/getBeRevieweds")
    public String getBerevieweds() {
        return beReviewedService.getBeRevieweds().toString();
    }

    @GetMapping("/agree")
    public String agree(@RequestParam("account") String account) {
        return beReviewedService.agree(account).toString();
    }

    @GetMapping("/disagree")
    public String disagree(@RequestParam("account") String account) {
        return beReviewedService.disagree(account).toString();
    }
}

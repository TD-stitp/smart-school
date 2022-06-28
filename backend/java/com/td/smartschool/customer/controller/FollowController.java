package com.td.smartschool.customer.controller;

import com.td.smartschool.customer.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/25 9:29
 */
@RestController
@RequestMapping("/api/follow")
@AllArgsConstructor
public class FollowController {

    FollowService followService;

    @GetMapping("/getFollows")
    public String getFollows() {
        return followService.getFollows().toString();
    }

    @GetMapping("/addFollow")
    public String addFollow(String code) {
        return followService.addFollow(code).toString();
    }

    @GetMapping("/cancelFollow")
    public String cancelFollow(String code) {
        return followService.cancelFollow(code).toString();
    }

}


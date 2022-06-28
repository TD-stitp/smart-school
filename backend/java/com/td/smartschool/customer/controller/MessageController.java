package com.td.smartschool.customer.controller;

import com.td.smartschool.customer.pojo.Message;
import com.td.smartschool.customer.service.FollowService;
import com.td.smartschool.customer.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/25 22:20
 */
@RestController
@RequestMapping("/api/message")
@AllArgsConstructor
public class MessageController {

    MessageService messageService;
    FollowService followService;

    @GetMapping("/getMessages")
    public String getMessages(String code) {
        return messageService.getMessages(code).toString();
    }

    @GetMapping("/sendMessage")
    public String addMessage(String code, String message) {
        return messageService.sendMessage(code, new Message(String.valueOf(System.currentTimeMillis()), message)).toString();
    }
}
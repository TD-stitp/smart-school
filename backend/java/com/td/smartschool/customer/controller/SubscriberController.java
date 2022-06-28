package com.td.smartschool.customer.controller;

import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.customer.service.SubscriberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/25 17:06
 */
@RestController
@RequestMapping("/api/subscriber")
@AllArgsConstructor
public class SubscriberController {

    TableService tableService;
    SubscriberService subscriberService;

    @GetMapping("/getSubscribers")
    public String getSubscribers(String code) {
        return subscriberService.getSubscribers(code).toString();
    }

    @GetMapping("/getSubscribersNumber")
    public String getSubscriberNumber(String code) {
        return subscriberService.getSubscriberNumber(code).toString();
    }

}

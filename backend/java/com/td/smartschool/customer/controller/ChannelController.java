package com.td.smartschool.customer.controller;

import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.customer.pojo.Name;
import com.td.smartschool.customer.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/26 12:10
 */
@RestController
@RequestMapping("/api/channel")
@AllArgsConstructor
public class ChannelController {

    ChannelService channelService;
    TableService tableService;

    @GetMapping("/getChannels")
    public String getChannels() {
        return channelService.getChannels().toString();
    }

    @GetMapping("/setChannelName")
    public String setChannelName(Name name) {
        return channelService.setName(name).toString();
    }

    @GetMapping("/createChannel")
    public String createChannel(String name) {
        return tableService.createTable(name).toString();
    }

    @GetMapping("/deleteChannel")
    public String deleteChannel(String code) {
        return channelService.deleteChannel(code).toString();
    }

}

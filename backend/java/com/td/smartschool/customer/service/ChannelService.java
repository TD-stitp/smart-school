package com.td.smartschool.customer.service;

import com.td.smartschool.customer.pojo.Channel;
import com.td.smartschool.customer.pojo.Name;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月26日 12:04:40
 */
@Service
public interface ChannelService {

    JSONObject getChannels();

    JSONObject addChannel(String code, String name);

    int addChannel(String username, Channel channel);

    JSONObject deleteChannel(String code);

    int addName(Name name);

    JSONObject setName(Name name);

    String getName(String code);

}

package com.td.smartschool.customer.service.impl;

import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.customer.mapper.ChannelMapper;
import com.td.smartschool.customer.mapper.FollowMapper;
import com.td.smartschool.customer.mapper.SubscriberMapper;
import com.td.smartschool.customer.pojo.Channel;
import com.td.smartschool.customer.pojo.Name;
import com.td.smartschool.customer.pojo.Subscriber;
import com.td.smartschool.customer.service.ChannelService;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/26 12:06
 */
@Service
@AllArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final static String TABLE_NAME = "channels";

    ChannelMapper channelMapper;
    TableService tableService;
    SubscriberMapper subscriberMapper;
    FollowMapper followMapper;

    private String getUsername() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute("username");
    }

    @Override
    public JSONObject getChannels() {
        String username = getUsername();
        JSONObject result = new JSONObject()
                .put("total", 0)
                .put("msg", "未创建频道！");
        if (tableService.queryTableExists(TABLE_NAME, username)) {
            List<Channel> channels = channelMapper.getChannels(username);
            Format format = new SimpleDateFormat("yy-MM-dd");
            JSONArray array = new JSONArray();
            result.put("code", "200");
            result.put("total", channels.size());
            result.remove("msg");
            for (Channel channel : channels) {
                JSONObject temp = new JSONObject();
                temp.put("code", channel.getCode());
                temp.put("name", channel.getName());
                temp.put("date", format.format(channel.getCreatetime()));
                array.put(temp);
            }
            result.put("channels", array);
        }
        return result;
    }

    @Override
    public JSONObject addChannel(String code, String name) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "订阅成功");
        channelMapper.addChannel(getUsername(), code, name, new Date());
        return result;
    }

    @Override
    public int addChannel(String username, Channel channel) {
        return channelMapper.addChannel(username, channel.getCode(), channel.getName(), channel.getCreatetime());
    }

    @Override
    public int addName(Name name) {
        return channelMapper.addName(name);
    }

    @Override
    public JSONObject setName(Name name) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "修改成功");
        channelMapper.setName(name.getCode(), name.getName());
        channelMapper.setChannelName(getUsername(), name.getCode(), name.getName());
        return result;
    }

    @Override
    public String getName(String code) {
        return channelMapper.getName(code);
    }

    @Override
    public JSONObject deleteChannel(String code) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "解散成功");
        channelMapper.deleteChannel(getUsername(), code);
        channelMapper.deleteName(code);
        for (Subscriber subscriber : subscriberMapper.getSubscribers(code)) {
            followMapper.deleteFollow(subscriber.getStudentID(), code);
        }
        tableService.dropMessage(code);
        tableService.dropSubscriber(code);
        return result;
    }
}

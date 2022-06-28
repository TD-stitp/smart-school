package com.td.smartschool.customer.service.impl;

import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.customer.mapper.FollowMapper;
import com.td.smartschool.customer.pojo.Follow;
import com.td.smartschool.customer.pojo.Subscriber;
import com.td.smartschool.customer.service.ChannelService;
import com.td.smartschool.customer.service.FollowService;
import com.td.smartschool.customer.service.SubscriberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/24 19:43
 */
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    @NonNull
    FollowMapper followMapper;
    @NonNull
    SubscriberService subscriberService;
    @NonNull
    TableService tableService;
    @NonNull ChannelService channelService;

    private String getUsername() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute("username");
    }

    private JSONObject basicAddFollow(String username, String code) {
        JSONObject result = new JSONObject();
        if (tableService.queryTableExists("subscribers", code)) {
            try {
                followMapper.addFollow(username, code, "目前还没有消息哦~");
                subscriberService.addSubscriber(code, new Subscriber(username));
            } catch (Exception e) {
                e.printStackTrace();
                result.put("code", "202");
                result.put("msg", "订阅失败");
            }
            result.put("code", "200");
            result.put("msg", "订阅成功");
        } else {
            result.put("code", "404");
            result.put("msg", "错误的订阅码");
        }
        return result;
    }

    @Override
    public JSONObject getFollows() {
        String username = getUsername();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.put("code", "200");
        result.put("total", getFollowNumber(username));
        for (Follow follow : followMapper.getFollows(username)) {
            JSONObject temp = new JSONObject();
            temp.put("code", follow.getCode());
            temp.put("name", channelService.getName(follow.getCode()));
            temp.put("message", follow.getLastmessage());
            array.put(temp);
        }
        result.put("follows", array);
        return result;
    }

    @Override
    public int getFollowNumber(String username) {
        return followMapper.getFollowNumber(username);
    }

    @Override
    public JSONObject addFollow(String code) {
        return basicAddFollow(getUsername(), code);
    }

    @Override
    public JSONObject addFollow(String username, String code) {
        return basicAddFollow(username, code);
    }

    @Override
    public String getLastMessage(String code) {
        return followMapper.getLastFollow(getUsername(), code).getLastmessage();
    }

    @Override
    public JSONObject cancelFollow(String code) {
        String username = getUsername();
        int followFlag = followMapper.deleteFollow(username, code);
        int subscriberFlag = subscriberService.deleteSubscriber(code, new Subscriber(username));
        JSONObject result = new JSONObject();
        if (followFlag == 1 && subscriberFlag == 1) {
            result.put("code", "200");
            result.put("msg", "成功取消订阅");
        } else {
            result.put("code", "202");
            result.put("msg", "取消失败，请稍后重试");
        }
        return result;
    }

    @Override
    public int setMessage(Follow follow) {
        return followMapper.setFollow(getUsername(), follow.getCode(), follow.getLastmessage());
    }
}

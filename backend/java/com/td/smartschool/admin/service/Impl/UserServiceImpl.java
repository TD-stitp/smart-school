package com.td.smartschool.admin.service.Impl;

import com.td.smartschool.admin.mapper.UserMapper;
import com.td.smartschool.admin.pojo.User;
import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.admin.service.UserService;
import com.td.smartschool.customer.mapper.ChannelMapper;
import com.td.smartschool.customer.mapper.FollowMapper;
import com.td.smartschool.customer.mapper.SubscriberMapper;
import com.td.smartschool.customer.pojo.Channel;
import com.td.smartschool.customer.pojo.Subscriber;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/25 17:26
 */
@Service(value = "userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    TableService tableService;
    FollowMapper followMapper;
    ChannelMapper channelMapper;
    SubscriberMapper subscriberMapper;

    @Override
    public JSONObject getUsers() {
        List<User> list = userMapper.getUsers();
        JSONObject result = new JSONObject();
        JSONArray users = new JSONArray();
        result.put("code", "200");
        result.put("total", list.size());
        for (User user : list) {
            JSONObject temp = new JSONObject();
            temp.put("account", user.getAccount());
            temp.put("name", user.getName());
            temp.put("department", user.getDepartment());
            users.put(temp);
        }
        result.put("options", users);
        return result;
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public JSONObject addUser(User user) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "添加成功!");
        userMapper.addUser(user);
        tableService.createChannel(user.getAccount());
        return result;
    }

    @Override
    public JSONObject deleteUser(String username) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "删除成功!");
        userMapper.deleteUser(username);
        for (Channel channel : channelMapper.getChannels(username)) {
            channelMapper.deleteName(channel.getCode());
            tableService.dropMessage(channel.getCode());
            for (Subscriber subscriber : subscriberMapper.getSubscribers(channel.getCode())) {
                followMapper.deleteFollow(subscriber.getStudentID(), channel.getCode());
            }
            tableService.dropSubscriber(channel.getCode());
        }
        tableService.dropChannel(username);
        return result;
    }
}

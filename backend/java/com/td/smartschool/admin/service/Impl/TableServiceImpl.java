package com.td.smartschool.admin.service.Impl;

import com.td.smartschool.admin.mapper.TableMapper;
import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.customer.common.utils.UUIDUtil;
import com.td.smartschool.customer.pojo.Channel;
import com.td.smartschool.customer.pojo.Name;
import com.td.smartschool.customer.service.ChannelService;
import com.td.smartschool.customer.service.FollowService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/21 21:25
 */
@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    // 自动注入避免循环注入
    @Autowired
    FollowService followService;
    @Autowired
    ChannelService channelService;

    @NonNull
    TableMapper tableMapper;

    @Override
    public JSONObject createTable(String name) {
        JSONObject result = new JSONObject();
        String username = (String) SecurityUtils.getSubject().getSession().getAttribute("username");
        String code = UUIDUtil.getCode();
        try {
            tableMapper.createMessages(code);
            tableMapper.createSubscribers(code);
            channelService.addChannel(username, new Channel(code, name, new Date()));
            channelService.addName(new Name(code, name));
            followService.addFollow(username, code);
            result.put("code", code);
            result.put("name", name);
            result.put("msg", "创建成功");
        } catch (Exception e) {
            result.put("msg", "创建失败");
        }
        return result;
    }

    @Override
    public int createFollow(String tableName) {
        return tableMapper.createFollows(tableName);
    }

    @Override
    public int createChannel(String tableName) {
        return tableMapper.createChannels(tableName);
    }

    @Override
    public int getMessageNumber(String uuid) {
        return tableMapper.getDataRows("messages", uuid);
    }

    @Override
    public int getFollowNumber(String uuid) {
        return tableMapper.getDataRows("follows", uuid);
    }

    @Override
    public int getSubscriberNumber(String uuid) {
        return tableMapper.getDataRows("subscribers", uuid);
    }

    @Override
    public int dropFollow(String tableName) {
        return tableMapper.dropDatabase("follows", tableName);
    }

    @Override
    public int dropMessage(String tableName) {
        return tableMapper.dropDatabase("messages", tableName);
    }

    @Override
    public int dropSubscriber(String tableName) {
        return tableMapper.dropDatabase("subscribers", tableName);
    }

    @Override
    public int dropChannel(String tableName) {
        return tableMapper.dropDatabase("channels", tableName);
    }

    @Override
    public boolean queryTableExists(String type, String tableName) {
        try {
            tableMapper.getDataRows(type, tableName);
        } catch (BadSqlGrammarException e) {
            return false;
        }
        return true;
    }
}

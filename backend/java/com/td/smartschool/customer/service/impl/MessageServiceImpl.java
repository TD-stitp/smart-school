package com.td.smartschool.customer.service.impl;

import com.td.smartschool.customer.mapper.MessageMapper;
import com.td.smartschool.customer.pojo.Follow;
import com.td.smartschool.customer.pojo.Message;
import com.td.smartschool.customer.service.FollowService;
import com.td.smartschool.customer.service.MessageService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/24 19:58
 */
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    MessageMapper messageMapper;
    FollowService followService;

    @Override
    public JSONObject getMessages(String tableName) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.put("code", "200");
        for (Message message : messageMapper.getMessages(tableName)) {
            JSONObject temp = new JSONObject();
            temp.put("message", message.getMessage());
            temp.put("date", format.format(new Date(Long.parseLong(message.getDate()))));
            array.put(temp);
        }
        result.put("messages", array);
        return result;
    }

    @Override
    public Message getLastMessage(String tableName) {
        Message message = null;
        try {
            message = messageMapper.getLastMessage(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public JSONObject sendMessage(String tableName, Message message) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "发送成功");
        messageMapper.addMessage(tableName, message.getDate(), message.getMessage());
        followService.setMessage(new Follow(tableName, message.getMessage()));
        return result;
    }
}

package com.td.smartschool.customer.service.impl;

import com.td.smartschool.customer.mapper.SubscriberMapper;
import com.td.smartschool.customer.pojo.Subscriber;
import com.td.smartschool.customer.service.SubscriberService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/24 20:04
 */
@Service
@AllArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    SubscriberMapper subscriberMapper;

    private int getDataRows(String tableName) {
        return subscriberMapper.getDataRows(tableName);
    }

    @Override
    public JSONObject getSubscribers(String tableName) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.put("code", "200");
        result.put("total", getDataRows(tableName));
        for (Subscriber subscriber : subscriberMapper.getSubscribers(tableName)) {
            JSONObject temp = new JSONObject();
            temp.put("id", subscriber.getStudentID());
            array.put(temp);
        }
        result.put("subscribers", array);
        return result;
    }

    @Override
    public JSONObject getSubscriberNumber(String tableName) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("num", getDataRows(tableName));
        return result;
    }

    @Override
    public int addSubscriber(String tableName, Subscriber subscriber) {
        return subscriberMapper.addSubscriber(tableName, subscriber.getStudentID());
    }

    @Override
    public int deleteSubscriber(String tableName, Subscriber subscriber) {
        return subscriberMapper.deleteSubscriber(tableName, subscriber.getStudentID());
    }
}

package com.td.smartschool.customer.service;


import com.td.smartschool.customer.pojo.Subscriber;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月24日 20:00:33
 */
@Service
public interface SubscriberService {

    JSONObject getSubscribers(String tableName);

    JSONObject getSubscriberNumber(String tableName);

    int addSubscriber(String tableName, Subscriber subscriber);

    int deleteSubscriber(String tableName, Subscriber subscriber);
}

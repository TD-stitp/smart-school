package com.td.smartschool.admin.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/21 21:23
 */
@Service
public interface TableService {

    JSONObject createTable(String name);

    int createFollow(String tableName);

    int createChannel(String tableName);

    int getMessageNumber(String uuid);

    int getFollowNumber(String uuid);

    int getSubscriberNumber(String uuid);

    int dropFollow(String tableName);

    int dropMessage(String tableName);

    int dropSubscriber(String tableName);

    int dropChannel(String tableName);

    boolean queryTableExists(String type, String tableName);
}

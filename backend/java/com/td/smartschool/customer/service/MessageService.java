package com.td.smartschool.customer.service;


import com.td.smartschool.customer.pojo.Message;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月24日 19:54:39
 */
@Service
public interface MessageService {

    JSONObject getMessages(String tableName);

    Message getLastMessage(String tableName);

    JSONObject sendMessage(String tableName, Message message);
}

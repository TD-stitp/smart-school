package com.td.smartschool.customer.service;


import com.td.smartschool.customer.pojo.Follow;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月24日 19:23:54
 */
@Service
public interface FollowService {

    JSONObject getFollows();

    JSONObject addFollow(String code);

    JSONObject addFollow(String username, String code);

    int getFollowNumber(String username);

    String getLastMessage(String code);

    int setMessage(Follow follow);

    JSONObject cancelFollow(String code);

}

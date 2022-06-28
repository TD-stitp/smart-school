package com.td.smartschool.admin.service;

import com.td.smartschool.admin.pojo.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月25日 17:25:33
 */
@Service
public interface UserService {

    JSONObject getUsers();

    User getUser(String username);

    JSONObject addUser(User user);

    JSONObject deleteUser(String username);
}

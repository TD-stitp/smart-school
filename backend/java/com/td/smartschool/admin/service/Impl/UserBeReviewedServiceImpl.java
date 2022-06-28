package com.td.smartschool.admin.service.Impl;

import com.td.smartschool.admin.mapper.UserBeReviewedMapper;
import com.td.smartschool.admin.pojo.User;
import com.td.smartschool.admin.service.TableService;
import com.td.smartschool.admin.service.UserBeReviewedService;
import com.td.smartschool.admin.service.UserService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/18 21:38
 */
@Service
@AllArgsConstructor
public class UserBeReviewedServiceImpl implements UserBeReviewedService {

    private final TableService tableService;
    private final UserService userService;
    private final UserBeReviewedMapper beReviewedMapper;

    @Override
    public JSONObject getBeRevieweds() {
        List<User> list = beReviewedMapper.getUsers();
        JSONObject result = new JSONObject();
        JSONArray users = new JSONArray();
        result.put("code", "200");
        result.put("total", list.size());
        for (User user : list) {
            JSONObject temp = new JSONObject();
            temp.put("account", user.getAccount());
            temp.put("name", user.getName());
            temp.put("department", user.getDepartment());
            temp.put("reason", user.getReason());
            users.put(temp);
        }
        result.put("options", users);
        return result;
    }

    @Override
    public User getBeReviewed(String account) {
        return beReviewedMapper.getUser(account);
    }

    @Override
    public JSONObject addBeReviewed(User user) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "申请成功!");
        beReviewedMapper.addUser(user);
        return result;
    }

    @Override
    public JSONObject deleteBeReviewed(String account) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "删除成功!");
        beReviewedMapper.deleteUser(account);
        return result;
    }

    @Override
    public JSONObject agree(String account) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "同意用户" + account + "的权限申请！");
        User user = beReviewedMapper.getUser(account);
        beReviewedMapper.deleteUser(account);
        userService.addUser(user);
        tableService.createChannel(account);
        return result;
    }

    @Override
    public JSONObject disagree(String account) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "拒绝用户" + account + "的权限申请！");
        beReviewedMapper.deleteUser(account);
        return result;
    }
}

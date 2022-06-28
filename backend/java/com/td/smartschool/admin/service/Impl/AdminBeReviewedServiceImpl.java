package com.td.smartschool.admin.service.Impl;

import com.td.smartschool.admin.mapper.BeReviewedMapper;
import com.td.smartschool.admin.pojo.Admin;
import com.td.smartschool.admin.service.AdminBeReviewedService;
import com.td.smartschool.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/16 22:07
 */
@AllArgsConstructor
@Service
public class AdminBeReviewedServiceImpl implements AdminBeReviewedService {

    private final BeReviewedMapper beReviewedMapper;
    private final AdminService adminService;

    @Override
    public JSONObject getBeRevieweds() {
        List<Admin> list = beReviewedMapper.getBeRevieweds();
        JSONObject result = new JSONObject();
        JSONArray admins = new JSONArray();
        result.put("code", "200");
        result.put("total", list.size());
        for (Admin admin : list) {
            JSONObject temp = new JSONObject();
            temp.put("nickname", admin.getNickname());
            temp.put("account", admin.getAccount());
            admins.put(temp);

        }
        result.put("options", admins);
        return result;
    }

    @Override
    public Admin getBeReviewed(String account) {
        return beReviewedMapper.getBeReviewed(account);
    }

    @Override
    public int updateBeReviewed(Admin admin) {
        return beReviewedMapper.updateBeReviewed(admin);
    }

    @Override
    public int deleteBeReviewed(String account) {
        return beReviewedMapper.deleteBeReviewed(account);
    }

    @Override
    public JSONObject addBeReviewed(Admin admin) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "注册成功，请等待管理员审核");
        try {
            if (adminService.getAdmin(admin.getAccount()) != null) {
                result.put("code", "202");
                result.put("msg", "该账号已存在，请勿重复注册");
            } else {
                beReviewedMapper.addBeReviewed(admin);
            }
        } catch (DuplicateKeyException e) {
            result.put("code", "202");
            result.put("msg", "该账号已在注册列表中，请耐心等待管理员审核");
        }
        return result;
    }

    @Override
    public JSONObject agree(String account) {
        JSONObject result = new JSONObject();
        Admin entity = beReviewedMapper.getBeReviewed(account);
        if (entity == null) {
            result.put("code", "202");
            result.put("msg", "账号不存在");
        } else {
            beReviewedMapper.deleteBeReviewed(account);
            adminService.addAdmin(entity);
            result.put("code", "200");
            result.put("msg", "同意" + account + "的管理员账号注册");
        }
        return result;
    }

    @Override
    public JSONObject disagree(String account) {
        JSONObject result = new JSONObject();
        Admin entity = beReviewedMapper.getBeReviewed(account);
        if (entity == null) {
            result.put("code", "202");
            result.put("msg", "账号不存在");
        } else {
            beReviewedMapper.deleteBeReviewed(account);
            result.put("code", "200");
            result.put("msg", "拒绝" + account + "的管理员账号注册");
        }
        return result;
    }
}

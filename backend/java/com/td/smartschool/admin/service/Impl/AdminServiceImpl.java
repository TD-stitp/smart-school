package com.td.smartschool.admin.service.Impl;

import com.td.smartschool.admin.mapper.AdminMapper;
import com.td.smartschool.admin.pojo.Admin;
import com.td.smartschool.admin.pojo.AdminPermission;
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
 * @date 2022/4/15 20:58
 */
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public JSONObject getAdmins() {
        List<Admin> list = adminMapper.getAdmins();
        JSONObject result = new JSONObject();
        JSONArray admins = new JSONArray();
        result.put("code", "200");
        result.put("total", list.size());
        for (Admin admin : list) {
            JSONObject temp = new JSONObject();
            temp.put("nickname", admin.getNickname());
            temp.put("account", admin.getAccount());
            temp.put("permission", admin.getPermission());
            admins.put(temp);

        }
        result.put("options", admins);
        return result;
    }

    @Override
    public Admin getAdmin(String account) {
        return adminMapper.getAdmin(account);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public JSONObject setPermission(String account, AdminPermission permission) {
        JSONObject result = new JSONObject();
        Admin entity = adminMapper.getAdmin(account);
        if (entity == null) {
            result.put("code", "202");
            result.put("msg", "账号不存在");
        } else {
            entity.setPermission(permission);
            adminMapper.updateAdmin(entity);
            result.put("code", "200");
            result.put("msg", "管理员权限修改成功");
        }
        return result;
    }

    @Override
    public JSONObject deleteAdmin(String account) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "删除成功");
        adminMapper.deleteAdmin(account);
        return result;
    }

    @Override
    public JSONObject addAdmin(Admin admin) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "添加成功");
        try {
            adminMapper.addAdmin(admin);
        } catch (DuplicateKeyException e) {
            result.put("code", "202");
            result.put("msg", "请勿添加相同账号");
        }
        return result;
    }
}

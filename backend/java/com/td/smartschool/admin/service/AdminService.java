package com.td.smartschool.admin.service;


import com.td.smartschool.admin.pojo.Admin;
import com.td.smartschool.admin.pojo.AdminPermission;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年4月15日 20:57:21
 */
@Service
public interface AdminService {

    JSONObject getAdmins();

    Admin getAdmin(String account);

    int updateAdmin(Admin admin);

    JSONObject deleteAdmin(String account);

    JSONObject addAdmin(Admin admin);

    JSONObject setPermission(String account, AdminPermission permission);

}

package com.td.smartschool.admin.service;

import com.td.smartschool.admin.pojo.Admin;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/16 22:07
 */
@Service
public interface AdminBeReviewedService {

    JSONObject getBeRevieweds();

    Admin getBeReviewed(String account);

    int updateBeReviewed(Admin admin);

    int deleteBeReviewed(String account);

    JSONObject addBeReviewed(Admin admin);

    JSONObject agree(String account);

    JSONObject disagree(String account);
}

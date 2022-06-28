package com.td.smartschool.admin.service;

import com.td.smartschool.admin.pojo.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年4月18日 21:38:02
 */
@Service
public interface UserBeReviewedService {

    JSONObject getBeRevieweds();

    User getBeReviewed(String account);

    JSONObject addBeReviewed(User user);

    JSONObject deleteBeReviewed(String account);

    JSONObject agree(String account);

    JSONObject disagree(String account);
}

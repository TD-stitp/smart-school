package com.td.smartschool.customer.config;

import com.td.smartschool.customer.service.ChannelService;
import com.td.smartschool.customer.service.FollowService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/3/8 16:28
 */
@Aspect
@Configuration
@AllArgsConstructor
public class AuthorAspect {

    private final ChannelService channelService;
    private final FollowService followService;

    private final String adminExecutePath = "execution(* com.td.smartschool.customer.controller.ChannelController.deleteChannel(String)) ||" +
            "execution(* com.td.smartschool.customer.controller.ChannelController.setChannelName(..)) ||" +
            "execution(* com.td.smartschool.customer.controller.SubscriberController.*(..)) ||" +
            "execution(* com.td.smartschool.customer.controller.MessageController.*(String, String))";

    private final String userExecutePath = "execution(* com.td.smartschool.customer.controller.MessageController.*(String)) ||" +
            "execution(* com.td.smartschool.customer.controller.FollowController.cancelFollow(String))";

    private final JSONObject noAuthor = new JSONObject().put("code", "202")
            .put("msg", "没有权限");
    private Map<String, String> queryMap;

    private void init() {
        queryMap = new HashMap<>(16);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String queryString = request.getQueryString();
        String[] strArray = queryString.split("&");
        for (String s : strArray) {
            String[] temp = s.split("=");
            queryMap.put(temp[0], temp[1]);
        }
    }

    private Object execution(ProceedingJoinPoint joinPoint, JSONObject json, String type) {
        if (json.getInt("total") != 0) {
            JSONArray arrays = json.getJSONArray(type);
            if (arrays.isEmpty()) {
                return noAuthor.toString();
            } else {
                for (int index = 0; index < arrays.length(); index++) {
                    JSONObject tempJson = arrays.getJSONObject(index);
                    if (tempJson.getString("code").equalsIgnoreCase(queryMap.get("code"))) {
                        Object result = null;
                        try {
                            result = joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                        return result;
                    }
                }
            }
        }
        return noAuthor.toString();
    }

    @Around(adminExecutePath)
    public Object doAdminAuthorization(ProceedingJoinPoint joinPoint) {
        init();
        JSONObject channelJson = channelService.getChannels();
        return execution(joinPoint, channelJson, "channels");
    }

    @Around(userExecutePath)
    public Object doUserAuthorization(ProceedingJoinPoint joinPoint) {
        init();
        JSONObject followJson = followService.getFollows();
        return execution(joinPoint, followJson, "follows");
    }
}

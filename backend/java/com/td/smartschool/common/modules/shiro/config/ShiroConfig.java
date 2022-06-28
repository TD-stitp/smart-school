package com.td.smartschool.common.modules.shiro.config;

import com.td.smartschool.common.modules.shiro.entity.AdminRealm;
import com.td.smartschool.common.modules.shiro.entity.UserRealm;
import com.td.smartschool.common.modules.shiro.multiple.CustomizedModularRealmAuthenticator;
import com.td.smartschool.common.modules.shiro.service.impl.RoleOrFilter;
import com.td.smartschool.common.modules.shiro.session.ShiroSession;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2021/9/22 17:33
 */

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    @Autowired
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManger) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManger);
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("e-perms", new RoleOrFilter());
        shiroFilter.setFilters(filtersMap);

        // 登录权限管理
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 后台管理系统
        filterMap.put("/api/admin/login", "anon");
        filterMap.put("/api/admin/logout", "authc");
        filterMap.put("/api/admin/**", "authc,e-perms[admin:all]");
        filterMap.put("/api/image/**", "authc,e-perms[admin:all|banner]");
        filterMap.put("/api/user/**", "authc,e-perms[admin:all|channel]");

        // 教务系统API拦截
        filterMap.put("/api/education/toLogin", "anon");
        filterMap.put("/api/education/login", "anon");
        filterMap.put("/api/education/**", "authc");
        // 一卡通系统API拦截
        filterMap.put("/api/electricity/toLogin", "anon");
        filterMap.put("/api/electricity/getValidateCode", "anon");
        filterMap.put("/api/electricity/getRSAKey", "anon");
        filterMap.put("/api/electricity/login", "authc");
        filterMap.put("/api/electricity/**", "authc,perms[electricity:login]");
        // 订阅系统API拦截
        filterMap.put("/api/subscriber/createChannel", "authc,perms[anchor:admin]");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        shiroFilter.setLoginUrl("/");
        return shiroFilter;
    }

    @Bean
    public CustomizedModularRealmAuthenticator authenticator() {
        CustomizedModularRealmAuthenticator authenticator = new CustomizedModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    // DefaultWebSecurityManger  第二步
    @Bean
    @Autowired
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomizedModularRealmAuthenticator authenticator, List<Realm> realms) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator);
        securityManager.setRealms(realms);
        return securityManager;
    }

    //创建 realm对象 需要自定义类  第一步
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public AdminRealm adminRealm() {
        return new AdminRealm();
    }

    /**
     * 自定义的 shiro session 缓存管理器，用于跨域等情况下使用 token 进行验证，不依赖于sessionId
     */
    @Bean
    public SessionManager sessionManager() {
        //将我们继承后重写的shiro session 注册
        ShiroSession shiroSession = new ShiroSession();
        //如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡

        shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
        return shiroSession;
    }

}

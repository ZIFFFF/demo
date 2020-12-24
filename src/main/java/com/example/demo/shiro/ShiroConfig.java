package com.example.demo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/***
 * Shiro核心API
 * Subject: 用户主体（把操作交给SecurityManager）
 * SecurityManager：安全管理器（关联Realm）
 * Realm：Shiro连接数据的桥梁
 */

@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *      anon：无需认证（登录）可以访问
         *      authc：必须认证才可以访问登录
         *      user：如果使用rememberMe的功能可以直接访问
         *      perms：该资源必须得到资源权限才可以访问
         *      role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/software/**", "authc");
        filterMap.put("/user/**", "authc");
        filterMap.put("/index", "authc");
        filterMap.put("/", "anon");
        filterMap.put("/logoutAction", "logout");
        //授权过滤器
        filterMap.put("/user/add", "perms[权限添加]");

        //修改error页面
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        System.out.println("shiro拦截器工厂注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }

}

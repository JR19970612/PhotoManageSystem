package com.ydb.security.config.authencationconfig;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @date:2018/12/22
 */

@Component
public class AuthorizeConfig {
    public void configure(HttpSecurity builder) throws Exception {
        builder.authorizeRequests()
                .antMatchers("/", "/login.html", "/loginPerson").permitAll()//首页和登陆页不进行认证拦截
                .antMatchers(HttpMethod.POST, "/person").permitAll()
                .antMatchers("/client/**").anonymous()//图片浏览页面任何用户都可进行访问
                .antMatchers("/manage/User/**").hasRole("SuperAdmin")//管理员管理资源只有超级管理员才拥有权限
                .anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }
}
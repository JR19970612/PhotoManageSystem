package com.ydb.config.security;

import com.ydb.config.security.fomauthentication.FormAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: SpringSecurity配置
 * @date:2018/12/21
 */
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置表单登陆
        formAuthenticationConfig.configure(http);
        //关闭csrf
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .authorities("ROLE_ADMIN");
    }
}

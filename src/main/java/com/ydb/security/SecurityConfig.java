package com.ydb.security;

import com.ydb.security.config.authencationconfig.AuthorizeConfig;
import com.ydb.security.config.formconfig.FormAuthenticationConfig;
import com.ydb.service.imp.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: SpringSecurity配置
 * @date:2018/12/21
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    AuthorizeConfig authorizeConfig;

    @Autowired
    AdminDetailsService adminDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http//csrf跨域访问配置
                .csrf()
                .disable()//关闭csrf
                //访问请求头配置
                .headers().disable()
//                .frameOptions().disable()//关闭拦截嵌入式网页
                //退出配置
                .logout()
                .logoutUrl("/signOut")//退出拦截器处理的退出url
                .logoutSuccessUrl("/client/index.html")//退出后重定向的页面
                .and();
        //配置表单登陆
        formAuthenticationConfig.configure(http);
        //权限配置
        authorizeConfig.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //对静态资源进行放行
        web.
                ignoring()
                .mvcMatchers("/favicon/**")
                .and();
    }

    /**
     * 配置认证管理器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(adminDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}

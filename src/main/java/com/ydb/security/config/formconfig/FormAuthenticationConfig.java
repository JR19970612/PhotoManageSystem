package com.ydb.security.config.formconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 表单登录配置
 * @date:2018/12/22
 */
@Component
public class FormAuthenticationConfig {

    @Autowired
    FormAuthenticationSuccessHandler successHandler;

    @Autowired
    FormAuthenctiationFailureHandler failureHandler;

    public void configure(HttpSecurity builder) throws Exception {
        builder.formLogin()
                .loginPage("/client/index.html")//登陆页面，若登陆失败后将默认跳转到该指定的页面和未进行身份认证的页面也将跳转于此
                .loginProcessingUrl("/login")///Form表单登陆拦截器处理的退出url，当用form进行登陆时，信息提交的url必须是该指定的url
                .usernameParameter("personName")
                .passwordParameter("personPassword")
                .successHandler(successHandler)//登陆成功处理器
                .failureHandler(failureHandler);//登陆失败处理器
    }
}

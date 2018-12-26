package com.ydb.security.config.authencationconfig;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

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
                .antMatchers("/", "/login.html", "/client/**", "/originalphoto/**", "/thumphoto/**").permitAll()//首页和登陆页、图片浏览页面不进行认证拦截，任何用户都可进行访问
                //开放接口
                .antMatchers(HttpMethod.GET, "/album/**", "/albums", "/photo/**").permitAll()
                .antMatchers(HttpMethod.POST, "/loginPerson", "/comment").permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/loginPerson", "/comment").permitAll()//授权上面跨域请求的OPTIONS预请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()//该设置表示授权所有POST请求通发送OPTIONS预请求进行授权
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html").hasAuthority("SuperAdmin")//Swagger接口测试只对超级管理员开通
                .antMatchers("/manage/assets/**", "/manage/Css/**", "/manage/front/**", "/manage/Images/**", "/manage/Js/**", "/manage/allvariable.js").hasAnyAuthority("SuperAdmin", "Admin")//后台公共静态资源，也就是超级管理员和管理员都能访问的资源
                .antMatchers("/manage/user.html", "/manage/User/**").hasAuthority("SuperAdmin")//管理员管理资源只有超级管理员才拥有权限
                .antMatchers("/manage/photo.html", "/manage/Album/**", "/manage/Comment/**", "/manage/Photo/**").hasAuthority("Admin")//相册管理资源只有管理员才拥有权限
                .anyRequest().access("@rbacService.hasPermission(request, authentication)");//其它接口采用RBAC数据模型控制
    }
}
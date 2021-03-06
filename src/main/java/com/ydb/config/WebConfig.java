package com.ydb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.aspect
 * @date:2018/12/16
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {//注意！！！如果这里集成WebMvcConfigurerSupport的话，接下来的配置将会覆盖父类的方法配置，比如这里配置资源索引话，Springboot默认的资源索引路径（/static、/resource...）将不起作用，


    //映射静态资源本地寻找路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/originalphoto/**").addResourceLocations("file:E:/Java/WorkPlace/PhotoManageSystem/image/originalphoto/");
        registry.addResourceHandler("/thumphoto/**").addResourceLocations("file:E:/Java/WorkPlace/PhotoManageSystem/image/thumphoto/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/client/index.html");
        super.addViewControllers(registry);
    }

    //配置跨域权限
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/loginPerson")//允许跨域的接口
                .allowedOrigins("*")//允许访问的站点
                .allowCredentials(true)
                .allowedMethods("POST", "PATCH")//支持跨域的请求方法
                .maxAge(3600);
        super.addCorsMappings(registry);
    }

    @Bean
    public RedirectView redirectPhotoView() {
        return new RedirectView("/gdpi/manage/Photo/index.html");
    }

    @Bean
    public RedirectView redirectAlbumView() {
        return new RedirectView("/gdpi/manage/Album/index.html");
    }

    @Bean
    public RedirectView redirectUserView() {
        return new RedirectView("/gdpi/manage/User/index.html");
    }

}

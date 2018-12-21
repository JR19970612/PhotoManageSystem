package com.ydb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/originalphoto/**").addResourceLocations("file:D://originalphoto/");
        registry.addResourceHandler("/thumphoto/**").addResourceLocations("file:D://thumphoto/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }

    @Bean
    public RedirectView redirectView() {
        return new RedirectView("http://127.0.0.1:8848/后台模版/index.html/2/9");
    }
}

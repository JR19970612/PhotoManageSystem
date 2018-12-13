package com.ydb.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/originalphoto/**").addResourceLocations("file:D://originalphoto/");
        registry.addResourceHandler("/thumphoto/**").addResourceLocations("file:D://thumphoto/");
        registry.addResourceHandler("/*.*").addResourceLocations("classpath:/static/", "classpath:/public/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }
}

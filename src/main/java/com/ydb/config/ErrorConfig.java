package com.ydb.config;

import com.ydb.bean.ResultBean;
import com.ydb.controller.PmyErrorController;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.config
 * @date:2018/12/21
 */
@Configuration
public class ErrorConfig {
    private final ServerProperties serverProperties;

    private final List<ErrorViewResolver> errorViewResolvers;

    public ErrorConfig(ServerProperties serverProperties,
                       ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
    }


    private class ErrorAttributes extends DefaultErrorAttributes {
        public static final String ERROR_ATTRIBUTE = "ERROR_BEAN";

        @Override
        public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
            ResultBean errorBean = (ResultBean) requestAttributes.getAttribute(ERROR_ATTRIBUTE, 0);
            Map<String, Object> body = new HashMap<>();
            body.put("error", errorBean);
            return body;
        }
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new ErrorAttributes();
    }

    @Bean
    public ErrorController errorController(org.springframework.boot.autoconfigure.web.ErrorAttributes errorAttributes) {
        return new PmyErrorController(errorAttributes, this.serverProperties.getError(),
                this.errorViewResolvers);
    }
}

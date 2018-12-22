/**
 *
 */
package com.ydb.config.security.fomauthentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 浏览器环境下登录成功的处理器
 * @date:2018/12/22
 */
@Component("formAuthenticationSuccessHandler")
public class FormAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");
        String accept = request.getHeader("Accept");
        //根据appcet响应头判断客户端是浏览器还是移动端，在进行指定响应动作
        if (accept != null & accept.contains("text/html")) {
            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            response.setContentType("application/json;charset=UTF-8");
            String type = authentication.getClass().getSimpleName();
            response.getWriter().write(type);
        }
    }
}

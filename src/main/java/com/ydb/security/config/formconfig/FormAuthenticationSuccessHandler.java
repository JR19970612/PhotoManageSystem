/**
 *
 */
package com.ydb.security.config.formconfig;

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
        if (accept != null & accept.contains("text/html")) {//若是浏览器端则重定向到指定页面
            setAlwaysUseDefaultTargetUrl(true);//是否每次登陆成功都重定向到下面设置的目标路径
            setDefaultTargetUrl("/manage/index.html");//登陆成功后重定向的目标路径
            super.onAuthenticationSuccess(request, response, authentication);//默认情况下，若不配置以上两个方法，该方法会将成功之后的请求转发到之前访问的路径。比如：我现在访问/preson，但被认证拦截了，当我登陆认证成功后，将继续访问/perosn
        } else {//若是移动的端登陆访问则返回成功消息JSON数据
            response.setContentType("application/json;charset=UTF-8");
            String type = authentication.getClass().getSimpleName();
            response.getWriter().write(type);
        }
    }
}

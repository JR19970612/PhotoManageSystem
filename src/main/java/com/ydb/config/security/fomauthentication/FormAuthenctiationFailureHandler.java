/**
 *
 */
package com.ydb.config.security.fomauthentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author: create by JR
 * @version: v1.0
 * @description: 浏览器环境下登录失败的处理器
 * @date:2018/12/22
 */
@Component("formAuthenctiationFailureHandler")
public class FormAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        String accept = request.getHeader("Accept");
        //根据appcet响应头判断客户端是浏览器还是移动端，在进行指定响应动作
        if (accept != null & accept.contains("text/html")) {
            //重新跳转到登陆页面
            super.onAuthenticationFailure(request, response, exception);
        } else {
            //返回Json错误信息
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(exception.getMessage());
        }
    }
}

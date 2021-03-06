package com.ydb.controller;

import com.ydb.bean.ResultBean;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 全局异常信息返回适配器处理器
 * @date:2018/12/16
 */

@RequestMapping("/error")
public class PmyErrorController extends AbstractErrorController {

    private final ErrorProperties errorProperties;

    public PmyErrorController(ErrorAttributes errorAttributes,
                              ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        this.errorProperties = errorProperties;
    }

    //响应浏览器客户端请求
    @RequestMapping(produces = "text/html")
    public void errorHtml(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        //不同状态码进行不同响应
        if ("413、415、417、417".contains(String.valueOf(status.value()))) {
            response.getWriter().print((ResultBean)model.get("error"));
        } else {
            //根据状态码选择合适的异常页面
            ModelAndView modelAndView = resolveErrorView(request, response, status, model);
            response.sendRedirect(modelAndView.getViewName());
        }
    }

    //响应手机客户端请求
    @RequestMapping
    @ResponseBody
    public ResponseEntity<ResultBean> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
//        response.setStatus(status.value());
        return new ResponseEntity<ResultBean>(
                (ResultBean) body.get("error"), status);
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }


    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

}
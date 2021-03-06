package com.ydb.interceptor;

import com.ydb.bean.ExceptionBean;
import com.ydb.bean.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 全局异常处理
 * @date:2018/12/16
 */
@ControllerAdvice
public class ExceptionInterceptor {
    Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @ExceptionHandler({Exception.class})
    public String handlerException(Exception e, HttpServletRequest request) {
        //控制台打印异常信息
        e.printStackTrace();
        //封装异常信息
        ResultBean exceptionResult = new ResultBean();
        initException(e, request, exceptionResult);
        //将异常信息存放到reqeust域中，供异常处理器处理
        request.setAttribute("ERROR_BEAN", exceptionResult);
        //转发到/error(自定义异常处理器:PmyErrorController)
        return "forward:/error";
    }

    public void initException(Exception e, HttpServletRequest request, ResultBean resultBean) {
        String msg;
        int status;
        switch (e.getClass().getSimpleName()) {
            case "MultipartException": {
                status = 413;
                msg = "上传文件大小超出界限";
                break;
            }
            case "FomatTypeException": {
                status = 415;
                msg = "请重新确认上传文件";
                break;
            }
            case "DataIntegrityViolationException": {
                status = 417;
                msg = "请求确认上传参数";
                break;
            }
            case "DuplicateKeyException": {
                status = 417;
                msg = "请求确认上传参数";
                break;
            }
            case "BindException": {
                status = 417;
                msg = "请求确认上传参数";
                break;
            }
            default: {
                status = 500;
                msg = "服务器异常";
            }
        }
        request.setAttribute("javax.servlet.error.status_code", status);
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        String errorMsg = "文件名：" + stackTraceElement.getFileName() +
                "\r\n类名：" + stackTraceElement.getClassName() +
                "\r\n方法名：" + stackTraceElement.getMethodName() +
                "\r\n抛出异常行号：" + stackTraceElement.getLineNumber();
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setExceptionName(e.getClass().getName());
        exceptionBean.setTime(new Date().toString());
        exceptionBean.setExcetionMessage(errorMsg);
        resultBean.setException(exceptionBean);
        resultBean.setStatus(status);
        resultBean.setMsg(msg);
    }
}

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
        int status = ResultBean.FAILURE_CODE;
        String msg = "服务器异常";
        switch (e.getClass().getSimpleName()) {
            case "BindException": {
                status = ResultBean.BINDEXCEPTION_CODE;
                msg = "数据校验异常";
                break;
            }
            case "ParamsException": {
                status = ResultBean.PARAMSEXCPEITON_CODE;
                msg = "参数异常";
                break;
            }
            case "MultipartException": {
                status = ResultBean.UPLOADFILESIEZEXPTION_CODE;
                msg = "上传文件大小超出界限";
                break;
            }
            case "FomatTypeException": {
                status = ResultBean.FILEFOMATTYPEEZEXPTION_CODE;
                msg = "不支持该格式文件上传";
                break;
            }
            case "DuplicateKeyException": {
                status = ResultBean.DAOEXCEPTION;
                msg = "请求确认上传参数";
                break;
            }
        }
        request.setAttribute("javax.servlet.error.status_code", 400);
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

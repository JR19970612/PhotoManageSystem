package com.ydb.interceptor;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ExceptionBean;
import com.ydb.bean.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 全局异常处理
 */
@ControllerAdvice(basePackages = "com.ydb.controller")
@ResponseBody
public class ExceptionInterceptor {
    Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @ExceptionHandler({Exception.class})
    @JsonView(ResultBean.ExceptionView.class)
    public ResultBean handlerException(Exception e) {
        //控制台打印异常信息
        logger.error(e.getMessage(), e.getCause());
        ResultBean exceptionResult = new ResultBean();
        //判断异常类型
        judgeException(e, exceptionResult);
        //封装异常信息
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setExceptionName(e.getClass().getName());
        exceptionBean.setTime(new Date().toString());
        exceptionBean.setExcetionMessage(e.getMessage());
        exceptionResult.setException(exceptionBean);
        return exceptionResult;
    }

    public void judgeException(Exception e, ResultBean resultBean) {
        int status = ResultBean.FAILURE_CODE;
        String msg = "服务器异常";
        switch (e.getClass().getSimpleName()) {
            case "BindException": {
                status = ResultBean.BINDEXCEPTION_CODE;
                msg = "数据校验异常";
                break;
            }
        }
        resultBean.setStatus(status);
        resultBean.setMsg(msg);
    }
}

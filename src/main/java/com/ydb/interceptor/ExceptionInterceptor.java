package com.ydb.interceptor;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ExceptionBean;
import com.ydb.bean.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

/**
 * 全局异常处理
 */
@ControllerAdvice(basePackages = "com.ydb.controller")
@ResponseBody
public class ExceptionInterceptor {

    @ExceptionHandler({Exception.class})
    @JsonView(ResultBean.ExceptionView.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultBean handlerException(Exception e) {
        e.printStackTrace();
        ResultBean exceptionResult = new ResultBean();
        exceptionResult.setStatus(ResultBean.FAILURE_CODE);
        exceptionResult.setMsg("服务器异常");
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setExceptionName(e.getClass().getName());
        exceptionBean.setTime(new Date().toString());
        exceptionBean.setExcetionMessage(e.getMessage());
        exceptionResult.setException(exceptionBean);
        return exceptionResult;
    }
}

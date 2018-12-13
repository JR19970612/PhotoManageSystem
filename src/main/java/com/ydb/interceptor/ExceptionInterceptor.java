package com.ydb.interceptor;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.ExceptionView;
import com.ydb.bean.ExceptionBean;
import com.ydb.bean.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 全局异常处理
 */
@ControllerAdvice(basePackages = {"com.ydb.controller"})
@ResponseBody
public class ExceptionInterceptor {
    Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @ExceptionHandler({Exception.class})
    @JsonView(ExceptionView.class)
    public ResultBean handlerException(Exception e, HttpServletResponse response) {
        //控制台打印异常信息
        e.printStackTrace();
        logger.error(e.getMessage(), e.getCause());
        ResultBean exceptionResult = new ResultBean();
        //判断异常类型
        judgeException(e, response, exceptionResult);
        //封装异常信息
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setExceptionName(e.getClass().getName());
        exceptionBean.setTime(new Date().toString());
        exceptionBean.setExcetionMessage(e.getMessage());
        exceptionResult.setException(exceptionBean);
        return exceptionResult;
    }

    public void judgeException(Exception e, HttpServletResponse response, ResultBean resultBean) {
        int status = ResultBean.FAILURE_CODE;
        String msg = "服务器异常";
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        switch (e.getClass().getSimpleName()) {
            case "BindException": {
                status = ResultBean.BINDEXCEPTION_CODE;
                msg = "数据校验异常";
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            }
            case "ParamsException": {
                status = ResultBean.PARAMSEXCPEITON_CODE;
                msg = "参数异常";
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            }
            case "MultipartException": {
                status = ResultBean.UPLOADFILESIEZEXPTION_CODE;
                msg = "上传文件大小超出界限";
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            }
            case "FomatTypeException": {
                status = ResultBean.FILEFOMATTYPEEZEXPTION_CODE;
                msg = "不支持该格式文件上传";
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            }
            case "DuplicateKeyException": {
                status = ResultBean.DAOEXCEPTION;
                msg = "请求确认上传参数";
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            }
        }
        resultBean.setStatus(status);
        resultBean.setMsg(msg);
    }
}

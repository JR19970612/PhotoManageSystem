package com.ydb.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.BaseView;
import com.ydb.JsonView.ExceptionView;
import com.ydb.JsonView.SuccessView;

import java.util.List;
/**
 * @author: create by JR
 * @version: v1.0
 * @description: api接口数据返回封装Bean
 * @date:2018/12/16
 */

public class ResultBean<E> {
    /**
     * 状态码定义
     */
    public static final Integer FAILURE_CODE = -1;//失败
    public static final Integer SUCCSSED_CODE = 0;//成功
    public static final Integer BINDEXCEPTION_CODE = 1;//数据校验异常
    public static final Integer PARAMSEXCPEITON_CODE = 2;//携带参数异常
    public static final Integer UPLOADFILESIEZEXPTION_CODE = 3;//上传文件超出界限
    public static final Integer FILEFOMATTYPEEZEXPTION_CODE = 4;
    public static final Integer DAOEXCEPTION = 5;

    /**
     * 定义返回字段
     */
    @JsonView(value = {BaseView.class})
    private Integer status;
    @JsonView(value = {BaseView.class})
    private String msg;
    @JsonView(value = SuccessView.class)
    private List<E> data;
    @JsonView(value = ExceptionView.class)
    private ExceptionBean exception;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }


    public ExceptionBean getException() {
        return exception;
    }

    public void setException(ExceptionBean exception) {
        this.exception = exception;
    }
}

package com.ydb.bean;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * api接口数据返回封装Bean
 */
public class ResultBean<E> {
    /**
     * 状态码定义
     */
    public static final Integer SUCCSSED_CODE = 0;//成功
    public static final Integer FAILURE_CODE = -1;//失败


    /**
     * 定义@JsonView接口
     */

    public interface SuccessView {
    }

    public interface ExceptionView {
    }

    /**
     * 定义返回字段
     */
    @JsonView(value = {SuccessView.class, ExceptionView.class})
    private Integer status;
    @JsonView(value = {SuccessView.class, ExceptionView.class})
    private String msg;
    @JsonView(value = SuccessView.class)
    private List<E> data;
    @JsonView(value = ExceptionView.class)
    private ExceptionBean exception ;

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

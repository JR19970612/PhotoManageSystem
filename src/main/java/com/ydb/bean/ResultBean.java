package com.ydb.bean;

import java.util.List;

public class ResultBean<E> {
    /**
     * 状态码定义
     */
    public static final Integer SUCCSSED_CODE = 0;//成功
    public static final Integer FAILURE_CODE = -1;//失败

    private Integer status;
    private String msg;
    private List<E> data;

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
}

package com.ydb.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.ExceptionView;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 异常信息封装Bean
 * @date:2018/12/16
 */
public class ExceptionBean {
    @JsonView(ExceptionView.class)
    private String exceptionName;
    @JsonView(ExceptionView.class)
    private String time;
    @JsonView(ExceptionView.class)
    private String excetionMessage;

    public ExceptionBean() {
    }

    public ExceptionBean(String exceptionName, String time, String excetionMessage) {
        this.exceptionName = exceptionName;
        this.time = time;
        this.excetionMessage = excetionMessage;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExcetionMessage() {
        return excetionMessage;
    }

    public void setExcetionMessage(String excetionMessage) {
        this.excetionMessage = excetionMessage;
    }

    @Override
    public String toString() {
        return "ExceptionBean{" +
                "exceptionName='" + exceptionName + '\'' +
                ", time='" + time + '\'' +
                ", excetionMessage='" + excetionMessage + '\'' +
                '}';
    }
}

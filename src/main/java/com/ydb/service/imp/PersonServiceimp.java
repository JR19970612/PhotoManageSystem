package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.IPersonDao;
import com.ydb.entity.Person;
import com.ydb.service.IPersonService;
import com.ydb.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: com.ydb.dao
 * @description: PersonServiceimp
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
@Service
public class PersonServiceimp implements IPersonService {

    @Autowired
    private IPersonDao mapper;


    @Override
    public ResultBean<Person> insertPerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        person.setPersonPassword(MD5Util.encode(person.getPersonPassword()));//MD5加密
        //TODO 设置用户默认头像
        resultBean.setData(Arrays.asList(person));
        int code = mapper.insertPerson(person);
        initResultBean(code, resultBean);
        return resultBean;
    }

    @Override
    public ResultBean<List<Person>> queryPersons() {
        List<Person> persons = mapper.queryPersons();
        ResultBean<List<Person>> resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(Collections.singletonList(persons));
        return resultBean;
    }


    @Override
    public ResultBean<Person> queryPerson(Integer personId) {
        Person person = mapper.queryPerson(personId);
        ResultBean<Person> resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(Collections.singletonList(person));
        return resultBean;
    }

    @Override
    public ResultBean<Person> deletePerson(Integer personId) {
        Person person = new Person();
        person.setPersonId(personId);
        int code = mapper.deletePerson(person);
        ResultBean<Person> resultBean = new ResultBean<>();
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList());
        return resultBean;
    }


    @Override
    public ResultBean<Person> updatePerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        person.setPersonPassword(MD5Util.encode(person.getPersonPassword()));//MD5加密
        int code = mapper.updatePerson(person);
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList(person));
        return resultBean;
    }

    @Override
    public ResultBean<Person> loginPerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        Person loginPerson = new Person();
        if (person.getPersonPassword() != null || person.getPersonPassword() != "" || person.getPersonPassword().length() > 0) {
            person.setPersonPassword(MD5Util.encode(person.getPersonPassword()));//MD5加密
        }
        if (person.getPersonId() != null || person.getPersonName() != null) {
            System.out.print("getPersonId:" + person.getPersonId() + ",getPersonName:" + person.getPersonName());
            loginPerson = mapper.loginPerson(person);
        } else {
            loginPerson = null;
        }
        if (loginPerson == null) {
            resultBean.setMsg("登陆失败");
        } else {
            resultBean.setMsg("登陆成功");
        }
        resultBean.setData(Arrays.asList(person));
        return resultBean;

    }

    private void initResultBean(int code, ResultBean resultBean) {
        if (code > 0) {
            //插入数据成功
            resultBean.setStatus(ResultBean.SUCCSSED_CODE);
            resultBean.setMsg("操作成功");
        } else {
            resultBean.setStatus(ResultBean.FAILURE_CODE);
            resultBean.setMsg("操作失败");
        }
    }
}

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
//    @Qualifier(value = "personDao")
    private IPersonDao mapper;

    ResultBean<Person> PersonResultBean;

    ResultBean<List<Person>> PersonResultList;
    Person loginPerson;

    @Override
    public ResultBean<Person> insertPerson(Person person) {
        PersonResultBean = new ResultBean<>();
        PersonResultBean.setStatus(ResultBean.SUCCSSED_CODE);
        person.setPersonPassword(MD5Util.encode(person.getPersonPassword()));//MD5加密
        PersonResultBean.setData(Arrays.asList(person));
        int code = mapper.insertPerson(person);
        if (code == 1) {
            PersonResultBean.setMsg("添加成功");
        } else {
            //删除不存在的id
            PersonResultBean.setMsg("添加失败");
        }
        return PersonResultBean;
    }

    @Override
    public ResultBean<List<Person>> queryPersons() {
        PersonResultList = new ResultBean<List<Person>>();
        PersonResultList.setStatus(ResultBean.SUCCSSED_CODE);
        PersonResultList.setMsg("查询成功");
        List<Person> persons = mapper.queryPersons();
        PersonResultList.setData(Collections.singletonList(persons));
        return PersonResultList;
    }


    @Override
    public ResultBean<Person> queryPerson(Integer personId) {
        PersonResultBean = new ResultBean<>();
        PersonResultBean.setStatus(ResultBean.SUCCSSED_CODE);
        PersonResultBean.setMsg("查询成功");
        Person person = mapper.queryPerson(personId);
        PersonResultBean.setData(Collections.singletonList(person));
        return PersonResultBean;
    }

    @Override
    public ResultBean<Person> deletePerson(Integer personId) {
        PersonResultBean = new ResultBean<>();
        PersonResultBean.setStatus(ResultBean.SUCCSSED_CODE);

        int code = mapper.deletePerson(personId);
        if (code == 1) {
            PersonResultBean.setMsg("删除成功");
        } else {
            //删除不存在的id
            PersonResultBean.setMsg("删除失败");
        }
        PersonResultBean.setData(null);
        return PersonResultBean;
    }


    @Override
    public ResultBean<Person> updatePerson(Person person) {
        PersonResultBean = new ResultBean<>();
        PersonResultBean.setStatus(ResultBean.SUCCSSED_CODE);
        person.setPersonPassword(MD5Util.encode(person.getPersonPassword()));//MD5加密

        int code = mapper.updatePerson(person);

        if (code == 1) {
            PersonResultBean.setMsg("修改成功");

        } else {
            PersonResultBean.setMsg("修改失败");
        }
        PersonResultBean.setData(Collections.singletonList(person));
        return PersonResultBean;
    }

    @Override
    public ResultBean<Person> loginPerson(Person person) {

        PersonResultBean = new ResultBean();
        PersonResultBean.setStatus(ResultBean.SUCCSSED_CODE);
        loginPerson = new Person();
        if (person.getPersonPassword() != null || person.getPersonPassword() != "" || person.getPersonPassword().length() > 0) {
            person.setPersonPassword(MD5Util.encode(person.getPersonPassword()));//MD5加密
        }

        if (person.getPersonId() != null || person.getPersonName() != null) {
            System.out.print("getPersonId:" + person.getPersonId() + ",getPersonName:" + person.getPersonName());
            loginPerson = mapper.loginPerson(person);
        } else {
            loginPerson = null;
        }
//        Person loginPerson = mapper.loginPerson(person);
        if (loginPerson == null) {
            PersonResultBean.setMsg("登陆失败");
        } else {
            PersonResultBean.setMsg("登陆成功");
        }
        PersonResultBean.setData(Collections.singletonList(loginPerson));
//        PersonResultBean.setData();
        return PersonResultBean;

    }
}

package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.IPersonDao;
import com.ydb.entity.Person;
import com.ydb.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @program: com.ydb.dao
 * @description: PersonServiceimp
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
@Service
public class PersonServiceimp implements IPersonService {

    @Autowired
    private IPersonDao personDao;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResultBean<Person> register(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        if (person.getPersonAvatarUrl() == null) {
            // 设置用户默认头像
            Random random = new Random();
            person.setPersonAvatarUrl("http://47.106.147.248/gdpi/favicon/" + random.nextInt(16) + ".bmp");
        }
        int code = personDao.insertPerson(person);
        //设置用户匿名角色
        personDao.assigningRoles(3, person.getPersonId());
        resultBean.setData(Arrays.asList(person));
        initResultBean(code, resultBean);
        return resultBean;
    }

    @Transactional
    @Override
    public ResultBean<Person> insertPerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        person.setPersonPassword(bCryptPasswordEncoder.encode(person.getPersonPassword()));
        if (person.getPersonAvatarUrl() == null) {
            // 设置用户默认头像
            Random random = new Random();
            person.setPersonAvatarUrl("http://47.106.147.248/gdpi/favicon/" + random.nextInt(16) + ".bmp");
        }
        int code = personDao.insertPerson(person);
        //设置用户管理员角色
        personDao.assigningRoles(2, person.getPersonId());
        resultBean.setData(Arrays.asList(person));
        initResultBean(code, resultBean);
        return resultBean;
    }

    @Override
    public ResultBean<Person> queryPersons() {
        List<Person> persons = personDao.queryPersons();
        for (int index = 0; index < persons.size(); index++) {//移除微信用户
            Person person = persons.get(index);
            if (person.getPersonName().equals("Admin") || person.getOpenId() != null) {
                persons.remove(person);
            }
        }
        ResultBean<Person> resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(persons);
        return resultBean;
    }


    @Override
    public ResultBean<Person> queryPerson(String personName) {
        Person person = personDao.findPersonByUserName(personName);
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
        int code = personDao.deletePerson(person);
        ResultBean<Person> resultBean = new ResultBean<>();
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList());
        return resultBean;
    }


    @Override
    public ResultBean<Person> updatePerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        if (person.getPassword() != null) {
            person.setPersonPassword(bCryptPasswordEncoder.encode(person.getPersonPassword()));
        }
        int code = personDao.updatePerson(person);
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList(person));
        return resultBean;
    }

    @Override
    public ResultBean<Person> loginPerson(String openId) {
        ResultBean<Person> resultBean = new ResultBean<>();
        Person loginPerson = personDao.findPersonByOpenId(openId);
        if (loginPerson == null) {
            initResultBean(1, resultBean);
        } else {
            initResultBean(0, resultBean);
        }
        resultBean.setData(Arrays.asList(loginPerson));
        return resultBean;
    }

    private void initResultBean(int code, ResultBean resultBean) {
        if (code > 0) {
            resultBean.setStatus(ResultBean.SUCCSSED_CODE);
            resultBean.setMsg("操作成功");
        } else {
            resultBean.setStatus(ResultBean.FAILURE_CODE);
            resultBean.setMsg("操作失败");
        }
    }
}

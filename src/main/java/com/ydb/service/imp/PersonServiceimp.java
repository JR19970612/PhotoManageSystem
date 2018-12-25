package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.IPersonDao;
import com.ydb.entity.Person;
import com.ydb.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    private IPersonDao mapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResultBean<Person> register(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        if (person.getPersonAvatarUrl() == null) {
            //TODO 设置用户默认头像
            Random random = new Random();
            person.setPersonAvatarUrl("http://localhost:8080/gdpi/favicon/" + random.nextInt(16) + ".bmp");
        }
        int code = mapper.insertPerson(person);
        resultBean.setData(Arrays.asList(person));
        initResultBean(code, resultBean);
        return resultBean;
    }

    @Override
    public ResultBean<Person> insertPerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        person.setPersonPassword(bCryptPasswordEncoder.encode(person.getPersonPassword()));
        //TODO 设置用户默认头像
        Random random = new Random();
        person.setPersonAvatarUrl("http://localhost:8080/gdpi/favicon/" + random.nextInt(16) + ".bmp");
        int code = mapper.insertPerson(person);
        resultBean.setData(Arrays.asList(person));
        initResultBean(code, resultBean);
        return resultBean;
    }

    @Override
    public ResultBean<Person> queryPersons() {
        List<Person> persons = mapper.queryPersons();
        for (int i = 0; i < persons.size(); i++) {
           Person person=persons.get(i);
            if (person.getOpenId() != null) {
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
        Person person = mapper.queryPersonByName(personName);
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
        person.setPersonPassword(bCryptPasswordEncoder.encode(person.getPersonPassword()));
        int code = mapper.updatePerson(person);
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList(person));
        return resultBean;
    }

    @Override
    public ResultBean<Person> loginPerson(Person person) {
        ResultBean<Person> resultBean = new ResultBean<>();
        Person loginPerson = mapper.loginPerson(person);
        //判断管理员用户,微信用户直接过
        if (loginPerson != null && person.getPersonPassword() != null) {
            if (!bCryptPasswordEncoder.matches(person.getPersonPassword(), loginPerson.getPersonPassword())) {
                loginPerson = null;
            }
        }
        if (loginPerson == null) {
            resultBean.setStatus(ResultBean.FAILURE_CODE);
            resultBean.setMsg("登陆失败");
        } else {
            resultBean.setStatus(ResultBean.SUCCSSED_CODE);
            resultBean.setMsg("登陆成功");
        }
        resultBean.setData(Arrays.asList(loginPerson));
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

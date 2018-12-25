package com.ydb.service.imp;

import com.ydb.dao.IPersonDao;
import com.ydb.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.dao
 * @date:2018/12/24
 */
@Component
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    IPersonDao personDao;

    @Override
    public Person loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDao.findPersonByUserNamePassword(username);
        return person;
    }
}

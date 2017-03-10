package com.cew.service.impl;

/**
 * Created by chenchaofei on 2017/3/10.
 */
import javax.transaction.Transactional;

import com.cew.dao.TUserDao;
import com.cew.entity.TUser;
import com.cew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserDao userDao;

    @Override
    public TUser findByName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public TUser findById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public TUser addUser(TUser user) {
        userDao.save(user);
        return user;
    }

}

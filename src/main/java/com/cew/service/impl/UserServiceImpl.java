package com.cew.service.impl;

/**
 * Created by chenchaofei on 2017/3/10.
 */
import javax.transaction.Transactional;

import com.cew.dao.TUserDao;
import com.cew.entity.TUser;
import com.cew.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    private static boolean isStart=false;
    @Override
    public void initAdminUser() {
        if(isStart) {
            return ;
        }
        TUser adminUser;
        adminUser = this.findByName("admin");
        if(adminUser == null) {
            adminUser = new TUser();
            adminUser.setUserName("admin");
            adminUser.setPassWord("cew@123");
            this.addUser(adminUser);
            logger.info("init admin user");
        }
        isStart = true;
    }

}

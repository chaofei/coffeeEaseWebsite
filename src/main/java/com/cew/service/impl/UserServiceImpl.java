package com.cew.service.impl;

/**
 * Created by chenchaofei on 2017/3/10.
 */
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.cew.common.config.HttpSessionConfig;
import com.cew.dao.TUserDao;
import com.cew.entity.TUser;
import com.cew.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;


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

    @Override
    public HashMap getLoginInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (HashMap<String, Object>)request.getSession().getAttribute(HttpSessionConfig.KEY_IS_LOGIN);
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

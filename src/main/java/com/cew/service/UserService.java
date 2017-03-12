package com.cew.service;

import com.cew.entity.TUser;

import java.util.HashMap;

/**
 * Created by chenchaofei on 2017/3/10.
 */
public interface UserService {
    TUser findByName(String username);
    TUser findById(Long id);

    TUser addUser(TUser user);

    void initAdminUser();

    HashMap getLoginInfo();
}

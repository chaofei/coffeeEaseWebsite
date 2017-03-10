package com.cew.controller;

/**
 * Created by chenchaofei on 2017/3/10.
 */
import com.cew.entity.TUser;
import com.cew.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String helloWorld() {
        return "hello World";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/id/{id}", method= RequestMethod.GET)
    public TUser getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * 测试地址  http://localhost:8080/getUser?name=zhao
     * @param name
     * @return
     */
    @RequestMapping(value="/getUser", method= RequestMethod.GET)
    public TUser user(String name) {
        TUser user = userService.findByName(name);
        return user;
    }

    /**
     * 测试地址 http://localhost:8080/addUser?userName=zhao
     * @param user
     * @return
     */
    @RequestMapping(value="/addUser", method= RequestMethod.POST)
    public TUser adduser(TUser user) {
        return userService.addUser(user);
    }
}

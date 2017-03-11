package com.cew.controller;

/**
 * Created by chenchaofei on 2017/3/10.
 */
import com.cew.common.config.CaptchaConfig;
import com.cew.entity.TUser;
import com.cew.result.JsonResult;
import com.cew.result.ResultCode;
import com.cew.service.UserService;
import com.cew.util.Str;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "/user", description = "用户相关API", position = 1)
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value="登录", notes="用户登录", httpMethod = "POST")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType="query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType="query", dataType = "String"),
            @ApiImplicitParam(name = "cap", value = "验证码", required = true, paramType="query", dataType = "String")
    })
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public JsonResult login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("cap") String usercap) {
        TUser user = userService.findByName(username);
        String realcap = (String)request.getSession().getAttribute(CaptchaConfig.SESSION_KEY);
        if(!usercap.equals(realcap)) {
            return new JsonResult(ResultCode.INVALID_CAPCODE);
        }
        if(user == null) {
            return new JsonResult(ResultCode.LOGIN_FAIL);
        }
        if(!user.getPassWord().equals(Str.md5(password))) {
            return new JsonResult(ResultCode.LOGIN_FAIL);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", user.getId());
        data.put("username", user.getUserName());
        return new JsonResult(ResultCode.LOGIN_SUCCESS, data);
    }
}

package com.cew.common.interceptors;

import com.alibaba.fastjson.JSON;
import com.cew.common.config.HttpSessionConfig;
import com.cew.result.JsonResult;
import com.cew.result.ResultCode;
import com.cew.service.UserService;
import com.cew.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenchaofei on 2017/3/10.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(!checkLogin(httpServletRequest, httpServletResponse)) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getRequestURI().indexOf("/job/admin") == 0 && userService.getLoginInfo() == null) {
            JsonResult result = new JsonResult(ResultCode.FORBIDDEN);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        return true;
    }
}

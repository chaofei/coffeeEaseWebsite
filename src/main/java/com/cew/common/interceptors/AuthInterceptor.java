package com.cew.common.interceptors;

import com.alibaba.fastjson.JSON;
import com.cew.common.config.HttpSessionConfig;
import com.cew.result.JsonResult;
import com.cew.result.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenchaofei on 2017/3/10.
 */
public class AuthInterceptor implements HandlerInterceptor {

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
        if(request.getRequestURI().indexOf("/job/admin") == 0 && request.getSession().getAttribute(HttpSessionConfig.KEY_IS_LOGIN) == null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            JsonResult result = new JsonResult(ResultCode.FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        return true;
    }
}

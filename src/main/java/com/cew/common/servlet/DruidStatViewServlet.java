package com.cew.common.servlet;

/**
 * Created by chenchaofei on 2017/3/10.
 * 配置web访问的servlet
 */
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;


/**
 * 配置druid监控统计功能
 * 在SpringBoot项目中基于注解的配置，如果是web.xml配置，按规则配置即可
 * 这样启动项目后在浏览器中输入地址：端口/druid，就可以看到druid的监控web页面了
 */

@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "allow", value = "192.168.16.110,127.0.0.1"), // IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name="deny",value="192.168.16.111"), // IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="ccf"),// 用户名
                @WebInitParam(name="loginPassword",value="ccf"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        }
)
public class DruidStatViewServlet extends StatViewServlet {

}

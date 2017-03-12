package com.cew.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by chenchaofei on 2017/3/11.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)//30分钟失效
public class HttpSessionConfig {
    public final static String KEY_CAP = "capText";

    public final static String KEY_IS_LOGIN = "isLogin";
}

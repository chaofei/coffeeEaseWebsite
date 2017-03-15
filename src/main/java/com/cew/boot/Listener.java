package com.cew.boot;

import com.cew.service.UserService;
import com.cew.util.SpringContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by chenchaofei on 2017/3/15.
 */
@Component
public class Listener implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${cew.user.admin.init}")
    private boolean initAdminUser;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (initAdminUser) {
            UserService userService = SpringContext.getBean(UserService.class);
            userService.initAdminUser();
        }
    }

}

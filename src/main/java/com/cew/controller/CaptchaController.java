package com.cew.controller;

import com.cew.common.config.CaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by chenchaofei on 2017/3/11.
 */
@Controller
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @RequestMapping(value = "/captcha-image", method = RequestMethod.GET)
    void getKaptchaImage(HttpServletResponse response, HttpSession session) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute(CaptchaConfig.SESSION_KEY, capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.flush();
        out.close();
    }
//
//    @RequestMapping("/captcha-get")
//    String getcode(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String code = (String)session.getAttribute(CaptchaConfig.SESSION_KEY);
//        return code;
//    }

}

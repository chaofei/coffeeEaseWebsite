package com.cew.controller;

import com.cew.result.JsonResult;
import com.cew.result.ResultCode;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenchaofei on 2017/3/13.
 */
@RestController
public class MainsiteErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public JsonResult handleError(){
        return new JsonResult(ResultCode.PAGE_NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}

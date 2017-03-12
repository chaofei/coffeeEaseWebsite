package com.cew.controller;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.common.config.CaptchaConfig;
import com.cew.common.config.HttpSessionConfig;
import com.cew.entity.TJob;
import com.cew.entity.TUser;
import com.cew.result.JsonResult;
import com.cew.result.ResultCode;
import com.cew.service.JobService;
import com.cew.service.UserService;
import com.cew.util.Str;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "/job", description = "职位API")
@RequestMapping("/job")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value="列表", notes="职位列表", httpMethod = "GET")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "p", value = "页码", required = false, paramType="query", dataType = "int"),
    })
    @RequestMapping(value="/list", method= RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "p", defaultValue = "0", required = false) int pageNum) {
        List<TJob> data = jobService.getOnineList(pageNum);
        return new JsonResult(ResultCode.SUCCESS, data);
    }

    @ApiOperation(value="添加", notes="添加职位", httpMethod = "POST")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "title", value = "职位名称", required = true, paramType="query", dataType = "String"),
    })
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public JsonResult add(@RequestParam("title") String title){
        TJob job = new TJob();
        job.setTitle(title);
        job.setStatus(TJob.STATUS_ONLINE);
        jobService.addJob(job);
        return new JsonResult(ResultCode.SUCCESS, job.getId());
    }

    @ApiOperation(value="更新资料", notes="更新职位资料", httpMethod = "POST")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "职位id", required = true, paramType="query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "职位名称", required = true, paramType="query", dataType = "int"),
    })
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public JsonResult update(@RequestParam("id") Long id,
            @RequestParam("title") String title){
        TJob job = new TJob();
        job.setTitle(title);
        job.setStatus(TJob.STATUS_ONLINE);
        jobService.addJob(job);
        return new JsonResult(ResultCode.SUCCESS, job.getId());
    }


}

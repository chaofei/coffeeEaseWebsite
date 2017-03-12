package com.cew.controller;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.entity.TJob;
import com.cew.result.JsonResult;
import com.cew.result.ResultCode;
import com.cew.service.JobService;
import com.cew.util.GeneratePageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/job", description = "职位API")
@RequestMapping("/job")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value="列表", notes="职位列表", httpMethod = "GET")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "p", value = "页码", required = false, paramType="query", dataType = "int"),
    })
    @RequestMapping(value="/list", method= RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "p", defaultValue = "0", required = false) int pageNum) {
        return new JsonResult(ResultCode.SUCCESS, GeneratePageable.getPageInfo(
                jobService.getOnlineList(pageNum),
                jobService.getOnlineCount()
        ));
    }


    @ApiOperation(value="管理列表", notes="职位管理列表", httpMethod = "GET")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "p", value = "页码", required = false, paramType="query", dataType = "int"),
            @ApiImplicitParam(name = "s", value = "状态", required = false, paramType="query", dataType = "int"),
    })
    @RequestMapping(value="/admin/list", method= RequestMethod.GET)
    public JsonResult offlineList(@RequestParam(value = "p", defaultValue = "0", required = false) int pageNum,
                                  @RequestParam(value = "s", defaultValue = "0", required = false) int status) {
        if(status == 1) {
            return new JsonResult(ResultCode.SUCCESS, GeneratePageable.getPageInfo(
                    jobService.getOnlineList(pageNum),
                    jobService.getOnlineCount()
            ));
        }
        return new JsonResult(ResultCode.SUCCESS, GeneratePageable.getPageInfo(
                jobService.getOfflineList(pageNum),
                jobService.getOfflineCount()
        ));
    }

    @ApiOperation(value="添加", notes="添加职位", httpMethod = "POST")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "title", value = "职位名称", required = true, paramType="query", dataType = "String"),
    })
    @RequestMapping(value="/admin/add", method= RequestMethod.POST)
    public JsonResult add(@RequestParam("title") String title){
        TJob job = new TJob();
        job.setTitle(title);
        job.setStatus(TJob.STATUS_ONLINE);
        jobService.add(job);
        return new JsonResult(ResultCode.SUCCESS, job.getId());
    }


    @ApiOperation(value="更新资料", notes="更新职位资料", httpMethod = "POST")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "职位id", required = true, paramType="query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "职位名称", required = true, paramType="query", dataType = "String"),
    })
    @RequestMapping(value="/admin/modify", method= RequestMethod.POST)
    public JsonResult modify(@RequestParam("id") Long id,
            @RequestParam("title") String title){
        TJob job = new TJob();
        job.setId(id);
        job.setTitle(title);
        jobService.modify(job);
        return new JsonResult(ResultCode.SUCCESS, true);
    }


    @ApiOperation(value="删除", notes="删除职位", httpMethod = "GET")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "职位id", required = true, paramType="query", dataType = "int")
    })
    @RequestMapping(value="/admin/delete", method= RequestMethod.GET)
    public JsonResult delete(@RequestParam("id") Long id){
        jobService.delete(id);
        return new JsonResult(ResultCode.SUCCESS, true);
    }


    @ApiOperation(value="更新状态", notes="更新职位状态", httpMethod = "GET")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "职位id", required = true, paramType="query", dataType = "int"),
            @ApiImplicitParam(name = "status", value = "职位状态", required = true, paramType="query", dataType = "int")
    })
    @RequestMapping(value="/admin/upstatus", method= RequestMethod.GET)
    public JsonResult upstatus(@RequestParam("id") Long id,
                               @RequestParam("status") byte status){
        jobService.changeStatus(id, status);
        return new JsonResult(ResultCode.SUCCESS, true);
    }





}

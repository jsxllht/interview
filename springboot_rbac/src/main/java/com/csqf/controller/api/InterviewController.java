package com.csqf.controller.api;

import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.pojo.Interview;
import com.csqf.service.InterviewService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/interview")
@RestController
@Api(description = "interview接口")
@CrossOrigin
public class InterviewController {
    @Autowired
    InterviewService interviewService;
//
//    @ApiOperation("获取所有interview信息")
//    @GetMapping("/allInterview")
//    public R findallInterview(){
//        List<Interview> listInterview = interviewService.findAllInterview();
//        return new R(ResponseEnum.SUCCESS,listInterview);
//    }
    @ApiOperation("模糊查询加分页查询interview信息")
    @GetMapping("/pageInfo")
    public R selectByInterviewInfo(String headline, String content, Integer pagenum, Integer pagesize){
        PageInfo<Interview> interviewPageInfo = interviewService.selectByInterviewInfo(headline, content, pagenum, pagesize);
        return new R(ResponseEnum.SUCCESS,interviewPageInfo);
    }
}

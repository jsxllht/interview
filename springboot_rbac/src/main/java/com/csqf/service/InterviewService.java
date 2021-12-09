package com.csqf.service;

import com.csqf.pojo.Interview;
import com.github.pagehelper.PageInfo;

public interface InterviewService {
    //增
    int addInterview(Interview interview);

    //删
    int delInterview(Integer id);

    //改
    int updInterview(Interview interview);

    //查(带分页及模糊查询)
    PageInfo<Interview> selectByInterviewInfo(String headline, String content,Integer pagenum, Integer pagesize);

//    //初始化时查所有
//    List<Interview> findAllInterview();

}

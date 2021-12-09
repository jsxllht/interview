package com.csqf.service.impl;

import com.csqf.mapper.InterviewMapper;
import com.csqf.pojo.Interview;
import com.csqf.pojo.InterviewExample;
import com.csqf.service.InterviewService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewMapper interviewMapper;
    @Override
    public int addInterview(Interview interview) {

        return 0;
    }

    @Override
    public int delInterview(Integer id) {
        return 0;
    }

    @Override
    public int updInterview(Interview interview) {
        return 0;
    }

    @Override
    public PageInfo<Interview> selectByInterviewInfo(String headline, String content, Integer pagenum, Integer pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        if (StringUtils.isEmpty(headline)&&StringUtils.isEmpty(content)){
            return new PageInfo<Interview>(interviewMapper.selectByExample(null));
        }
        //构造条件
        InterviewExample example = new InterviewExample();
        InterviewExample.Criteria criteria1 = example.createCriteria();
        if(!StringUtils.isEmpty(headline)){
            criteria1.andHeadlineLike(headline);
        }
        if(!StringUtils.isEmpty(content)){
            criteria1.andContentLike(content);
        }
        return new PageInfo<Interview>(interviewMapper.selectByExample(example));
    }

//    @Override
//    public List<Interview> findAllInterview() {
//        return interviewMapper.selectByExample(null);
//    }
}

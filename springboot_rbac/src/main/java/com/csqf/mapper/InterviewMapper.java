package com.csqf.mapper;

import com.csqf.pojo.Interview;
import com.csqf.pojo.InterviewExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewMapper {
    int countByExample(InterviewExample example);

    int deleteByExample(InterviewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Interview record);

    int insertSelective(Interview record);

    List<Interview> selectByExample(InterviewExample example);

    Interview selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Interview record, @Param("example") InterviewExample example);

    int updateByExample(@Param("record") Interview record, @Param("example") InterviewExample example);

    int updateByPrimaryKeySelective(Interview record);

    int updateByPrimaryKey(Interview record);
}
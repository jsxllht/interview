package com.csqf.mapper;

import com.csqf.pojo.PhoneLogin;
import com.csqf.pojo.PhoneLoginExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneLoginMapper {
    int countByExample(PhoneLoginExample example);

    int deleteByExample(PhoneLoginExample example);

    int deleteByPrimaryKey(String phone);

    int insert(PhoneLogin record);

    int insertSelective(PhoneLogin record);

    List<PhoneLogin> selectByExample(PhoneLoginExample example);

    PhoneLogin selectByPrimaryKey(String phone);

    int updateByExampleSelective(@Param("record") PhoneLogin record, @Param("example") PhoneLoginExample example);

    int updateByExample(@Param("record") PhoneLogin record, @Param("example") PhoneLoginExample example);

    int updateByPrimaryKeySelective(PhoneLogin record);

    int updateByPrimaryKey(PhoneLogin record);
}
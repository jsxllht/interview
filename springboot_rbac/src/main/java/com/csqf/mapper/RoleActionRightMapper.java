package com.csqf.mapper;

import com.csqf.pojo.RoleActionRight;
import com.csqf.pojo.RoleActionRightExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleActionRightMapper {
    int countByExample(RoleActionRightExample example);

    int deleteByExample(RoleActionRightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleActionRight record);

    int insertSelective(RoleActionRight record);

    List<RoleActionRight> selectByExample(RoleActionRightExample example);

    RoleActionRight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleActionRight record, @Param("example") RoleActionRightExample example);

    int updateByExample(@Param("record") RoleActionRight record, @Param("example") RoleActionRightExample example);

    int updateByPrimaryKeySelective(RoleActionRight record);

    int updateByPrimaryKey(RoleActionRight record);
}
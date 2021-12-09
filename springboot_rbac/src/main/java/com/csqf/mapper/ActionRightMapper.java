package com.csqf.mapper;

import com.csqf.pojo.ActionRight;
import com.csqf.pojo.ActionRightExample;
import com.csqf.pojo.dto.ActionMenusDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRightMapper {
    int countByExample(ActionRightExample example);

    int deleteByExample(ActionRightExample example);

    int deleteByPrimaryKey(Integer rightid);

    int insert(ActionRight record);

    int insertSelective(ActionRight record);

    List<ActionRight> selectByExample(ActionRightExample example);

    ActionRight selectByPrimaryKey(Integer rightid);

    int updateByExampleSelective(@Param("record") ActionRight record, @Param("example") ActionRightExample example);

    int updateByExample(@Param("record") ActionRight record, @Param("example") ActionRightExample example);

    int updateByPrimaryKeySelective(ActionRight record);

    int updateByPrimaryKey(ActionRight record);

    List<ActionMenusDTO> findActionMenusByParentid(@Param("parentid") Integer parentid);

    List<Integer> actionRightsByRoleid(@Param("roleid") Integer roleid);
}
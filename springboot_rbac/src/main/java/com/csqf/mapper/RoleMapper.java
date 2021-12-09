package com.csqf.mapper;

import com.csqf.pojo.Role;
import com.csqf.pojo.RoleExample;
import com.csqf.pojo.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<MenuDTO> celectRightByRoleidAndParentid(@Param("roleid") Integer roleid, @Param("parentid") Integer parentid);

    List<String> selectRightCodeByRoleid(@Param("roleid") Integer roleid);

}
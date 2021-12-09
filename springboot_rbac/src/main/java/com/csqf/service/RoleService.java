package com.csqf.service;

import com.csqf.mapper.RoleMapper;
import com.csqf.mapper.RoleRightMapper;
import com.csqf.pojo.Role;
import com.csqf.pojo.RoleExample;
import com.csqf.pojo.RoleRightExample;
import com.csqf.pojo.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    /*
     *根据角色id获得这个角色的权限，收纳并返回
     * */
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleRightMapper roleRightMapper;

    /*
    根据角色id找到对应的权限菜单
    * */
    public List<MenuDTO> findRightByRoleIdForMenu(Integer RoleId) {
        List<MenuDTO> roots = roleMapper.celectRightByRoleidAndParentid(RoleId, 0);
        for (MenuDTO root : roots) {
            Integer rightId = root.getRightid();
            List<MenuDTO> child = roleMapper.celectRightByRoleidAndParentid(RoleId, rightId);
            root.setChildren(child);
        }
        return roots;
    }

    /*
    根据角色id查找对应的动作权限
    * */
    public List<String> findRightCodeByRoleId(Integer roleId) {
        return roleMapper.selectRightCodeByRoleid(roleId);

    }

    /*
查找所有的角色列表
* */
    public List<Role> findAllRoleList() {
        return roleMapper.selectByExample(null);
    }

    /*
更具名字添加用户
* */
    public void addRole(Role role) {
        roleMapper.insertSelective(role);
    }

    /*
更具名字删除用户
* */
    public void deleteRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
        RoleRightExample example = new RoleRightExample();
        example.createCriteria().andRoleidEqualTo(id);
        roleRightMapper.deleteByExample(example);
    }

    /*
检查角色名是否存在
* */
    public Boolean checkRoleName(String rolename) {
        RoleExample example = new RoleExample();
        example.createCriteria().andRolenameEqualTo(rolename);
        List<Role> roles = roleMapper.selectByExample(example);
        if (roles == null || roles.size() == 0) {
            return true;
        }
        return false;
    }
}

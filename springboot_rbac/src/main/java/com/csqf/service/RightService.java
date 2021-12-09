package com.csqf.service;

import com.csqf.mapper.ActionRightMapper;
import com.csqf.mapper.RoleActionRightMapper;
import com.csqf.mapper.RoleRightMapper;
import com.csqf.mapper.RrightMapper;
import com.csqf.pojo.*;
import com.csqf.pojo.dto.ActionMenusDTO;
import com.csqf.pojo.dto.EmpowermentDTO;
import com.csqf.pojo.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RightService {

    @Autowired
    private RrightMapper rrightMapper;

    @Autowired
    private ActionRightMapper actionRightMapper;
    @Autowired
private RoleRightMapper roleRightMapper;
    @Autowired
    private RoleActionRightMapper roleActionRightMapper;

    public List<MenuDTO> getRightsByRoleid() {
    // 1 获得指定的角色第一层的权力的集合   菜单权力
    List<MenuDTO> roots = rrightMapper.selectRightsByParentid(0);
    if (CollectionUtils.isEmpty(roots)) {
        for (MenuDTO root : roots) {
            Integer parentid = root.getRightid();
            // 查询第一层的每一个权力节点的儿子
            List<MenuDTO> menuDTOS = rrightMapper.selectRightsByParentid(parentid);
            root.setChildren(menuDTOS);

            }
        }
    return roots;
    }

    public List<ActionMenusDTO> getActionRightsByRoleid(){
        // 1 获得指定的角色第一层的权力的集合   动作权限
        List<ActionMenusDTO> actionRoots = actionRightMapper.findActionMenusByParentid(0);
        if (CollectionUtils.isEmpty(actionRoots)) {
        for (ActionMenusDTO actionroot : actionRoots) {
            Integer parentid = actionroot.getRightid();
        //查询下一层的子节点
            actionroot.setChildren(actionRightMapper.findActionMenusByParentid(parentid));
            }
        }
        return actionRoots;
    }

    //更具角色id查找所拥有的动作权限id
    public List<Integer> selectActionRightidsByRoleNoParent(Integer roleid) {
        return actionRightMapper.actionRightsByRoleid(roleid);
    }

    public List<Integer> selectRightidsByRoleNoParent(Integer roleid) {
        return rrightMapper.selectRightidsByRoleNoParent(roleid);
    }

    @Transactional
    public void EmpowermentByRoleIdAndRights(EmpowermentDTO empowermentDTO){
        //先根据id删除掉所有权限，再根据前端的数组确定权限
        RoleRightExample example = new RoleRightExample();
        example.createCriteria().andRoleidEqualTo(empowermentDTO.getRoleid());
        roleRightMapper.deleteByExample(example);
        //授权
        List<Integer> rights = empowermentDTO.getRights();
        for (int i=0;i<rights.size();i++) {
            RoleRight roleRight = new RoleRight();
            roleRight.setRoleid(empowermentDTO.getRoleid());
            roleRight.setRightid(rights.get(i));
            roleRightMapper.insertSelective(roleRight);
        }
    }

    @Transactional
    public void actionEmpowermentByRoleidAndRights(EmpowermentDTO empowermentDTO){
        //先根据id删除掉所有权限，再根据前端的数组确定权限
        System.out.println(11);
        RoleActionRightExample example = new RoleActionRightExample();
        example.createCriteria().andRoleidEqualTo(empowermentDTO.getRoleid());
        roleActionRightMapper.deleteByExample(example);
        //授权
        List<Integer> rights = empowermentDTO.getRights();
        for (int i=0;i<rights.size();i++) {
            RoleActionRight roleActionRight = new RoleActionRight();
            roleActionRight.setRoleid(empowermentDTO.getRoleid());
            roleActionRight.setRightid(rights.get(i));
            roleActionRightMapper.insertSelective(roleActionRight);
        }
    }



}
package com.csqf.controller.admin;

import com.csqf.aop.anno.CheckActionRight;
import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.pojo.dto.ActionMenusDTO;
import com.csqf.pojo.dto.EmpowermentDTO;
import com.csqf.pojo.dto.MenuDTO;
import com.csqf.service.RightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/right/test")
@Api(description = "菜单那权限和动作权限接口")
public class AdminRightController {
    @Autowired
    private RightService rightService;

    @GetMapping("/search/check")
    @CheckActionRight("TEST_SEARCH")
    @ApiOperation("判断该用户是否有查询权限")
    public R CheckSearch() {
        return new R(ResponseEnum.SUCCESS, null);
    }

    @GetMapping("/audit/check")
    @CheckActionRight(value = "TEST_AUDIT")
    @ApiOperation("判断该用户是否有审批权限")
    public R CheckAaudit() {
        return new R(ResponseEnum.SUCCESS, null);
    }

    /**
     * 获得系统中所有的菜单权限 层级格式返回
     */
    @ApiOperation("获得系统中所有的权限 层级格式返回")
    @GetMapping("/rights")
    public R getRightsByRoleid() {
        // System.out.println(roleid);
        List<MenuDTO> rights = rightService.getRightsByRoleid();
        return new R(ResponseEnum.SUCCESS, rights);
    }

    /**
     * 根据角色id 查询出 这个角色的拥有的权限id的集合 不包含父节点
     */
    @ApiOperation("根据角色id 查询出 这个角色的拥有的权限id的集合 不包含父节点")
    @GetMapping("/rights/grant/{roleid}")
    public R getRightsByRoleidNoParent(
            @ApiParam(value = "角色id", required = true) @PathVariable("roleid") Integer roleid) {
        List<Integer> integers = rightService.selectRightidsByRoleNoParent(roleid);
        return new R(ResponseEnum.SUCCESS, integers);
    }

    /*
     * 获取根据用户id和权限菜单节点给用户赋权
     * */
    @PostMapping("/rights/empowerment")
    @ApiOperation("获取根据用户id和权限菜单节点给用户赋权")
    public R empowermentByRoleidAndMenus(@RequestBody EmpowermentDTO empowermentDTO) {
        rightService.EmpowermentByRoleIdAndRights(empowermentDTO);
        return new R(ResponseEnum.SUCCESS, null);
    }

    @ApiOperation("获得系统中所有的动作权限 层级格式返回")
    @GetMapping("/actionrights")
    public R getactionRightsByRoleid() {
        List<ActionMenusDTO> actionMenus = rightService.getActionRightsByRoleid();
        return new R(ResponseEnum.SUCCESS, actionMenus);
    }

    @ApiOperation("根据角色id 查询出 这个角色的拥有的动作权限id的集合 不包含父节点")
    @GetMapping("/actionrights/{roleid}")
    public R getactionRightsByRoleidNoParent(@PathVariable("roleid") Integer roleid) {
        List<Integer> integers = rightService.selectActionRightidsByRoleNoParent(roleid);
        return new R(ResponseEnum.SUCCESS, integers);
    }

    @PostMapping("/rights/actionempowerment")
    @ApiOperation("获取根据用户id和权限动作节点给用户赋权")
    public R actionEmpowermentByRoleidAndMenus(@RequestBody EmpowermentDTO empowermentDTO) {
        rightService.actionEmpowermentByRoleidAndRights(empowermentDTO);
        return new R(ResponseEnum.SUCCESS, null);
    }


}

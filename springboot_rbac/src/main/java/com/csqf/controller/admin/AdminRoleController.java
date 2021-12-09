package com.csqf.controller.admin;

import com.csqf.aop.anno.CheckLogin;
import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.pojo.Role;
import com.csqf.pojo.dto.MenuDTO;
import com.csqf.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/role")
@Api(description = "角色接口")
public class AdminRoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/menus")
    @CheckLogin
    @ApiOperation("根据角色id找到对应的权限菜单")
    public R findRightsByRoleidForMenu(HttpServletRequest request) {
        Object roleid = request.getAttribute("roleid");
        System.out.println(roleid);
        List<MenuDTO> menus = roleService.findRightByRoleIdForMenu(Integer.parseInt(roleid.toString()));
        return new R(ResponseEnum.SUCCESS, menus);
    }

    @GetMapping("/allrolelist")
    @ApiOperation("获取所有角色列表")
    public R findAllRoleList() {
        List<Role> allRoleList = roleService.findAllRoleList();
        System.out.println(allRoleList);
        return new R(ResponseEnum.SUCCESS, allRoleList);
    }

    @PostMapping("/addrole")
    @ApiOperation("添加角色")
    public R addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return new R(ResponseEnum.SUCCESS, null);
    }
    @DeleteMapping("/deleterole/{id}")
    @ApiOperation("删除角色")
    public R deleteRole(@PathVariable("id") String id) {
        roleService.deleteRole(Integer.parseInt(id));
        return new R(ResponseEnum.SUCCESS, null);
    }

    @ApiOperation("判断角色名字是否存在")
    @GetMapping("/checkrolename/{rolename}")
    public R checkUserName(@PathVariable("rolename") String rolename) {
        Boolean flag = roleService.checkRoleName(rolename);
        return new R(ResponseEnum.SUCCESS, flag);
    }
}

package com.csqf.controller.admin;


import com.csqf.aop.anno.CheckLogin;
import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.pojo.User;
import com.csqf.pojo.UserExample;
import com.csqf.pojo.dto.RoleidDto;
import com.csqf.pojo.dto.UserListDTO;
import com.csqf.service.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/admin/user")
@RestController
@CrossOrigin
@Api(description = "用户信息接口")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @ApiOperation("根据用户id查用户信息")
    @GetMapping("/userinfo")
    @CheckLogin
    public R userInfoByUserid(HttpServletRequest request) {
        Long id = new Long(request.getAttribute("id").toString());
        User user = userService.findUserByUserid(id);
        return new R(ResponseEnum.SUCCESS, user);
    }

    @ApiOperation("判断名字是否存在")
    @GetMapping("/checkname/{userName}")
    @CheckLogin
    public R checkUserName(@PathVariable("userName") String userName, HttpServletRequest request) {
        Long id = new Long(request.getAttribute("id").toString());
        Boolean flag = userService.checkUserName(userName, id);
        return new R(ResponseEnum.SUCCESS, flag);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/updateuser")
    public R updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.updateByUser(user);
        return new R(ResponseEnum.SUCCESS, null);
    }

    @ApiOperation("查找所有用户id和是否是vip")
    @GetMapping("/vipAndRoleid")
    public R findvipAndRoleid() {
        List<UserListDTO> user = userService.findUserListAll();
        return new R(ResponseEnum.SUCCESS, user);
    }

    @ApiOperation("根据电话和是否是vip已经角色查找用户")
    @GetMapping("/selectuser")
    public R selectByUserInfo(String phone, String roles, String isvip,Integer pagenum,Integer pagesize) {
        PageInfo<User> pageInfo = userService.selectByUserInfo(phone, roles, isvip, pagenum, pagesize);
        return new R(ResponseEnum.SUCCESS, pageInfo);
    }

    @ApiOperation("删除根据id")
    @DeleteMapping("/delete/{id}")
    public R deleteUser(@PathVariable("id") String id){
        userService.deleteUser(Long.parseLong(id));
        return new R(ResponseEnum.SUCCESS, null);
    }

    @ApiOperation("根据id修改用户角色")
    @PutMapping("/update/roles")
    public R updateRolesByRoleidFindRolename(@RequestBody RoleidDto roleidDto){
        userService.updateRolesByRoleidFindRolename(roleidDto.getRoleid(),roleidDto.getId());
        return new R(ResponseEnum.SUCCESS, null);
    }
}
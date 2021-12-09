package com.csqf.controller.admin;

import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.config.JWTConfig;
import com.csqf.pojo.User;
import com.csqf.pojo.dto.LoginDTO;
import com.csqf.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "登录接口")
@CrossOrigin
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JWTConfig jwtConfig;


    @PostMapping("/phonecode")
    @ApiOperation("生成验证码码")
    public R generateCode(String phone){
        loginService.generateCode(phone);
        return new R(ResponseEnum.SUCCESS,null);
    }

    @PostMapping("/phonelogin")
    @ApiOperation("手机号码登录")
    public R login(String phone, String code){
        User user = loginService.phoneLogin(phone, code);
        LoginDTO loginDto = new LoginDTO();
        BeanUtils.copyProperties(user,loginDto);
        String token = jwtConfig.generateJwt(loginDto);
        loginDto.setToken(token);
        return new R(ResponseEnum.SUCCESS,loginDto);
    }
}
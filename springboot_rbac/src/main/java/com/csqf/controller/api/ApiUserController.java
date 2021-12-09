package com.csqf.controller.api;



import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.config.JWTConfig;
import com.csqf.pojo.User;
import com.csqf.pojo.dto.LoginDTO;
import com.csqf.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
@Api(description = "登录接口")
@CrossOrigin
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTConfig jwtConfig;

    //    @DeleteMapping("/{id}")
    @PostMapping("/login")
    @ApiOperation("验证用户名和密码")
    public R login(String userName, String password){
        User user = userService.login(userName, password);
        LoginDTO loginDto = new LoginDTO();
        BeanUtils.copyProperties(user,loginDto);
        String token = jwtConfig.generateJwt(loginDto);
        loginDto.setToken(token);
        return new R(ResponseEnum.SUCCESS,loginDto);
    }
}

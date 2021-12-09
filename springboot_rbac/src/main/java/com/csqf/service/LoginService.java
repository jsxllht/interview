package com.csqf.service;

import com.csqf.common.exception.AppException;
import com.csqf.common.result.ResponseEnum;
import com.csqf.mapper.PhoneLoginMapper;
import com.csqf.mapper.UserMapper;
import com.csqf.pojo.User;
import com.csqf.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Autowired
    private PhoneLoginMapper phoneLoginMapper;

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserMapper userMapper;

    @Resource(name = "StringRedisTemplate")
    private RedisTemplate redisTemplate;


    //生成验证码并发送 同时保存到数据库
    public void generateCode(String phone){
        String code = (int)((Math.random()*900000)+100000)+"";
        redisTemplate.opsForValue().set(phone, code,120,TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get(phone));
//        smsService.sendCheckCode(phone,code);
    }


    //手机登录验证
    public User phoneLogin(String phone, String code) {
        // 去redis查
        String codeRedis = (String)redisTemplate.opsForValue().get(phone);
        if(StringUtils.isEmpty(codeRedis)){
            throw new AppException(ResponseEnum.CODE_EXCPTION);
        }else{
            if(codeRedis.equals(code)){
                // 去查用户表是否有
                UserExample example = new UserExample();
                example.createCriteria().andPhoneEqualTo(Long.parseLong(phone));
                List<User> users = userMapper.selectByExample(example);
                // 如果电话号码不存在
                User user = new User();
                if(CollectionUtils.isEmpty(users)){
                    user.setPhone(Long.parseLong(phone));
                    user.setAvatarUrl("https://csqf001.oss-cn-shenzhen.aliyuncs.com/avatar/default.jpg");
                    user.setCreateTime(new Date());
                    user.setUserName(phone);
                    user.setUserPassword("123456");
                    user.setRoleid(2);
                    user.setRoles("user");
                    user.setVersion(1L);
                    userMapper.insertSelective(user);
                }else {
                    // 存在 把用户的信息送出去
                    user=users.get(0);
                }
                return userMapper.selectByPrimaryKey(user.getId());
                // 验证码是否正确
            }
            else{
                throw new AppException(ResponseEnum.CODE_ERROR);
            }
        }
    }
}

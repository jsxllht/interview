package com.csqf.service;

import com.csqf.common.exception.AppException;
import com.csqf.common.result.ResponseEnum;
import com.csqf.mapper.RoleMapper;
import com.csqf.mapper.UserMapper;
import com.csqf.pojo.Role;
import com.csqf.pojo.User;
import com.csqf.pojo.UserExample;
import com.csqf.pojo.dto.UserListDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RoleMapper roleMapper;
    /**
     * 登录
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public User login(String name, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(name);
        System.out.println(1);
        List<User> users = userMapper.selectByExample(example);
        if (users == null && users.size() == 0) {
            throw new AppException(ResponseEnum.USERNAME_NOT_FOUND);
        }
        User user = users.get(0);
        if (!user.getUserPassword().equals(password)) {
            throw new AppException(ResponseEnum.USERNAME_OR_PASSWORD_INVALIDATE);
        }
        return user;
    }

    /*
     * 根据id查找用户信息
     * */
    public User findUserByUserid(long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /**
     * userName: 客户端填写的 需要验证的用户名
     * id：  当前登录用户
     * 返回true: 可以注册 false: 不能注册
     */

    public Boolean checkUserName(String username, Long id) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username).andIdNotEqualTo(id);
        int i = userMapper.countByExample(example);
        return i == 0;
    }

    /**
     * 更新用户信息
     */
    public void updateByUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }


    /**
     * 查找所有的用户信息，分页
     */
    public List<UserListDTO> findUserListAll() {
        List<UserListDTO> list = userMapper.findUserListAll();
        return list;
    }

    public List<Long> findAll() {
        List<User> users = userMapper.selectByExample(null);
        List<Long>  list= new ArrayList<>();
        for (User user : users) {
            list.add(user.getId());
        }
        return list;
    }

    public PageInfo<User> selectByUserInfo(String phone, String roles, String isvip, Integer pagenum, Integer pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        if (StringUtils.isEmpty(phone)&&StringUtils.isEmpty(roles)&&StringUtils.isEmpty(isvip)){
            return new PageInfo<User>(userMapper.selectByExample(null));
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(phone)) {
            criteria.andPhoneEqualTo(Long.parseLong(phone));
        }
        if (!StringUtils.isEmpty(roles)) {
            System.out.println(roles);
            criteria.andRolesEqualTo(roles);
        }
        if (!StringUtils.isEmpty(isvip)) {
            boolean flag=true;
            if(isvip.equals("0")){
                flag=false;
            }
            criteria.andIsvipEqualTo(flag);
        }
        return new PageInfo<>(userMapper.selectByExample(example));
    }

    public void deleteUser(Long id){

        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new AppException(ResponseEnum.ACCOUNT_NO_EXISTS);
        }
        userMapper.deleteByPrimaryKey(id);

        // feign  发出的请求是一个同步请求
        // 这种情况下  服务和服务之间的通信 可以是异步的
        // 3 删除文件
//        R r = ossFeignService.delFile(user.getAvatarUrl());
//        if(!r.getCode().equals("200")){
//            log.error("文件【" + user.getAvatarUrl()+"】没有删除成功");
//        }
            // 参数1 队列的名字
            // 参数2： 投递的消息
        if(!"https://csqf001.oss-cn-shenzhen.aliyuncs.com/avatar/default.jpg".equals(user.getAvatarUrl())){
            rabbitTemplate.convertAndSend("rbac_DeleteOssUrl",user.getAvatarUrl());
        System.out.println("================已发送");}
}


    public void updateRolesByRoleidFindRolename(String roleid,String id){
        System.out.println(roleid+"==============="+id);
        Role role = roleMapper.selectByPrimaryKey(Integer.parseInt(roleid));
        User user = userMapper.selectByPrimaryKey(Long.parseLong(id));
        user.setRoles(role.getRolename());
        user.setRoleid(role.getRoleid());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
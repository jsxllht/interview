package com.csqf;

import com.csqf.pojo.User;
import com.csqf.pojo.dto.MenuDTO;
import com.csqf.service.RightService;
import com.csqf.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserApplication {

    @Autowired
    private UserService userService;
    @Autowired
    private RightService rightService;

    @Test
    public void test(){
        User user = userService.login("lht", "123");
        System.out.println(user);
    }
    @Test
    public void test1(){
        List<MenuDTO> rightsByRoleid = rightService.getRightsByRoleid();
        System.out.println(rightsByRoleid);
    }

    @Test
    public void test2(){
        User u = userService.findUserByUserid((long) 1);
        System.out.println(u);


    }
}


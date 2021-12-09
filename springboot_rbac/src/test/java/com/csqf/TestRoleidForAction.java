package com.csqf;

import com.csqf.pojo.dto.MenuDTO;
import com.csqf.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRoleidForAction {
    @Autowired
    private RoleService roleService;

    @Test
    public void tets01(){
        List<MenuDTO> rightByRoleidForMenu = roleService.findRightByRoleidForMenu(1);
        System.out.println(rightByRoleidForMenu);
    }
}

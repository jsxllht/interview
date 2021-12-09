package com.csqf;

import com.csqf.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SmsTest {
    @Autowired
    private SmsService smsService;

    @Test
    public void test001(){
       smsService.sendCheckCode("13071349409","9999");
    }
}

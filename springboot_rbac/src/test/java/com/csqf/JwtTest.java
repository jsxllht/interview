package com.csqf;



import com.csqf.config.JWTConfig;
import com.csqf.pojo.dto.LoginDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JWTConfig jwtConfig;

    /**
     *  生成token(jwt)
     */
    @Test
    public void test001(){
        LoginDTO dto = LoginDTO.builder()
                .id(1L)
                .roleid(1)
                .userName("lht")
                .avatarUrl("hhhhh").build();
        String s = jwtConfig.generateJwt(dto);
        System.out.println(s);
    }

    /**
     *  解析token
     */
    @Test
    public void test002(){
        LoginDTO loginDTO = jwtConfig.checkJwt("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjUxMjY2NjUsImV4cCI6MTYyNTEyNzI2NSwiaWQiOjEsInVzZXJOYW1lIjoiem91a3gwMDciLCJhdmF0YXJVcmwiOiJoaGhoaCIsInJvbGVpZCI6MX0.sRfWHbb94mqlgOlB-Y2tfSzK0ZohWa7Q13AP0uHiWRU");
        System.out.println(loginDTO);
    }
}

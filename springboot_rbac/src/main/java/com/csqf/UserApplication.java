package com.csqf;

import com.csqf.common.config.OssConfig;
import com.csqf.common.config.Swagger2Config;
import com.csqf.common.exception.ControllerExceptionAdvice;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.csqf.mapper")
@Import({
        ControllerExceptionAdvice.class,
        Swagger2Config.class,
        OssConfig.class
})

public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
        }
    }

package com.csqf.oos;

import com.csqf.common.config.OssConfig;
import com.csqf.common.config.Swagger2Config;
import com.csqf.common.exception.ControllerExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        ControllerExceptionAdvice.class,
        Swagger2Config.class,
        OssConfig.class
})
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}


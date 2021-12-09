package com.csqf.common.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 定义接口的总体信息
     *
     * @return
     */
    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("刘海庭的Swagger API 文档")
                .description("即使再小的帆也能远航")
                .version("1.0")
                .contact(new Contact("刘海庭", "http://www.jsxllht.com", "204227136@qq.com"))
                .build();
    }

    private ApiInfo adminApiInfo() {

        return new ApiInfoBuilder()
                .title("刘海庭的Swagger Admin 文档")
                .description("即使再小的帆也能远航")
                .version("1.0")
                .contact(new Contact("刘海庭", "http://www.jsxllht.com", "204227136@qq.com"))
                .build();
    }

    @Bean
    public Docket webApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi") // 用户组
                .apiInfo(webApiInfo()) // 组的信息
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))  // 用户接口的判断
                .build();

    }

    @Bean
    public Docket adminApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi") // 管路⚪组
                .apiInfo(adminApiInfo()) // 组的信息
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();

    }
}

package com.csqf.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config.oss")
public class OssConfig {
//注意prefix要写到最后一个".”符号之前
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;
}

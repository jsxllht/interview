package com.csqf.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "config.sms")
public class SmsConfig {

    private String accessKeyId; //LTAI5tK3d5m7G5wbrvjK2Y7D
    private String accessSecret; //q5TZs5NTIkHuzZbPxiSn4NUe98fklt
    private String signName; //扩新
    private String regionId; //cn-shenzhen
    private String sysDomain; //dysmsapi.aliyuncs.com
}

package com.csqf.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    /**
     *  队列bean 程序一起动 扫描到bean  就会创建队列
     */
    @Bean
    public Queue DeleteOssQueue(){
        // 参数1 队列的名字
        // 参数2：  消息持久  一般是true
        // 参数3： 这个队列是否让消费者多占
        // 参数4： 这个队列是否自动删除
        // 参数5：  这个队列的其他参数
        return new Queue("rbac_DeleteOssUrl",true,false,false,null);
    }
}

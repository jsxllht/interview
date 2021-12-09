package com.csqf.oos.listener;

import com.csqf.oos.service.OssService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConsumerListener {
    @Autowired
    private OssService ossService;
    /**
     *  添加对队列的监听
     */
    @RabbitListener(queues = "rbac_DeleteOssUrl")
    public void getWorkQueueMessage(String msg, Channel channel, Message message){
        System.out.println(msg);
        ossService.delFile(msg);
        System.out.println("删除成功");
    }

}

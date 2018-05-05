package com.linshen.eurekaclientprovider.controller;

import com.linshen.eurekaclientprovider.service.RabbitSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/29
 * @Description: TODO
 */
@RestController
@Api("RabbitMQ接口")
@RequestMapping("/rabbit")
public class RabbitMQController {
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Processor processor;

    @PostMapping("/mySend")
    @ApiOperation("通过自定义生产者发布消息")
    public String mySendMsg(@RequestParam String msg){
        //创建消息
        Message message=MessageBuilder.withPayload(msg.getBytes()).build();
        //发送消息
        rabbitSender.sendOrder().send(message);
        return "SUCCESS";
    }

    @PostMapping("/send")
    @ApiOperation("通过Processor发布消息")
    public String sendMsg(@RequestParam String msg){
        //创建消息
        Message message=MessageBuilder.withPayload(msg.getBytes()).build();
        //发送消息
        processor.output().send(message);
        return "SUCCESS";
    }

}

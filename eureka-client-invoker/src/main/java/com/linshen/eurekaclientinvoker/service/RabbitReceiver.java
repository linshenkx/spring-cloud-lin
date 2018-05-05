package com.linshen.eurekaclientinvoker.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.SubscribableChannel;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/29
 * @Description: TODO
 */

public interface RabbitReceiver {
    @Input("myInput")
    SubscribableChannel myInput();

    @Input(Processor.OUTPUT)
    SubscribableChannel output();
}

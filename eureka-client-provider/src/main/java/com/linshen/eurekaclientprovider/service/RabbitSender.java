package com.linshen.eurekaclientprovider.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/29
 * @Description: TODO
 */

public interface RabbitSender {

    @Output("myInput")
    MessageChannel sendOrder();
}

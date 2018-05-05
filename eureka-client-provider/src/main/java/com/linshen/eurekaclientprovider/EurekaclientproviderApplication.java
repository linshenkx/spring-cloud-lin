package com.linshen.eurekaclientprovider;

import com.linshen.eurekaclientprovider.service.RabbitSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebMvc
@EnableBinding({RabbitSender.class,Processor.class})
public class EurekaclientproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaclientproviderApplication.class, args);
    }
}

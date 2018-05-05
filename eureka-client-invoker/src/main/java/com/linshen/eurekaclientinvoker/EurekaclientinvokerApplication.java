package com.linshen.eurekaclientinvoker;

import com.linshen.eurekaclientinvoker.annotation.NotScan;
import com.linshen.eurekaclientinvoker.service.RabbitReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(excludeFilters ={@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {NotScan.class})} )
@EnableWebMvc
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableBinding({RabbitReceiver.class})
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class EurekaclientinvokerApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @StreamListener("myInput")
    public void myreceive(byte[] msg){
        System.out.println("myInput接收到的消息是: "+new String(msg));
    }

    @StreamListener(Processor.OUTPUT)
    public void receiveOut(byte[] msg){
        System.out.println("Processor.OUTPUT接收到的消息是: "+new String(msg));
    }
    public static void main(String[] args) {
        SpringApplication.run(EurekaclientinvokerApplication.class, args);
    }
}

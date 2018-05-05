package com.linshen.eurekaclientinvoker.config;

import com.linshen.eurekaclientinvoker.annotation.NotScan;
import com.linshen.eurekaclientinvoker.config.ribbonComponent.MyPing;
import com.linshen.eurekaclientinvoker.config.ribbonComponent.MyRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: TODO
 */
@Configuration
@NotScan
public class RibbonConfiguration {
    @Bean
    public IRule getRule(){
//        //负载均衡规则为随机
//        return new RandomRule();
        //使用自定义的负载均衡规则组件
        return new MyRule();
    }

    @Bean
    public IPing getPing(){
        return new MyPing();
    }
}

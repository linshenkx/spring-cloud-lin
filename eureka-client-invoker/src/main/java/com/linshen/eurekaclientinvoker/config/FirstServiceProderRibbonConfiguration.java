package com.linshen.eurekaclientinvoker.config;


import com.linshen.eurekaclientinvoker.annotation.NotScan;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: 使用RibbonClient为特定name的Ribbon Client配置自定义负载均衡规则(configuration属性指定Ribbon的配置类)
 */
//可通过在yaml文件中使用属性自定义Ribbon配置
@NotScan
@Configuration
@RibbonClient(name = "first-service-provider",configuration = com.linshen.eurekaclientinvoker.config.RibbonConfiguration.class)
public class FirstServiceProderRibbonConfiguration {
}

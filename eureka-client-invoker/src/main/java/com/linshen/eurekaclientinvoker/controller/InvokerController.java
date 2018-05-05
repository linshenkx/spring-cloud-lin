package com.linshen.eurekaclientinvoker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: TODO
 */
@RestController
@RequestMapping("/router")
@Api("代理执行接口")
public class InvokerController {

    private static final Logger LOGGER=  LoggerFactory.getLogger(InvokerController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @ApiOperation("构造一个指定id的user")
    @RequestMapping(value = "user/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String testRouter(@PathVariable Long id){
        return restTemplate.getForObject("http://first-service-provider/user/"+id,String.class);
    }

    @ApiOperation("获取代理实例信息")
    @GetMapping("/instance")
    public String logUserInstance(){
        ServiceInstance serviceInstance=loadBalancerClient.choose("first-service-provider");
        String info="id:"+serviceInstance.getServiceId()+" host:"+serviceInstance.getHost()+" port:"+serviceInstance.getPort();
        LOGGER.info(info);
        return info;
    }

}

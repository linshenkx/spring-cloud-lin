package com.linshen.eurekaclientinvoker.controller;

import com.linshen.eurekaclientinvoker.feignClient.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/25
 * @Description: TODO
 */
@RestController
@RequestMapping("/hystrix")
@Api("测试hystrix")
public class HystrixTestController {

    @Autowired
    private UserClient userClient;

    @ApiOperation("构造一个指定id的user")
    @GetMapping("/user/{id}")
    public UserClient.User testHystrixUserClient(@PathVariable Long id){
        System.out.println("执行Spring Feign,代理构造User方法");
        return userClient.createById(id);
    }

    @ApiOperation(value = "Hello World正常接口")
    @GetMapping("/normalHello")
    public String normalHello(){
        return "Hello World";
    }

    @ApiOperation(value = "Hello World堵塞接口")
    @GetMapping("/errorHello")
    public String errorHello() throws InterruptedException {
        //模拟堵塞
        Thread.sleep(10_000);
        return "Error Hello World";
    }

}

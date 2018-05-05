package com.linshen.eurekaclientinvoker.controller;

import com.linshen.eurekaclientinvoker.config.feignComponent.MyContract;
import com.linshen.eurekaclientinvoker.config.feignComponent.MyDecoder;
import com.linshen.eurekaclientinvoker.config.feignComponent.MyEncoder;
import com.linshen.eurekaclientinvoker.config.feignComponent.MyInterceptor;
import com.linshen.eurekaclientinvoker.feignClient.UserClient;
import com.linshen.eurekaclientinvoker.feignClient.my.HelloClient;
import com.linshen.eurekaclientinvoker.feignClient.my.MyUserClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Feign;
import feign.Logger;
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
 * @date: 2018/4/23
 * @Description: TODO
 */
@RestController
@RequestMapping("/feign")
@Api("Feign代理执行")
@DefaultProperties(groupKey = "GroupFeignKey")
public class FeignController {

    @Autowired
    private UserClient userClient;

    @ApiOperation("构造一个指定id的user,带Hystrix缓存")
    @GetMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "fallbackUserClient",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "1000")
    },
            threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "1"),
            @HystrixProperty(name = "maxQueueSize",value = "10")
    })
//    @CacheResult
    public UserClient.User testUserClient(@PathVariable Long id){
        System.out.println("执行Spring Feign,代理构造User方法");
        return userClient.createById(id);
    }

    /**
     * @HystrixCollapser和@CacheResult都只用于一次HTTP请求,所以不应该注解在Controller上
     */
//    @HystrixCollapser(batchMethod = "getUsers",collapserProperties = {
//            @HystrixProperty(name = "timerDelayInMilliseconds",value = "3000")
//    })
//    @ApiOperation("构造一个指定id的user,合并请求")
//    @GetMapping("/users/{id}")
//    public Future<UserClient.User> getSinglePerson(Long id){
//        System.out.println("执行单个获取User的方法");
//        return null;
//    }
//
//    @HystrixCommand
//    private List<UserClient.User> getUsers(List<Long> ids){
//        System.out.println("收集请求,参数数量为: "+ids.size());
//        List<UserClient.User> userList=new ArrayList<>(ids.size());
//        for(Long id:ids){
//            userList.add(new UserClient.User(id,"人造人"+id+"号",0));
//        }
//        return userList;
//    }


    /**
     * fallback方法,返回一个默认User
     * 注意,fallback方法参数和返回值要和原调用方法一致
     * @param id
     * @return
     */
    private UserClient.User fallbackUserClient(Long id){
        return new UserClient.User(0L,"default",18);
    }

    @ApiOperation("构造一个指定id的user")
    @GetMapping("/my/user/{id}")
    public MyUserClient.User testMyUserClient(@PathVariable Long id){
        System.out.println("执行原生Feign,代理构造User方法");
        MyUserClient userClient=Feign.builder()
                .decoder(new MyDecoder())
                .encoder(new MyEncoder())
                .contract(new MyContract())
                .requestInterceptor(new MyInterceptor())
                .logLevel(Logger.Level.FULL)
                .logger(new Logger.JavaLogger().appendToFile("logs/http.log"))
                .target(MyUserClient.class,"http://localhost:8681/");
        MyUserClient.User user=userClient.createById(id);
        return user;
    }

    @ApiOperation("say hello")
    @GetMapping("/hello")
    public String sayHello(){
        HelloClient helloClient=Feign.builder()
                .logLevel(Logger.Level.FULL)
                .logger(new Logger.JavaLogger().appendToFile("logs/http.log"))
                .target(HelloClient.class,"http://localhost:8682/test/");
        return helloClient.sayHello();
    }
}

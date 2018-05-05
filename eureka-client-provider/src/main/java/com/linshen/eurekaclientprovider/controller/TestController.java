package com.linshen.eurekaclientprovider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: TODO
 */
@RestController
@Api("测试接口")
@RequestMapping("/test")
@RefreshScope
public class TestController {
//    @Value("${profile}")
//    private String profile;
//
//    @GetMapping("/profile")
//    @ApiOperation("获取profile信息")
//    public String getProfile(){
//        return profile;
//    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "hello")
    public String sayHello(){
        return "Hello World";
    }
}

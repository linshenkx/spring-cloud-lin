package com.linshen.eurekaclientprovider.controller;

import com.linshen.eurekaclientprovider.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: TODO
 */
@RestController
@Api("User接口")
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "构造一个指定id的user")
    public User getUser(@PathVariable("id")Long id){
        return new User(id,"li",18);
    }
}

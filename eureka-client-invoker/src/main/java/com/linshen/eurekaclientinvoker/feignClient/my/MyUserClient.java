package com.linshen.eurekaclientinvoker.feignClient.my;

import com.linshen.eurekaclientinvoker.config.feignComponent.MyUrl;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import lombok.Data;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: TODO
 */
public interface MyUserClient {

    @Data
    class User {
        private Long id;
        private String name;
        private Integer age;
    }

    @RequestLine("GET /user/{id}")
    @MyUrl(url = "/user/num",method = "GET")
    @Headers("Content-Type: application/json")
    User createById(@Param("id") Long id);

}

package com.linshen.eurekaclientinvoker.feignClient.my;

import feign.RequestLine;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: TODO
 */

public interface HelloClient {

    @RequestLine("GET /hello")
    String sayHello();
}

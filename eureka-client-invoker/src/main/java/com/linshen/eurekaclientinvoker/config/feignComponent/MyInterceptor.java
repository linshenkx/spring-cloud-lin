package com.linshen.eurekaclientinvoker.config.feignComponent;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: TODO
 */

public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("使用自定义拦截器");
        template.header("Content-Type","application/json");
    }
}

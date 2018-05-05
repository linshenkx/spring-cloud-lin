package com.linshen.eurekaclientinvoker.config.feignComponent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: 自定义的Feign注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {

    String url();
    String method();
}

package com.linshen.eurekaclientinvoker.feignClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: TODO
 */

@FeignClient(name = "first-service-provider",fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @Data
    @AllArgsConstructor
    class User {
        private Long id;
        private String name;
        private Integer age;
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User createById(@PathVariable("id") Long id);

    /**
     * @FeignClient默认开启了primary注解,所以这里使用同类型的Component不会报错
     */
    @Component
    class UserClientFallback implements UserClient {

        @Override
        public User createById(Long id) {
            System.out.println("调用回退方法");
            return new User(0L,"default",1);
        }
    }

//    /**
//     * FallbackFactory的特别之处在于在create回退实例的时候,可以拿到Throwable对象,获得回退原因
//     */
//    @Component
//    class UserClientFallbackFactory implements FallbackFactory<UserClient> {
//        private static final org.slf4j.Logger LOGGER=LoggerFactory.getLogger(UserClientFallbackFactory.class);
//
//        @Override
//        public UserClient create(Throwable cause) {
//            return new UserClient() {
//                @Override
//                public User createById(Long id) {
//                    System.out.println("执行UserCientFallback的createById方法");
//                    return new User(0L,"default",18);
//                }
//            };
//        }
//    }



}

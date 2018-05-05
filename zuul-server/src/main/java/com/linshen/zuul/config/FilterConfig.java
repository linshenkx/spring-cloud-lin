package com.linshen.zuul.config;

import com.linshen.zuul.filter.pre.MyFirstFilter;
import com.linshen.zuul.filter.routing.RestTemplateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/27
 * @Description: TODO
 */
@Configuration
public class FilterConfig {

    @Bean
    public MyFirstFilter myFirstFilter(){
        return new MyFirstFilter();
    }

    @Bean
    public RestTemplateFilter restTemplateFilter(RestTemplate restTemplate){
        return new RestTemplateFilter(restTemplate);
    }
}

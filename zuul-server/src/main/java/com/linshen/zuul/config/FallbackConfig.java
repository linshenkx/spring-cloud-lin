package com.linshen.zuul.config;

import com.linshen.zuul.fallbackProvider.DefaultFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/27
 * @Description: TODO
 */
@Configuration
public class FallbackConfig {


    @Bean
    public FallbackProvider defaultFallbackProvider(){
        return new DefaultFallbackProvider();
    }
}

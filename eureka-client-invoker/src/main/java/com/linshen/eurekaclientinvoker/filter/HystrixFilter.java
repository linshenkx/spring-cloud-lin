package com.linshen.eurekaclientinvoker.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/27
 * @Description: Hystrix的缓存和合并请求功能需要先初始化请求上下文才能实现
 */
@WebFilter(urlPatterns = "/*",filterName = "hystrixFilter")
@Component
public class HystrixFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HystrixRequestContext hystrixRequestContext=HystrixRequestContext.initializeContext();
        try{
            chain.doFilter(request,response);
        }finally {
            hystrixRequestContext.shutdown();
        }
    }

    @Override
    public void destroy() {

    }
}

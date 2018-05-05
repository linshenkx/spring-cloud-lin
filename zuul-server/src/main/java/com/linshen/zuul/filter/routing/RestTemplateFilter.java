package com.linshen.zuul.filter.routing;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/27
 * @Description: TODO
 */

public class RestTemplateFilter extends ZuulFilter {

    private RestTemplate restTemplate;

    public RestTemplateFilter(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        // HTTP请求的全部信息都封装在一个RequestContext对象里
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest=requestContext.getRequest();
        //获取请求uri
        String uri=httpServletRequest.getRequestURI();
        System.out.println("当前请求的uri是: "+uri);
        if(uri.contains("test-zuul-filter")){
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        //获取需要调用的服务的id
        String serviceId= (String) requestContext.get("serviceId");
        //获取请求的uri
        //获取的URI包含服务prefix(/test-zuul-filter)
//        String uri=requestContext.getRequest().getRequestURI();
        //获取的URI不包含服务prefix(/test-zuul-filter)
        String uri= (String) requestContext.get("requestURI");
        //组合成url给RestTemplate调用
        String url="http://"+serviceId+uri;
        System.out.println("执行RestTemplateFilter,调用的url: "+url);
        //调用并获取结果
        String result=restTemplate.getForObject(url,String.class);
        //设置路由状态,表示已经进行路由
        requestContext.setResponseBody(result);
        //设置响应标识,调用该方法后Spring Cloud自带的Ribbon路由过滤器,简单过滤器将不会执行
        requestContext.sendZuulResponse();
        return null;
    }
}

package com.linshen.eurekaclientinvoker.hystrixCommand;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/25
 * @Description: TODO
 */

public class HelloCommand extends HystrixCommand<String> {

    private String url;

    CloseableHttpClient closeableHttpClient;

    public HelloCommand(String url){
        //调用父类的构造器,设置命令组的key,默认用来作为线程池的key
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000))
        );
        //创建HttpClient客户端
        this.closeableHttpClient=HttpClients.createDefault();
        this.url=url;
    }
    public HelloCommand(boolean isTimeout){
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50000))
        );

    }

    @Override
    protected String run() throws Exception {

        try {
            //调用Get方法请求服务
            HttpGet httpGet=new HttpGet(url);
            //得到服务响应
            HttpResponse httpResponse=closeableHttpClient.execute(httpGet);
            //解析并返回命令执行结果
            return EntityUtils.toString(httpResponse.getEntity());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "执行失败";
    }

    @Override
    protected String getFallback() {
        System.out.println("执行HelloCommand的回退方法");
        return "执行失败,调用回退方法";
    }
}

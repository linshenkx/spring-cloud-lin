package com.linshen.eurekaclientinvoker.config.feignComponent;

import feign.Client;
import feign.Request;
import feign.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: 原理:将Feign的request转换执行,再将执行结果转换为Feign的response
 * 此处中间的执行客户端为HttpClient的ClosebleHttpClient
 */

public class MyClient implements Client {
    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        System.out.println("运行自定义的Feign客户端");
        try {
            //创建一个默认的客户端
            CloseableHttpClient httpClient= HttpClients.createDefault();
            //获取调用的HTTP方法
            final String method=request.method();
            //创建一个HttpClient的HttpRequest
            HttpRequestBase httpRequestBase=new HttpRequestBase() {
                @Override
                public String getMethod() {
                    return method;
                }
            };
            //设置请求地址
            httpRequestBase.setURI(new URI(request.url()));
            //执行请求,获取响应
            HttpResponse httpResponse=httpClient.execute(httpRequestBase);
            //获取响应的主体内容
            byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
            //将HttpClient的响应对象装换为Feign的Response
            Response response=Response.builder()
                    .body(bytes)
                    .headers(new HashMap<>())
                    .status(httpResponse.getStatusLine().getStatusCode())
                    .build();
            return response;
        }catch (Exception e){
            throw new IOException(e);
        }
    }
}

package com.linshen.eurekaclientinvoker.config.feignComponent;

import feign.Contract;
import feign.MethodMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: 对方法上的自定义注解MyUrl进行翻译
 */

public class MyContract extends Contract.Default {
    @Override
    protected void processAnnotationOnClass(MethodMetadata data, Class<?> clz) {
        super.processAnnotationOnClass(data,clz);
    }

    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation annotation, Method method) {
        //调用父类的方法,使其支持继承的注解
        super.processAnnotationOnMethod(data,annotation,method);
        //使用自定义注解
        if(MyUrl.class.isInstance(annotation)){
            //获取注解实例
            MyUrl myUrl=method.getAnnotation(MyUrl.class);
            //获取注解信息(Http Method和Url)
            String httpMethod=myUrl.method();
            String url=myUrl.url();
            //将值放进data的template中
            //data.template().method(httpMethod);
            //data.template().append(url);
            System.out.println("使用了自定义注解,method: "+httpMethod+" url: "+url);

        }
    }

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata data, Annotation[] annotations, int paramIndex) {
        return super.processAnnotationsOnParameter(data, annotations, paramIndex);
    }
}

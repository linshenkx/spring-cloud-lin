package com.linshen.eurekaclientinvoker.config.feignComponent;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.gson.GsonEncoder;

import java.lang.reflect.Type;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: TODO
 */

public class MyEncoder extends GsonEncoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        System.out.println("使用自定义的Feign编码器,bodyType:"+bodyType.getTypeName());
        super.encode(object, bodyType, template);
    }
}

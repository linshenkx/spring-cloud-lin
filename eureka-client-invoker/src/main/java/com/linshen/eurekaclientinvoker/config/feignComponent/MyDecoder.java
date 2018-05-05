package com.linshen.eurekaclientinvoker.config.feignComponent;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/23
 * @Description: 如果想要自定义,可以实现Decoder接口
 */

public class MyDecoder extends GsonDecoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        System.out.println("使用自定义解码器,type: "+type.getTypeName());
        return super.decode(response, type);
    }
}

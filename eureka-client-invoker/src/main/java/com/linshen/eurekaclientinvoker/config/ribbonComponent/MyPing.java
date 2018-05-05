package com.linshen.eurekaclientinvoker.config.ribbonComponent;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: TODO
 */

public class MyPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        System.out.println("这是自定义的Ping实现类: "+server.getId()+" "+ server.getHostPort());
        //这里只是简单地直接认定为存活
        return true;
    }
}

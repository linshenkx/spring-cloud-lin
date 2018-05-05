package com.linshen.eurekaclientinvoker.config.ribbonComponent;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: TODO
 */

public class MyRule implements IRule {

    ILoadBalancer iLoadBalancer;

    public MyRule(){

    }

    public MyRule(ILoadBalancer iLoadBalancer){
        this.iLoadBalancer=iLoadBalancer;
    }

    @Override
    public Server choose(Object key) {
        //获取全部的服务器
        List<Server> serverList=iLoadBalancer.getAllServers();
        System.out.println("这是自定义的负载均衡组件类,输出服务器信息: ");
        for (Server server:serverList){
            System.out.println(server.getId()+" "+server.getHostPort());
        }
        //自定义规则,这里仅简单地返回第一个Server对象
        return serverList.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        iLoadBalancer=lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return iLoadBalancer;
    }
}

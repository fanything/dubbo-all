package com.basic.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.basic.demo.sdk.service.HelloService;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/4 17:19
 */
public class Consumer {

    public static void main(String[] args){
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("Consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
        reference.setApplication(application);
        // 多个注册中心可以用setRegistries()
        reference.setRegistry(registry);
        reference.setInterface(HelloService.class);
        reference.setVersion("1.0.0");

        // 和本地bean一样使用xxxService
        HelloService helloService = reference.get();

        System.out.println(helloService.hello("dobbo"));
    }
}

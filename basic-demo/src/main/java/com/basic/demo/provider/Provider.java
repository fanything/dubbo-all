package com.basic.demo.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.basic.demo.provider.service.HelloServiceImpl;
import com.basic.demo.sdk.service.HelloService;

import java.io.IOException;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/4 17:13
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        // 服务实现
        HelloService helloService = new HelloServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("basic-demo");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        //protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<HelloService> service = new ServiceConfig<HelloService>();
        service.setApplication(application);
        // 多个注册中心可以用setRegistries()
        service.setRegistry(registry);
        // 多个协议可以用setProtocols()
        service.setProtocol(protocol);
        service.setInterface(HelloService.class);
        service.setRef(helloService);
        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();

        System.in.read();
    }
}

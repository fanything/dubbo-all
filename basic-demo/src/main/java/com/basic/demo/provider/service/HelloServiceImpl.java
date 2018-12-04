package com.basic.demo.provider.service;

import com.basic.demo.sdk.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/4 17:10
 */
@Service
public class HelloServiceImpl implements HelloService{

    public String hello(String name) {
        return "hello," + name;
    }
}

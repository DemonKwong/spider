package com.spider.dao;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SpiderDaoImplApplication {
    public static void main(String[] args) {
        System.setProperty("dubbo.qos.port","22223");
        SpringApplication.run(SpiderDaoImplApplication.class,args);
    }
}

package com.spider.dao.core;

import com.spider.dao.core.processor.DouBanProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kuangjiewen
 * @Title: SpiderCoreApplication
 * @ProjectName spider
 * @Description: 爬虫入口类
 * @date 2018/11/26 15:14
 */
@SpringBootApplication
public class SpiderCoreApplication implements CommandLineRunner {

    @Autowired
    private DouBanProcessor douBanProcessor;

    public static void main(String[] args) {
        SpringApplication.run(SpiderCoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

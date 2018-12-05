package com.spider.core;

import com.spider.core.scheduled.God;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.Spider;

/**
 * @author kuangjiewen
 * @Title: SpiderCoreApplication
 * @ProjectName spider
 * @Description: 爬虫入口类
 * @date 2018/11/26 15:14
 */
@SpringBootApplication
@EnableScheduling
public class SpiderCoreApplication implements CommandLineRunner {

    @Autowired
    private God god;

    public static Spider douBanSpider;

    public static void main(String[] args) {
        SpringApplication.run(SpiderCoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        god.createAndRun();
    }

}

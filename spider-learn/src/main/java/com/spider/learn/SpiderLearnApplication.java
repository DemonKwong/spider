package com.spider.learn;

import com.spider.learn.mapper.BaseHouseInfoMapper;
import com.spider.learn.mapper.HouseContentMapper;
import com.spider.learn.time.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author kuangjiewen
 * @Title: SpiderDaoApplication
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/11/27 15:21
 */
@SpringBootApplication
@EnableScheduling
//@MapperScan(basePackages="com.spider.learn.mapper")
public class SpiderLearnApplication implements CommandLineRunner {
    @Autowired
    private BaseHouseInfoMapper baseHouseInfoMapper;

    @Autowired
    private HouseContentMapper houseContentHouseContentMapper;

    @Autowired
    private Time time;

    public static void main(String[] args) {
        SpringApplication.run(SpiderLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
        time.a2();
    }
}

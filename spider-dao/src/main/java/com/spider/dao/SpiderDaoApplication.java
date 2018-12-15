package com.spider.dao;

import com.spider.dao.mapper.BaseHouseInfoMapper;
import com.spider.dao.mapper.HouseContentMapper;
import com.spider.dao.model.HouseContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;

/**
 * @author kuangjiewen
 * @Title: SpiderDaoApplication
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/11/2715:21
 */
@SpringBootApplication
@EnableScheduling
//@MapperScan(basePackages="com.spider.dao.mapper")
public class SpiderDaoApplication implements CommandLineRunner {
    @Autowired
    private BaseHouseInfoMapper baseHouseInfoMapper;

    @Autowired
    private HouseContentMapper houseContentHouseContentMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpiderDaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
    }
}

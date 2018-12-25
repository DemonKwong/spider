package com.spider.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author kuangjiewen
 * @Title: SpiderWebApplication
 * @ProjectName spider
 * @Description: 爬虫web入口
 * @date 2018/12/20 14:46
 */
@SpringBootApplication
@EnableCaching
public class SpiderWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpiderWebApplication.class,args);
	}
}

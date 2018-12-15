package com.spider.dao.time;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kuangjiewen
 * @Title: Time
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/12/14 12:00
 */
@Component
public class Time {

	@Scheduled(cron = "0 0/1 * * * ? ")
	public void a1(){
		System.err.println(Thread.currentThread().getName()+"hahahhahahah"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			Thread.sleep(1000*60*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0/5 * * * * ? ")
	public void a2(){
		System.err.println(Thread.currentThread().getName()+"ppppppppppp"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}

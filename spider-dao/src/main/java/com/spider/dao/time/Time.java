package com.spider.dao.time;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//@Scheduled(cron = "0 0/1 * * * ? ")
	public void a1(){
		System.err.println(Thread.currentThread().getName()+"hahahhahahah"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			Thread.sleep(1000*60*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//@Scheduled(cron = "0/5 * * * * ? ")
	public void a2(){
//		stringRedisTemplate.opsForValue().set("page",String.valueOf(1));
//		stringRedisTemplate.opsForList().rightPush("list","list1");
//		stringRedisTemplate.opsForList().rightPush("list","list2");
//		stringRedisTemplate.opsForList().rightPush("list","list3");
		stringRedisTemplate.opsForHash().put("set","age",String.valueOf(23));
		stringRedisTemplate.opsForHash().put("set","name","kjw");
		stringRedisTemplate.opsForHash().put("set","company","grg");
		stringRedisTemplate.opsForValue().set("test","11\\012");
		System.err.println(Thread.currentThread().getName()+"ppppppppppp"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}

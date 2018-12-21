package com.spider.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author kuangjiewen
 * @Title: HelloController
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/12/2014:48
 */
@RestController
public class HelloController {

	private Logger logger = Logger.getLogger(HelloController.class);

	@RequestMapping("hello")
	public String hello(@RequestParam Map map){
//		// 微信加密签名
//		String signature = request.getParameter("signature");
//		// 随机字符串
//		String echostr = request.getParameter("echostr");
//		// 时间戳
//		String timestamp = request.getParameter("timestamp");
//		// 随机数
//		String nonce = request.getParameter("nonce");
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		return jsonObject.getString("echostr");
	}

	@RequestMapping("get")
	public String get(@RequestParam Map map){
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		logger.info(jsonObject.toJSONString());
		return "hello";
	}
}

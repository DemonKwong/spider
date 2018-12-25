package com.spider.web.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxMpMessageRouter wxMpMessageRouter;
	@RequestMapping("hello")
	public String hello(String timestamp, String nonce, String signature, String echostr, HttpServletRequest request) throws IOException, WxErrorException {
		if(!wxMpService.checkSignature(timestamp,nonce,signature)){
			return "非法请求";
		}
		if(StringUtils.isNoneBlank(echostr)){
			return echostr;
		}
		String token = wxMpService.getAccessToken();
		WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
		WxMpXmlOutMessage wxMpXmlOutMessage = wxMpMessageRouter.route(wxMpXmlMessage);
		if(wxMpXmlOutMessage != null){
			return wxMpXmlOutMessage.toXml();
		}else{
			return "success";
		}

	}

}

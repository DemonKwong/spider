package com.spider.web.controller;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author kuangjiewen
 * @Title: WeChatController
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/12/20 14:48
 */
@Controller
public class WeChatController {

	private Logger logger = Logger.getLogger(WeChatController.class);

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxMpMessageRouter wxMpMessageRouter;

	@RequestMapping("portal")
	public String hello(String timestamp, String nonce, String signature, String echostr, HttpServletRequest request) throws IOException {
		if(!wxMpService.checkSignature(timestamp,nonce,signature)){
			return "非法请求";
		}
		if(StringUtils.isNotEmpty(echostr)){
			return echostr;
		}
		WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
		WxMpXmlOutMessage wxMpXmlOutMessage = wxMpMessageRouter.route(wxMpXmlMessage);
		if(wxMpXmlOutMessage != null){
			return wxMpXmlOutMessage.toXml();
		}else{
			return "success";
		}

	}

}

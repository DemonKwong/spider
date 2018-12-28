package com.spider.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.spider.dao.api.HouseInfoMapper;
import com.spider.dao.dto.HouseInfo;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author kuangjiewen
 * @Title: WeChatController
 * @ProjectName spider
 * @Description: 接收微信发过来的请求入口
 * @date 2018/12/20 14:48
 */
@RestController
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
		logger.info("收到了一条消息，发送者："+wxMpXmlMessage.getFromUser()+"消息类型："+wxMpXmlMessage.getMsgType());
		WxMpXmlOutMessage wxMpXmlOutMessage = wxMpMessageRouter.route(wxMpXmlMessage);
		if(wxMpXmlOutMessage != null){
			return wxMpXmlOutMessage.toXml();
		}else{
			return "success";
		}

	}

}

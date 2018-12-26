package com.spider.web.configuration;

import com.spider.web.handler.SubscribeMessgeHandler;
import com.spider.web.handler.TextMessageHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kuangjiewen
 * @Title: WxMpServiceConfiguration
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/12/24 18:18
 */
@Configuration
public class WeChatConfiguration {

	@Value("${wechat.app-id}")
	private String appId;

	@Value("${wechat.secret}")
	private String secret;

	@Value("${wechat.aes-key}")
	private String aesKey;

	@Value("${wechat.token}")
	private String token;

	@Bean
	public WxMpConfigStorage initWxMpInMemoryConfigStorage(){
		WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpInMemoryConfigStorage.setAppId(appId);
		wxMpInMemoryConfigStorage.setSecret(secret);
		wxMpInMemoryConfigStorage.setAesKey(aesKey);
		wxMpInMemoryConfigStorage.setToken(token);
		return wxMpInMemoryConfigStorage;
	}


	@Bean
	public WxMpService initWxMpService(WxMpConfigStorage wxMpConfigStorage){
		WxMpServiceImpl wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
		return wxMpService;
	}

	@Bean
	public WxMpMessageRouter initWxMpMessageRouter(WxMpService wxMpService, TextMessageHandler textMessageHandler, SubscribeMessgeHandler subscribeMessgeHandler){
		WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textMessageHandler).end();
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.SUBSCRIBE).handler(subscribeMessgeHandler).end();
		return wxMpMessageRouter;
	}

}

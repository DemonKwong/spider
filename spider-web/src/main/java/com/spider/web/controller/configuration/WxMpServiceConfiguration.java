package com.spider.web.controller.configuration;

import com.spider.web.controller.handler.SubscribeMessgeHandler;
import com.spider.web.controller.handler.TextMessageHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
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
public class WxMpServiceConfiguration {

	@Bean
	public WxMpConfigStorage initWxMpInMemoryConfigStorage(){
		WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpInMemoryConfigStorage.setAppId("wxc4976d0c8d729b76");
		wxMpInMemoryConfigStorage.setSecret("2805540e9cb6c7ad5b9fca76889e8891");
		wxMpInMemoryConfigStorage.setAesKey("d5zJf4dXd1sus6Vx6pVUfRDx4O3G3TWQwc9sebvvvZK");
		wxMpInMemoryConfigStorage.setToken("hello2018");
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
		wxMpMessageRouter.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textMessageHandler).end().rule().async(false).msgType("event").event("subscribe").handler(subscribeMessgeHandler).end();
		return wxMpMessageRouter;
	}

}

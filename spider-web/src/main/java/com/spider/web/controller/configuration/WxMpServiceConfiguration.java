package com.spider.web.controller.configuration;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
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
	public WxMpService init(){
		WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpInMemoryConfigStorage.setAppId("wxc4976d0c8d729b76");
		wxMpInMemoryConfigStorage.setSecret("2805540e9cb6c7ad5b9fca76889e8891");
		wxMpInMemoryConfigStorage.setAesKey("d5zJf4dXd1sus6Vx6pVUfRDx4O3G3TWQwc9sebvvvZK");
		wxMpInMemoryConfigStorage.setToken("hello2018");
		WxMpServiceImpl wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
		return wxMpService;
	}
}

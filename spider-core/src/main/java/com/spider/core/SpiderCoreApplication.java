package com.spider.core;

import com.spider.core.processor.DouBanProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * @author kuangjiewen
 * @Title: SpiderCoreApplication
 * @ProjectName spider
 * @Description: 爬虫入口类
 * @date 2018/11/26 15:14
 */
@SpringBootApplication
public class SpiderCoreApplication implements CommandLineRunner {

    @Autowired
    private DouBanProcessor douBanProcessor;

    public static void main(String[] args) {
        SpringApplication.run(SpiderCoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Request request = new Request();
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        request.setUrl("https://www.douban.com/group/tianhezufang/discussion?start=0");
        DouBanProxyProvider douBanProxyProvider = new DouBanProxyProvider();
        douBanProxyProvider.setKuaiDaiLiSpider(kuaiDaiLiSpider);
        kuaiDaiLiSpider.setProxyProvider(douBanProxyProvider);
        DoubanDownLoader downloader = new DoubanDownLoader(douBanProxyProvider);

        Spider spider = Spider.create(doubanPageProcessor)
                //从"https://github.com/code4craft"开始抓
                .addRequest(request)
                .addPipeline(doubanHousePipeLine)
                .setDownloader(downloader)
                //开启5个线程抓取
                .thread(1);
        //启动爬虫.run();
        spider.getStatus();
    }
}

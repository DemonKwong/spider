package com.spider.core.scheduled;

import com.spider.core.SpiderCoreApplication;
import com.spider.core.persist.DouBanPipeLine;
import com.spider.core.processor.DouBanProcessor;
import com.spider.core.processor.GitIpProxyProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class God {

    private Logger logger = Logger.getLogger(God.class);

    @Autowired
    private DouBanProcessor douBanProcessor;

    @Autowired
    private DouBanPipeLine douBanPipeLine;

    @Autowired
    private GitIpProxyProcessor gitIpProxyProcessor;


    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/10 0/1 * * * ? ")
    public void createDouBanSpiderAndRun(){
        logger.info("定时器开始执行任务。。。。。。。");
        Integer pageNumber = douBanProcessor.getPageNumber();
        if(pageNumber == null){
            pageNumber = 1;
        }
        //如果爬虫已经阵亡了就重新创建一只继续爬
        if(SpiderCoreApplication.douBanSpider == null || SpiderCoreApplication.douBanSpider.getStatus() == Spider.Status.Stopped){
            logger.info("发现原来的爬虫已经阵亡，正在创建一只新的爬虫");
            createIpProxySpiderAndRun();
            while (gitIpProxyProcessor.getThread().isAlive()){
                logger.info("爬取代理的爬虫还在运行。。。");
            }
            Request douBanRequest = new Request();
            douBanRequest.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
            douBanRequest.setUrl("https://www.douban.com/group/tianhezufang/discussion?start="+(pageNumber-1)*25);
            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
            Proxy proxy = new Proxy(gitIpProxyProcessor.getHost(),gitIpProxyProcessor.getPort());
            httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(proxy));
            SpiderCoreApplication.douBanSpider = Spider.create(douBanProcessor)
                    .addPipeline(douBanPipeLine)
                    //从"https://github.com/code4craft"开始抓
                    .addRequest(douBanRequest)
                    //设置代理
                    .setDownloader(httpClientDownloader)
                    //开启5个线程抓取
                    .thread(1);
            SpiderCoreApplication.douBanSpider.run();
            logger.info("创建爬虫完毕~");
        }
        logger.info("定时器任务执行结束！！！！！！！");
    }

    private void createIpProxySpiderAndRun(){
        Request request = new Request();
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        request.setUrl("https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list");
        Spider.create(gitIpProxyProcessor)
                .addRequest(request)
                .thread(1)
                .run();
    }
}

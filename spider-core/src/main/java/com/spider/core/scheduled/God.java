package com.spider.core.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.spider.core.persist.DouBanPipeLine;
import com.spider.core.processor.DouBanProcessor;
import com.spider.core.processor.GitIpProxyProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

@Component
public class God {

    private Logger logger = Logger.getLogger(God.class);

    @Autowired
    private DouBanProcessor douBanProcessor;

    @Autowired
    private DouBanPipeLine douBanPipeLine;

    @Autowired
    private GitIpProxyProcessor gitIpProxyProcessor;

    @Scheduled(cron = "0 0 0/3 * * ? ")
    public void createDouBanSpiderAndRun(){
        logger.info("定时器开始执行任务。。。。。。。");
        logger.info("发现原来的爬虫已经阵亡，正在创建一只新的爬虫");
        createIpProxySpiderAndRun();
        while (gitIpProxyProcessor.getThread().isAlive()){
            logger.info("爬取代理的爬虫还在运行。。。");
        }
        List<String> proxyList = gitIpProxyProcessor.getProxyList();
        for(String proxyStr : proxyList){
            JSONObject object = JSONObject.parseObject(proxyStr);
            createDouBanSpider(object.getString("host"),object.getInteger("port"));
            logger.info("创建了一只爬虫。。。host："+object.getString("host")+"  port："+object.getInteger("port"));
        }
        logger.info("创建爬虫完毕~");
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

    private void createDouBanSpider(String host,Integer port){
        Integer pageNumber = douBanProcessor.getPageNumber();
        if(pageNumber == null){
            pageNumber = 1;
        }else{
            //这里要把缓存里面的当前页数重置到上一页，然后当前页数要重新爬。这样可以保证所有页的数据都爬了
            douBanProcessor.rollBackPrePage();
        }
        Request douBanRequest = new Request();
        douBanRequest.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        douBanRequest.setUrl("https://www.douban.com/group/tianhezufang/discussion?start="+(pageNumber-1)*25);
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Proxy proxy = new Proxy(host,port);
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(proxy));
        Spider.create(douBanProcessor)
                .addPipeline(douBanPipeLine)
                //从"https://github.com/code4craft"开始抓
                .addRequest(douBanRequest)
                //设置代理
                .setDownloader(httpClientDownloader)
                //开启5个线程抓取
                .thread(1).run();
    }
}

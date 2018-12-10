package com.spider.core.processor;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Random;

@Component
public class GitIpProxyProcessor implements PageProcessor {

    private Logger logger = Logger.getLogger(GitIpProxyProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private String host;

    private Integer port;

    private Thread thread;

    @Override
    public void process(Page page) {
        logger.info(Thread.currentThread().getName()+"开始去拿一个代理回来啦");
        String jsonStr = page.getRawText();
        String[] jsonStrArr = jsonStr.split("\n");
        String json = jsonStrArr[new Random().nextInt(100)];
        JSONObject object = JSONObject.parseObject(json);
        this.host = object.getString("host");
        this.port = object.getInteger("port");
        this.thread = Thread.currentThread();
        logger.info(thread.getName()+"抓取完毕，host："+this.host+"   port："+this.port);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public Thread getThread() {
        return thread;
    }
}

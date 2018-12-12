package com.spider.core.processor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.List;

@Component
public class GitIpProxyProcessor implements PageProcessor {

    private Logger logger = Logger.getLogger(GitIpProxyProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private List<String> proxyList;

    private Thread thread;

    @Override
    public void process(Page page) {
        logger.info(Thread.currentThread().getName()+"开始去拿一个代理回来啦");
        String jsonStr = page.getRawText();
        String[] jsonStrArr = jsonStr.split("\n");
        proxyList = Arrays.asList(jsonStrArr);
        this.thread = Thread.currentThread();
        logger.info(thread.getName()+"抓取完毕，一共抓取了"+proxyList.size()+"个代理");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public List<String> getProxyList() {
        return proxyList;
    }

    public Thread getThread() {
        return thread;
    }
}

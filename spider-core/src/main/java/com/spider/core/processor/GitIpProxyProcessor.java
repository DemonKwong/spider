package com.spider.core.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.List;

@Component
public class GitIpProxyProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        String jsonStr = page.getRawText();
        String[] jsonStrArr = jsonStr.split("\n");
        List<String> list = Arrays.asList(jsonStrArr);
        page.putField("ipList",list);
    }

    @Override
    public Site getSite() {
        return site;
    }
}

package com.spider.core.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Random;

@Component
public class KuaiDaiLiProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        int index = new Random().nextInt(20)+1;
        String ip = page.getHtml().xpath("//*[@id=\"list\"]/table/tbody/tr["+index+"]/td[1]/text()").toString();
        String port = page.getHtml().xpath("//*[@id=\"list\"]/table/tbody/tr["+index+"]/td[2]/text()").toString();
        page.putField("ip",ip);
        page.putField("port",Integer.valueOf(port));
    }

    @Override
    public Site getSite() {
        return site;
    }
}

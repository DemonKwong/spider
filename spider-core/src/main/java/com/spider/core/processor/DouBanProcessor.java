package com.spider.core.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DouBanProcessor implements PageProcessor {

      private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

      @Override
      public void process(Page page) {
            if(Pattern.matches("https://www.douban.com/group/tianhezufang/discussion\\?start=\\d+",page.getUrl().toString())){
                  Html html = page.getHtml();
                  List<String> houseInfoLinks = html.xpath("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr/td[1]/a/@href").regex("^https://www.douban.com/group/topic/.*").all();
                  List<String> pageLinks = html.xpath("//*[@id=\"content\"]/div/div[1]/div[3]/a/@href").all();
                  houseInfoLinks.addAll(pageLinks);
                  for(String link : houseInfoLinks){
                        Request request = new Request();
                        request.setUrl(link);
                        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
                        page.addTargetRequest(request);
                  }
                  //忽略此页面不作持久化
                  page.setSkip(true);
            }else if(Pattern.matches("https://www.douban.com/group/topic/\\d+/",page.getUrl().toString())){
                  Html html = page.getHtml();
                  Pattern p = Pattern.compile("[5-9][0-9]{2}元|[1-9][0-9]{3}元|(月租)|(租金)|(价格).{5,11}");
                  Pattern p1 = Pattern.compile("[4-9][0-9]{2}|[1-9][0-9]{3}");
                  String content = html.xpath("//*[@id=\"link-report\"]/div/div/p").all().toString();
                  List<String> priceList = new ArrayList<>();
                  Matcher matcher = p.matcher(content);
                  while (matcher.find()){
                        String price = matcher.group();
                        Matcher matcher1 = p1.matcher(price);
                        while(matcher1.find()){
                              priceList.add(matcher1.group());
                        }
                  }
                  page.putField("title",html.xpath("//*[@id=\"content\"]/h1/text()").toString());
                  page.putField("price",priceList);
                  page.putField("url",page.getUrl().toString());
                  page.putField("publishTime",html.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/h3/span[2]/text()").toString());
                  page.putField("content",content.toString());
                  page.putField("imgs",html.xpath("//*[@id=\"link-report\"]/div/div/div/div[@class='image-wrapper']/img/@src").all());
            }
      }

      @Override
      public Site getSite() {
            return site;
      }

}

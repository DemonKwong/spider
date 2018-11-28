package com.spider.core.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("douBanProcessor")
public class DouBanProcessor implements PageProcessor {

      private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

      /**
       * 价格匹配的正则表达式
       * 将所有价格捞出来
       */
      private Pattern priceGroupPattern = Pattern.compile("[5-9][0-9]{2}元|[1-9][0-9]{3}元|(月租)|(租金)|(价格).{5,11}");

      /**
       * 将匹配出来的价格再匹配一次
       * */
      private Pattern pricePattern = Pattern.compile("[4-9][0-9]{2}|[1-9][0-9]{3}");

      /**
       * 页面列的正则表达式
       * */
      private String pageListStr = "https://www.douban.com/group/tianhezufang/discussion\\?start=\\d+";

      /**
       * 页面详情页的正则表达式
       * */
      private String pageDetailStr = "https://www.douban.com/group/topic/\\d+/";

      private static Map pageNumberMap = new HashMap();
      @Override
      public void process(Page page) {
            if(Pattern.matches(pageListStr,page.getUrl().toString())){
                  String pageNumber = page.getUrl().xpath("//a/text()").toString();
                  pageNumberMap.put("page",Integer.valueOf(pageNumber));
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
                  //忽略此页面的数据不作持久化
                  page.setSkip(true);
            }else if(Pattern.matches(pageDetailStr,page.getUrl().toString())){
                  Html html = page.getHtml();
                  String content = html.xpath("//*[@id=\"link-report\"]/div/div/p").all().toString();
                  List<String> priceList = new ArrayList<>();
                  Matcher matcher = priceGroupPattern.matcher(content);
                  while (matcher.find()){
                        String price = matcher.group();
                        Matcher matcher1 = pricePattern.matcher(price);
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

      public static Integer getPageNumber() {
            return (Integer) pageNumberMap.get("page");
      }
}

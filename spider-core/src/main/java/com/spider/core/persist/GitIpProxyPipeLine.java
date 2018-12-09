package com.spider.core.persist;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class GitIpProxyPipeLine implements Pipeline {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> ipList = resultItems.get("ipList");
        if(CollectionUtils.isNotEmpty(ipList)) {
            //当缓存不存在代理池或者代理池的ip少于十个的时候就更新一波
            if(!redisTemplate.hasKey("ipList") || redisTemplate.opsForList().size("ipList")<10) {
                redisTemplate.opsForList().leftPushAll("ipList", ipList);
            }
        }
    }
}

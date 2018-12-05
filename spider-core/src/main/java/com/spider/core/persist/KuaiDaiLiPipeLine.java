package com.spider.core.persist;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class KuaiDaiLiPipeLine implements Pipeline {
    private String ip;

    private int port;

    @Override
    public void process(ResultItems resultItems, Task task) {
        ip = resultItems.get("ip");
        port = resultItems.get("port");
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}

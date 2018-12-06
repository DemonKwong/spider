package com.spider.core.persist;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class KuaiDaiLiPipeLine implements Pipeline {
    private String ip;

    private int port;

    private static Thread thread;

    @Override
    public void process(ResultItems resultItems, Task task) {
        ip = resultItems.get("ip");
        port = resultItems.get("port");
        thread = resultItems.get("thread");
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean isAlive() {
        if(thread == null){
            return true;
        }else {
            return thread.isAlive();
        }
    }
}

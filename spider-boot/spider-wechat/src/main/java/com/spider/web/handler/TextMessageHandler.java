package com.spider.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.spider.dao.api.HouseInfoMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TextMessageHandler implements WxMpMessageHandler {

    @Reference
    private HouseInfoMapper houseInfoMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

        String content = wxMpXmlMessage.getContent();
        if(StringUtils.equals("昨日统计",content)){
            int count = houseInfoMapper.countNewInfoInYesterday();
            String msg = "昨日一共发布了"+count+"条租房信息";
            return WxMpXmlOutMessage.TEXT().toUser(wxMpXmlMessage.getFromUser()).fromUser(wxMpXmlMessage.getToUser()).content(msg).build();
        }else{
            return WxMpXmlOutMessage.TEXT().toUser(wxMpXmlMessage.getFromUser()).fromUser(wxMpXmlMessage.getToUser()).content(wxMpXmlMessage.getContent()).build();
        }

    }
}

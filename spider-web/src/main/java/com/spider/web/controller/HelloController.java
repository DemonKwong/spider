package com.spider.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author kuangjiewen
 * @Title: HelloController
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/12/2014:48
 */
@RestController
public class HelloController {

	private Logger logger = Logger.getLogger(HelloController.class);

	@RequestMapping("hello")
	public String hello(@RequestParam Map map,@RequestBody WeChatMsg msg){
//		// 微信加密签名
//		String signature = request.getParameter("signature");
//		// 随机字符串
//		String echostr = request.getParameter("echostr");
//		// 时间戳
//		String timestamp = request.getParameter("timestamp");
//		// 随机数
//		String nonce = request.getParameter("nonce");
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		logger.info("jsonObject:"+jsonObject.toJSONString());
		logger.info("msg:"+msg.toString());
		return jsonObject.getString("echostr");
	}

	@RequestMapping(value = "get",method = RequestMethod.POST)
	public String get(@RequestBody WeChatMsg msg){
		logger.info(msg.toString());
		return "hello";
	}

	@XmlRootElement(name = "xml")
	static class WeChatMsg{
		private String toUserName;
		private String fromUserName;
		private String createTime;
		private String msgType;
		private String content;
		private String msgId;

		public String getToUserName() {
			return toUserName;
		}

		@XmlElement(name = "ToUserName")
		public void setToUserName(String toUserName) {
			this.toUserName = toUserName;
		}

		public String getFromUserName() {
			return fromUserName;
		}

		@XmlElement(name = "FromUserName")
		public void setFromUserName(String fromUserName) {
			this.fromUserName = fromUserName;
		}

		public String getCreateTime() {
			return createTime;
		}

		@XmlElement(name = "CreateTime")
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getMsgType() {
			return msgType;
		}

		@XmlElement(name = "MsgType")
		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		public String getContent() {
			return content;
		}

		@XmlElement(name = "Content")
		public void setContent(String content) {
			this.content = content;
		}

		public String getMsgId() {
			return msgId;
		}

		@XmlElement(name = "MsgId")
		public void setMsgId(String msgId) {
			this.msgId = msgId;
		}

		@Override
		public String toString() {
			return "WeChatMsg{" +
					"toUserName='" + toUserName + '\'' +
					", fromUserName='" + fromUserName + '\'' +
					", createTime='" + createTime + '\'' +
					", msgType='" + msgType + '\'' +
					", content='" + content + '\'' +
					", msgId='" + msgId + '\'' +
					'}';
		}
	}
}

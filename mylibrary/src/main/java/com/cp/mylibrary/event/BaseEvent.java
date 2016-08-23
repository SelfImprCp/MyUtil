package com.cp.mylibrary.event;


 /**
  *  事件  的父类
  * @author Administrator
  *
  */
public class BaseEvent {
	// 消息类型
	private String myLcp;

	public String getMsgType() {
		return myLcp;

	}

	public void setMsgType(String lcp) {
		this.myLcp = lcp;
	}

	// 通过消息传递的内容
	private String myMsgContent;

	public String getMyMsgContent() {
		return myMsgContent;
	}

	public void setMyMsgContent(String myMsgContent) {
		this.myMsgContent = myMsgContent;
	}

}

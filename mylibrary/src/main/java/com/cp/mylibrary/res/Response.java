package com.cp.mylibrary.res;

import java.io.Serializable;

/**
 * 请求返回基本对象
 * 
 * @author Administrator
 * 
 */
public class Response implements Serializable {
 	public static final int SUCCESS = 0;


	//

	/**
	 * 0，成功；非0，失败
	 */
	private String resultCode = "";

	/**
	 * 错误描述
	 */
	private String resultMessage = "";

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}

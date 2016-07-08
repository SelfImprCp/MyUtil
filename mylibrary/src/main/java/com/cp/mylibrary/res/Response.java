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
	private int resultCode = 0;

	/**
	 * 错误描述
	 */
	private String resultMessage = "";


	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}

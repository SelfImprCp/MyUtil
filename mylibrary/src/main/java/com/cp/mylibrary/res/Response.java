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

	//  登录
 public static final int  LOGIN = 1;
	//

	/**
	 * 0，成功；1，失败
	 */
	private int code = 0;

	/**
	 * 错误描述
	 */
	private String msg = "";

	/**
	 * 0没登录， 1登录
	 */
	private int isLogin = 0;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

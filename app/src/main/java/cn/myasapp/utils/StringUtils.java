package cn.myasapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	private final static Pattern IMG_URL = Pattern
			.compile(".*?(gif|jpeg|png|jpg|bmp)");

	private final static Pattern URL = Pattern
			.compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");


	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 判断一个url是否为图片url
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isImgUrl(String url) {
		if (url == null || url.trim().length() == 0)
			return false;
		return IMG_URL.matcher(url).matches();
	}

	/**
	 * 手机号验证
	 * 
	 * @return
	 */
	public static boolean isPhoneValid(String phone) {
		// 手机号码正则
		// final String REGEX_MOBILE = "^1[3458][0-9]{9}$";
		// ^1[3458][0-9]{9}$
		// ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
		// Pattern p = Pattern.compile(REGEX_MOBILE);
		// Matcher m = p.matcher(str);
		if (StringUtils.isEmpty(phone))
			return false;
		if (phone.length() == 11 && phone.subSequence(0, 1).equals("1")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为一个合法的url地址
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUrl(String str) {
		if (str == null || str.trim().length() == 0)
			return false;
		return URL.matcher(str).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	public static String getString(String s) {
		return s == null ? "" : s;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line + "<br>");
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	/***
	 * 截取字符串
	 * 
	 * @param start
	 *            从那里开始，0算起
	 * @param num
	 *            截取多少个
	 * @param str
	 *            截取的字符串
	 * @return
	 */
	public static String getSubString(int start, int num, String str) {
		if (str == null) {
			return "";
		}
		int leng = str.length();
		if (start < 0) {
			start = 0;
		}
		if (start > leng) {
			start = leng;
		}
		if (num < 0) {
			num = 1;
		}
		int end = start + num;
		if (end > leng) {
			end = leng;
		}
		return str.substring(start, end);
	}

	/**
	 * 判断一个List是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmptyList(List list) {
		boolean isEmpty = false;
		if (list == null || list.size() == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	
}

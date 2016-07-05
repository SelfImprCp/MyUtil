package com.cp.mylibrary.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 关于时间日期的操作包
 * 
 * @author Administrator
 * 
 */
public class DateTimeUtil {

	public final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public final static String dateFormater3 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 取得 当前 时间戳
	 * 
	 * @return
	 */
	public static long getTimesTamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 使用当前时间戳拼接一个唯一的文件名
	 *
	 * @param
	 * @return
	 */
	public static String getTempFileName() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS");
		String fileName = format.format(new Timestamp(System
				.currentTimeMillis()));
		return fileName;
	}


	/**
	 * 
	 * 返回当前系统时间
	 */
	public static String getCurrentDataTime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}





	/**
	 * 获取当前时间为每年第几周
	 * 
	 * @return
	 */
	public static int getWeekOfYear() {
		return getWeekOfYear(new Date());
	}

	/**
	 * 获取当前时间为每年第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
		week = week == 0 ? 52 : week;
		return week > 0 ? week : 1;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}




	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 友好 显示 时间
	 * 
	 * @param sdate
	 *   时间戳
	 * @return
	 */
	public static String friendly_time2(String sdate) {
		String res = "";
		if (StringUtils.isEmpty(sdate))
			return "";

		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		String currentData = getCurrentDataTime("MM-dd");
		int currentDay = StringUtils.toInt(currentData.substring(3));
		int currentMoth = StringUtils.toInt(currentData.substring(0, 2));

		int sMoth = StringUtils.toInt(sdate.substring(5, 7));
		int sDay = StringUtils.toInt(sdate.substring(8, 10));
		int sYear = StringUtils.toInt(sdate.substring(0, 4));
		Date dt = new Date(sYear, sMoth - 1, sDay - 1);

		if (sDay == currentDay && sMoth == currentMoth) {
			res = "今天 / " + weekDays[getWeekOfDate(new Date())];
		} else if (sDay == currentDay + 1 && sMoth == currentMoth) {
			res = "昨天 / " + weekDays[(getWeekOfDate(new Date()) + 6) % 7];
		} else {
			if (sMoth < 10) {
				res = "0";
			}
			res += sMoth + "/";
			if (sDay < 10) {
				res += "0";
			}
			res += sDay + " / " + weekDays[getWeekOfDate(dt)];
		}

		return res;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate 时间戳
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = null;

		if (isInEasternEightZones())
			time = toDate(sdate);
		else
			time = transformTime(toDate(sdate), TimeZone.getTimeZone("GMT+08"),
					TimeZone.getDefault());

		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天 ";
		} else if (days > 2 && days < 31) {
			ftime = days + "天前";
		} else if (days >= 31 && days <= 2 * 31) {
			ftime = "一个月前";
		} else if (days > 2 * 31 && days <= 3 * 31) {
			ftime = "2个月前";
		} else if (days > 3 * 31 && days <= 4 * 31) {
			ftime = "3个月前";
		} else {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
	 * 
	 * @return
	 */
	public static boolean isInEasternEightZones() {
		boolean defaultVaule = true;
		if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
			defaultVaule = true;
		else
			defaultVaule = false;
		return defaultVaule;
	}

	/**
	 * 根据不同时区，转换时间 2014年7月31日
	 * 
	 * @param
	 * @return
	 */
	public static Date transformTime(Date date, TimeZone oldZone,
			TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		return toDate(sdate, dateFormater.get());
	}

	public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
		try {
			return dateFormater.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getDateString(Date date) {
		return dateFormater.get().format(date);
	}







}

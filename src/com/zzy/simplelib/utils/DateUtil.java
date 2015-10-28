package com.zzy.simplelib.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 日期时间格式 */
public class DateUtil {

	public static String getShowDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(date);
	}

	public static String getShowShortDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getShowDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return sdf.format(date);
	}

	public static String getShowShortDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	/***
	 * @param date
	 * @return MM月dd日 HH:mm
	 */
	public static String getShowMonthTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
		return sdf.format(date);
	}

	public static Date strTimeToDatePoint(String time) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
		try {
			return sdFormat.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date strTimeToDate(String time) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdFormat.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date strTimeToDateTime(String time) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try {
			return sdFormat.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date strShortTimeToDateTime(String time) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdFormat.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	// public static SimpleDate getSimpleDate(Date date) {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日");
	// String timeString = sdf.format(date);
	// int year = Integer.parseInt(timeString.substring(0,
	// timeString.lastIndexOf("年")));
	// int month = Integer.parseInt(timeString.substring(
	// timeString.lastIndexOf("年") + 1, timeString.lastIndexOf("月")));
	// int day = Integer.parseInt(timeString.substring(
	// timeString.lastIndexOf("月") + 1, timeString.lastIndexOf("日")));
	// return new SimpleDate(year, month, day);
	// }
	//
	// public static SimpleDate getSimpleDateTime(Date date) {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	// String timeString = sdf.format(date);
	// String[] firstStrings = timeString.split(" ");
	// int year = Integer.parseInt(firstStrings[0].substring(0,
	// firstStrings[0].lastIndexOf("年")));
	// int month = Integer.parseInt(firstStrings[0].substring(
	// firstStrings[0].lastIndexOf("年") + 1,
	// firstStrings[0].lastIndexOf("月")));
	// int day = Integer.parseInt(firstStrings[0].substring(
	// firstStrings[0].lastIndexOf("月") + 1,
	// firstStrings[0].lastIndexOf("日")));
	// String[] timeStrings = firstStrings[1].split(":");
	//
	// return new SimpleDate(year, month, day,
	// Integer.parseInt(timeStrings[0]),
	// Integer.parseInt(timeStrings[1]));
	// }

	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(new Date());
	}

	public static String getLastWeek() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.WEEK_OF_YEAR, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		return sf.format(c.getTime());
	}

	public static boolean getIsOutTime(Date targetDate) {
		long nowTime = new Date().getTime();
		long targetTime = targetDate.getTime();
		if (nowTime > targetTime) {
			return true;
		}
		return false;
	}

	public static boolean getEndDateIsOk(Date startDate, Date endDate) {
		long tempStart = startDate.getTime();
		long tempEnd = endDate.getTime();
		if (tempStart >= tempEnd) {
			return false;
		}
		return true;
	}

}

package com.CrossCountry.Survey.utils.shichf;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.CrossCountry.Survey.utils.Log4jUtil;

public class CommonCheck {
	public static String returnStr(String str, String value) {
		if ("-".equals(value)) {
			str = "-";
		} else if ("1".equals(value)) {
			str = "异常";
		} else if ("0".equals(value)) {
			str = "正常";
		} else {
			str = "-";
		}
		return str;
	}

	public static String returnDou(Class<? extends Object> clazz, String str, String value, String id) {
		try {
			if ("-".equals(value)) {
				str = "-";
			} else if (Double.parseDouble(value) <= 0) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值小于等于0：" + value + " id值为：" + id);
				str = String.valueOf(0);
			} else {
				str = value;
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value + " id值为：" + id);
			str = "-";
		}
		return str;
	}

	public static Integer returnInt(Class<? extends Object> clazz, String str, String value) {
		try {
			if (StringUtils.isBlank(value) || value.equals("null")) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值为空");
				str = String.valueOf(0);
			} else if (Integer.parseInt(value) <= 0) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值小于等于0：" + value);
				str = String.valueOf(0);
			} else {
				str = value;
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value);
			str = String.valueOf(0);
		}
		return Integer.valueOf(str);
	}

	public static String returnRate(Class<? extends Object> clazz, String str, String value, String id) {
		try {
			if ("-".equals(value)) {
				str = "-";
			} else if (Double.parseDouble(value) > 100) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值大于100：" + value + " id值为：" + id);
				str = "100%";// String.valueOf(100);
			} else if (Double.parseDouble(value) <= 0) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值小于等于0：" + value + " id值为：" + id);
				str = "0%";// String.valueOf(0);
			} else {
				str = value + "%";
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value + " id值为：" + id);
			str = "-";
		}
		return str;
	}

	public static String returnRate2(Class<? extends Object> clazz, String str, String value) {
		try {
			if ("-".equals(value)) {
				str = "-";
			} else if (StringUtils.isBlank(value) || value == "undefined" || Double.isNaN(Double.parseDouble(value))) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值为空：" + value);
				str = "0";// String.valueOf(0);
			} else if (Double.parseDouble(value) > 100) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值大于100：" + value);
				str = "100";// String.valueOf(100);
			} else if (Double.parseDouble(value) <= 0) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值小于等于0：" + value);
				str = "0";// String.valueOf(0);
			} else {
				str = value;
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value);
			str = "-";
		}
		return str;
	}

	public static String returnUse(Class<? extends Object> clazz, String str, String value, String id) {
		try {
			if ("-".equals(value)) {
				str = "-";
			} else if (Double.parseDouble(value) > 0) {
				str = "使用中";
			} else if (Double.parseDouble(value) <= 0) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值小于等于0：" + value + " id值为：" + id);
				str = "-";
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value + " id值为：" + id);
			str = "-";
		}
		return str;
	}

	public static boolean isStatus(String status, String[] deviceStatuss) {
		boolean isStatus = false;
		if (null == deviceStatuss) {
			isStatus = true;
		} else {
			for (int i = 0; i < deviceStatuss.length; i++) {
				if (status.equals(deviceStatuss[i])) {
					isStatus = true;
					return isStatus;
				}
			}
		}
		return isStatus;
	}

	public static Integer returnNumber(Class<? extends Object> clazz, String str, String value) {
		int returnStr;
		/*
		 * if(null==value){ str = Integer.valueOf(value); }else if("".equals(value)){
		 * return 0; }else if("-".equals(value)){ return 0; }else{ return
		 * Integer.valueOf(value); }
		 */
		try {
			if (StringUtils.isBlank(value)) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值为异常：" + value);
				returnStr = 0;
			} else if ("null".equals(value)) {
				returnStr = 0;
			} else {
				returnStr = Integer.valueOf(value).intValue();
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value);
			returnStr = 0;
		}
		return returnStr;

	}

	public static String reStatus(String pingstate, String runstate) {
		String onlineStatus = "";
		if ("3".equals(runstate)) {
			onlineStatus = "挂牌";
		} else if ("1".equals(runstate)) {
			onlineStatus = "在线";
		} else {
			if ("0".equals(runstate)) {
				onlineStatus = "离线";
			} else if ("2".equals(runstate)) {
				onlineStatus = "未投入";
			}
		}
		return onlineStatus;
	}

	// 获取传入日期算起的前7天的日期
	public static String getPastDate(String time, int past) {
		// 时间表示格式可以改变，yyyyMMdd需要写例如20160523这种形式的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
		Date date = sdf.parse(time, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
		calendar.add(Calendar.DATE, -past);
		String result = sdf.format(calendar.getTime());
		return result;
	}

	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * 
	 * @param intervals intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> getPastDateList(String time, int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = intervals - 1; i > -1; i--) {
			pastDaysList.add(getPastDate(time, i));
		}
		return pastDaysList;
	}

	// 获取今天时间
	public static String getToday() {
		String returnEntiy;
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		returnEntiy = matter1.format(dt);
		return returnEntiy;
	}

	// 获取今天开始时间
	public static String getTodayStartTime() {
		String returnEntiy;
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		returnEntiy = matter1.format(dt);
		returnEntiy = returnEntiy + " 00:00:00";
		return returnEntiy;

	}

	// 获取今年时间
	public static String getNowYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String formatDate = sdf.format(date);
		return formatDate;
	}

	/**
	 * 判断IP地址的合法性，这里采用了正则表达式的方法来判断 return true，合法
	 */
	public static boolean ipCheck(String text) {
		if (text != null && !text.isEmpty()) {
			// 定义正则表达式
			String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
			// 判断ip地址是否与正则表达式匹配
			if (text.matches(regex)) {
				// 返回判断信息
				return true;
			} else {
				// 返回判断信息
				return false;
			}
		}
		return false;
	}

	// ip比较
	public static int ipCompare(String ip1, String ip2) {
		Pattern p = Pattern.compile("\\d{1,3}");
		Matcher m1 = p.matcher(ip1);
		Matcher m2 = p.matcher(ip2);
		while (m1.find()) {
			m2.find();
			int i1 = Integer.valueOf(m1.group());
			int i2 = Integer.valueOf(m2.group());
			if (i1 < i2)
				return -1;
			else if (i1 > i2)
				return 1;
		}
		return 0;
	}

	// IP转为数字
	public static long ipToLong(String ipAddress) {
		long result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");
		for (int i = 3; i >= 0; i--) {
			long ip = Long.parseLong(ipAddressInArray[3 - i]);
			result |= ip << (i * 8);
		}
		return result;
	}

	// 数字转为IP
	public static String longToIp(long i) {
		return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
	}

}

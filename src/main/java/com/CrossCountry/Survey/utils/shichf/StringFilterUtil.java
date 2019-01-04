package com.CrossCountry.Survey.utils.shichf;

import org.apache.commons.lang.StringUtils;

public class StringFilterUtil {
	public static String filterName(String name) {
		if (StringUtils.isBlank(name)) {
			return "";
		}
		String filterName = "";
		if (name.indexOf("省调") != -1) {
			filterName = "省调";
		} else if (name.indexOf("市调") != -1) {
			filterName = "市调";
		} else if (name.indexOf("中调") != -1) {
			filterName = "中调";
		} else if (name.indexOf("分中心") != -1) {
			filterName = "分中心";
		} else if (name.indexOf("区调") != -1) {
			filterName = "区调";
		} else if (name.indexOf("地调") != -1) {
			filterName = "地调";
		} else if (name.indexOf("网调") != -1) {
			filterName = "网调";
		}
		return name.replaceAll(filterName, "");
	}

	public static void main(String[] args) {
		long nums = 1000;
		int tempNum = Integer.valueOf(String.valueOf(nums)).intValue() + 1;
		for (int i = 0; i < nums; i++) {
			--tempNum;
			int tempNumLength = String.valueOf(tempNum).length();
			int zeroCount = 6 - tempNumLength;
			StringBuffer order = new StringBuffer();
			for (int j = 0; j < zeroCount; j++) {
				order.append(0);
			}
			System.out.println(order.append(tempNum).toString());
		}
	}
}

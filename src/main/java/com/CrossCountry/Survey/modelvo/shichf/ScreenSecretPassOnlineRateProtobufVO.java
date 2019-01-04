package com.CrossCountry.Survey.modelvo.shichf;

import java.util.List;

public class ScreenSecretPassOnlineRateProtobufVO {
	private String name;// 在线率密通率
	private String color;// 颜色
	private List<String> timeArrayEncrypt;// 时间
	private List<String> encrryptpackageCurve;// 值

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getTimeArrayEncrypt() {
		return timeArrayEncrypt;
	}

	public void setTimeArrayEncrypt(List<String> timeArrayEncrypt) {
		this.timeArrayEncrypt = timeArrayEncrypt;
	}

	public List<String> getEncrryptpackageCurve() {
		return encrryptpackageCurve;
	}

	public void setEncrryptpackageCurve(List<String> encrryptpackageCurve) {
		this.encrryptpackageCurve = encrryptpackageCurve;
	}

}

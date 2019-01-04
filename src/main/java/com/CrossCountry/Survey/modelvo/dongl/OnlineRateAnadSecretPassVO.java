package com.CrossCountry.Survey.modelvo.dongl;

public class OnlineRateAnadSecretPassVO {
	String onlineRate;
	String onlineRateColor;// 1:95以上用绿色;2:90-95字体和圆圈用黄色;3:90以下，字体和圆圈红色
	String secretPass;
	String secretPassColor;// 1:95以上用绿色;2:90-95字体和圆圈用黄色;3:90以下，字体和圆圈红色

	public String getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(String onlineRate) {
		this.onlineRate = onlineRate;
	}

	public String getOnlineRateColor() {
		return onlineRateColor;
	}

	public void setOnlineRateColor(String onlineRateColor) {
		this.onlineRateColor = onlineRateColor;
	}

	public String getSecretPass() {
		return secretPass;
	}

	public void setSecretPass(String secretPass) {
		this.secretPass = secretPass;
	}

	public String getSecretPassColor() {
		return secretPassColor;
	}

	public void setSecretPassColor(String secretPassColor) {
		this.secretPassColor = secretPassColor;
	}

}

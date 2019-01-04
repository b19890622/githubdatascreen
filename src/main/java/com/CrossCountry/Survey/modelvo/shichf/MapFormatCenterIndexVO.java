package com.CrossCountry.Survey.modelvo.shichf;

public class MapFormatCenterIndexVO {
	private String onlineRate; // 分中心在线率
	private String safetyCheckRate; // 分中心核查率
	private String safetyEventNums; // 分中心安全事件数
	private String name;// 分中心名称
	private String secretRate; // 密通线率

	public String getSecretRate() {
		return secretRate;
	}

	public void setSecretRate(String secretRate) {
		this.secretRate = secretRate;
	}

	public String getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(String onlineRate) {
		this.onlineRate = onlineRate;
	}

	public String getSafetyCheckRate() {
		return safetyCheckRate;
	}

	public void setSafetyCheckRate(String safetyCheckRate) {
		this.safetyCheckRate = safetyCheckRate;
	}

	public String getSafetyEventNums() {
		return safetyEventNums;
	}

	public void setSafetyEventNums(String safetyEventNums) {
		this.safetyEventNums = safetyEventNums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MapFormatCenterIndexVO mapFormatCenterIndexVO = (MapFormatCenterIndexVO) o;
		if (name != null && onlineRate != null && safetyCheckRate != null && safetyEventNums != null) {
			if (!name.equals(mapFormatCenterIndexVO.name) || !onlineRate.equals(mapFormatCenterIndexVO.onlineRate)
					|| !safetyCheckRate.equals(mapFormatCenterIndexVO.safetyCheckRate)
					|| !safetyEventNums.equals(mapFormatCenterIndexVO.safetyEventNums))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		String str = name + onlineRate + safetyCheckRate + safetyEventNums;
		return str.hashCode();
	}
}

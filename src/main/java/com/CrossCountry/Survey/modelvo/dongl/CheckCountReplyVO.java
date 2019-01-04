package com.CrossCountry.Survey.modelvo.dongl;

public class CheckCountReplyVO {
	String unCompliance;// 不合规项
	String safetyFlaw;// 安全漏洞
	String weakPass;// 弱口令
	String unComPercent  ;//不合规项百分比，向上取整不带百分号 例2.1%取值为3%，返给前端数字3
	String safetyPercent  ;//安全漏洞百分比
	String weakPassPercent  ;//弱口令百分比

	public String getUnCompliance() {
		return unCompliance;
	}

	public void setUnCompliance(String unCompliance) {
		this.unCompliance = unCompliance;
	}

	public String getSafetyFlaw() {
		return safetyFlaw;
	}

	public void setSafetyFlaw(String safetyFlaw) {
		this.safetyFlaw = safetyFlaw;
	}

	public String getWeakPass() {
		return weakPass;
	}

	public void setWeakPass(String weakPass) {
		this.weakPass = weakPass;
	}

	public String getUnComPercent() {
		return unComPercent;
	}

	public void setUnComPercent(String unComPercent) {
		this.unComPercent = unComPercent;
	}

	public String getSafetyPercent() {
		return safetyPercent;
	}

	public void setSafetyPercent(String safetyPercent) {
		this.safetyPercent = safetyPercent;
	}

	public String getWeakPassPercent() {
		return weakPassPercent;
	}

	public void setWeakPassPercent(String weakPassPercent) {
		this.weakPassPercent = weakPassPercent;
	}
	
	
}

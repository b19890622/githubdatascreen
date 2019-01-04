package com.CrossCountry.Survey.modelvo.liujg;

import java.util.Date;

public class RiskWarnVO {
	private String riskNo;// 预警编号
	private String riskLevel; // 预警级别
	private String riskName; // 预警名称
	private String riskRange; // 预警范围
	private String riskDate; // 预警时间
	private Date riskRDate;  // 预警时间

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskRange() {
		return riskRange;
	}

	public void setRiskRange(String riskRange) {
		this.riskRange = riskRange;
	}

	public String getRiskDate() {
		return riskDate;
	}

	public void setRiskDate(String riskDate) {
		this.riskDate = riskDate;
	}

	public Date getRiskRDate() {
		return riskRDate;
	}

	public void setRiskRDate(Date riskRDate) {
		this.riskRDate = riskRDate;
	}

}

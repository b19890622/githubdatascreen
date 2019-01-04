package com.CrossCountry.Survey.modelvo.dongl;

public class MainStationAlarmDistributionEntity {
	private String xdate; // 横坐标日期
	private String masterNum;// 主机告警数
	private String netNum;// 网络告警数
	private String safetyNum;// 安防告警数
	private String dbNum;// 数据库数

	public String getXdate() {
		return xdate;
	}

	public void setXdate(String xdate) {
		this.xdate = xdate;
	}

	public String getMasterNum() {
		return masterNum;
	}

	public void setMasterNum(String masterNum) {
		this.masterNum = masterNum;
	}

	public String getNetNum() {
		return netNum;
	}

	public void setNetNum(String netNum) {
		this.netNum = netNum;
	}

	public String getSafetyNum() {
		return safetyNum;
	}

	public void setSafetyNum(String safetyNum) {
		this.safetyNum = safetyNum;
	}

	public String getDbNum() {
		return dbNum;
	}

	public void setDbNum(String dbNum) {
		this.dbNum = dbNum;
	}

}

package com.CrossCountry.Survey.modelvo.dongl;

public class SubStationAlarmDistributionEntity {
	private String xdate;// 横坐标日期
	private String substationNum;// 变电站告警数
	private String powerPlantNum;// 电厂告警数

	public String getXdate() {
		return xdate;
	}

	public void setXdate(String xdate) {
		this.xdate = xdate;
	}

	public String getSubstationNum() {
		return substationNum;
	}

	public void setSubstationNum(String substationNum) {
		this.substationNum = substationNum;
	}

	public String getPowerPlantNum() {
		return powerPlantNum;
	}

	public void setPowerPlantNum(String powerPlantNum) {
		this.powerPlantNum = powerPlantNum;
	}

}

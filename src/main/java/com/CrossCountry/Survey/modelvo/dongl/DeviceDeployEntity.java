package com.CrossCountry.Survey.modelvo.dongl;

public class DeviceDeployEntity {
	private String totalDeployNum;//应部署数量
	private String realDeployNum;//实际部署数量
	private String stationName;// 厂站名称
	private String stationNum;// 各厂站对应个数

	public String getTotalDeployNum() {
		return totalDeployNum;
	}

	public void setTotalDeployNum(String totalDeployNum) {
		this.totalDeployNum = totalDeployNum;
	}

	public String getRealDeployNum() {
		return realDeployNum;
	}

	public void setRealDeployNum(String realDeployNum) {
		this.realDeployNum = realDeployNum;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

}

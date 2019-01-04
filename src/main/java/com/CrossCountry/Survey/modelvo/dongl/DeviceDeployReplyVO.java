package com.CrossCountry.Survey.modelvo.dongl;

import java.util.List;


public class DeviceDeployReplyVO {
	private List<DeviceDeployEntity> stationDeploy;// 厂站部署
	private SubstationEntity substaion;// 变电站部署信息圆环
	private PowerPlantEntity powerPlant;// 厂站部署信息圆环
	private String totalNum;// 部署装置厂站总数量
	
	public List<DeviceDeployEntity> getStationDeploy() {
		return stationDeploy;
	}
	public void setStationDeploy(List<DeviceDeployEntity> stationDeploy) {
		this.stationDeploy = stationDeploy;
	}
	public SubstationEntity getSubstaion() {
		return substaion;
	}
	public void setSubstaion(SubstationEntity substaion) {
		this.substaion = substaion;
	}
	public PowerPlantEntity getPowerPlant() {
		return powerPlant;
	}
	public void setPowerPlant(PowerPlantEntity powerPlant) {
		this.powerPlant = powerPlant;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	
	
}

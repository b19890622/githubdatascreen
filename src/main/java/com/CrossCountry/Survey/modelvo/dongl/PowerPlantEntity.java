package com.CrossCountry.Survey.modelvo.dongl;

public class PowerPlantEntity {
	String powerUndeploy;// 发电厂未部署数量
	String powerDeploy;// 发电厂实际部署数量
	String powerSum; // 发电厂应部署总数
	String powerFinishRate;// 发电站部署完成比例

	public String getPowerUndeploy() {
		return powerUndeploy;
	}

	public void setPowerUndeploy(String powerUndeploy) {
		this.powerUndeploy = powerUndeploy;
	}

	public String getPowerDeploy() {
		return powerDeploy;
	}

	public void setPowerDeploy(String powerDeploy) {
		this.powerDeploy = powerDeploy;
	}

	public String getPowerSum() {
		return powerSum;
	}

	public void setPowerSum(String powerSum) {
		this.powerSum = powerSum;
	}

	public String getPowerFinishRate() {
		return powerFinishRate;
	}

	public void setPowerFinishRate(String powerFinishRate) {
		this.powerFinishRate = powerFinishRate;
	}

}

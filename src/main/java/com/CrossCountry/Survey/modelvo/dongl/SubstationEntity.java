package com.CrossCountry.Survey.modelvo.dongl;

public class SubstationEntity {
	String subUndeploy;// 变电站未部署数量
	String subDeploy;// 变电站实际部署数量
	String subSum;// 变电站应部署总数
	String subFinishRate;// 变电站部署完成比例

	public String getSubUndeploy() {
		return subUndeploy;
	}

	public void setSubUndeploy(String subUndeploy) {
		this.subUndeploy = subUndeploy;
	}

	public String getSubDeploy() {
		return subDeploy;
	}

	public void setSubDeploy(String subDeploy) {
		this.subDeploy = subDeploy;
	}

	public String getSubSum() {
		return subSum;
	}

	public void setSubSum(String subSum) {
		this.subSum = subSum;
	}

	public String getSubFinishRate() {
		return subFinishRate;
	}

	public void setSubFinishRate(String subFinishRate) {
		this.subFinishRate = subFinishRate;
	}

}

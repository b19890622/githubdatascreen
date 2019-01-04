package com.CrossCountry.Survey.modelvo.dongl;

import java.util.List;

public class MainStationAlarmDistributionReplyVO {
	List<MainStationAlarmDistributionEntity> mainStationAlarmDistributionList;

	DistributionBannerEntity distributionBannerEntity;

	public List<MainStationAlarmDistributionEntity> getMainStationAlarmDistributionList() {
		return mainStationAlarmDistributionList;
	}

	public void setMainStationAlarmDistributionList(
			List<MainStationAlarmDistributionEntity> mainStationAlarmDistributionList) {
		this.mainStationAlarmDistributionList = mainStationAlarmDistributionList;
	}

	public DistributionBannerEntity getDistributionBannerEntity() {
		return distributionBannerEntity;
	}

	public void setDistributionBannerEntity(DistributionBannerEntity distributionBannerEntity) {
		this.distributionBannerEntity = distributionBannerEntity;
	}

}

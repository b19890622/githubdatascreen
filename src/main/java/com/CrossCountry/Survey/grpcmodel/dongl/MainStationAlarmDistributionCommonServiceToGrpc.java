package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.DistributionBannerEntity;
import com.CrossCountry.Survey.modelvo.dongl.MainStationAlarmDistributionEntity;
import com.CrossCountry.Survey.modelvo.dongl.MainStationAlarmDistributionReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.DistributionBanner;
import com.datascreen.MainStationAlarmDistribution;
import com.datascreen.MainStationAlarmDistributionReply;





public class MainStationAlarmDistributionCommonServiceToGrpc {
	public MainStationAlarmDistributionReply getMainStationAlarmDistributionReply(Object object) {
		MainStationAlarmDistributionReply.Builder response = MainStationAlarmDistributionReply.newBuilder();
		MainStationAlarmDistributionReplyVO mainStationAlarmDistributionReplyVO =new MainStationAlarmDistributionReplyVO();
		if (null != object) {
			mainStationAlarmDistributionReplyVO = (MainStationAlarmDistributionReplyVO) object;
		}
		List<MainStationAlarmDistributionEntity> mainStationAlarmDistributionList =mainStationAlarmDistributionReplyVO.getMainStationAlarmDistributionList();
		DistributionBannerEntity distributionBannerEntity = mainStationAlarmDistributionReplyVO.getDistributionBannerEntity();
		if(null == mainStationAlarmDistributionList){
			mainStationAlarmDistributionList = new ArrayList<MainStationAlarmDistributionEntity>();
		}
		if(null == distributionBannerEntity){
			distributionBannerEntity = new DistributionBannerEntity();
		}
		List<MainStationAlarmDistribution> mainStationADList = new ArrayList<MainStationAlarmDistribution>();
		
		try {
			for(MainStationAlarmDistributionEntity mainStationAlarmDistributionEntity:mainStationAlarmDistributionList){
				MainStationAlarmDistribution mainStationAlarmDistribution = (MainStationAlarmDistribution) TransformToGrpcPo
						.convertToPojo(MainStationAlarmDistribution.class, mainStationAlarmDistributionEntity);
				mainStationADList.add(mainStationAlarmDistribution);
			}
			
			if(mainStationADList.size()>0){
				response.addAllMainStationAlarmDistribution(mainStationADList);
			}
			DistributionBanner distributionBanner = (DistributionBanner) TransformToGrpcPo.convertToPojo(DistributionBanner.class,
					distributionBannerEntity);
			response.setDistributionBanner(distributionBanner);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(MainStationAlarmDistributionCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

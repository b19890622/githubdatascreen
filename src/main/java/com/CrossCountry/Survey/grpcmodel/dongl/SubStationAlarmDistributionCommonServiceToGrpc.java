package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.SubStationAlarmDistributionEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubStationAlarmDistributionReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.SubStationAlarmDistribution;
import com.datascreen.SubStationAlarmDistributionReply;

public class SubStationAlarmDistributionCommonServiceToGrpc {
	public SubStationAlarmDistributionReply getSubStationAlarmDistributionReply(Object object) {
		SubStationAlarmDistributionReply.Builder response = SubStationAlarmDistributionReply.newBuilder();
		SubStationAlarmDistributionReplyVO subStationAlarmDistributionReplyVO = new SubStationAlarmDistributionReplyVO();
		if (null != object) {
			subStationAlarmDistributionReplyVO = (SubStationAlarmDistributionReplyVO) object;
		}
		List<SubStationAlarmDistributionEntity> subStationAlarmDistributionList = subStationAlarmDistributionReplyVO
				.getSubStationAlarmDistributionList();
		if (null == subStationAlarmDistributionList) {
			subStationAlarmDistributionList = new ArrayList<SubStationAlarmDistributionEntity>();
		}

		List<SubStationAlarmDistribution> subStationADList = new ArrayList<SubStationAlarmDistribution>();

		try {
			for (SubStationAlarmDistributionEntity subStationAlarmDistributionEntity : subStationAlarmDistributionList) {
				SubStationAlarmDistribution subStationAlarmDistribution = (SubStationAlarmDistribution) TransformToGrpcPo
						.convertToPojo(SubStationAlarmDistribution.class, subStationAlarmDistributionEntity);
				subStationADList.add(subStationAlarmDistribution);
			}

			if (subStationADList.size() > 0) {
				response.addAllSubStationAlarmDistribution(subStationADList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(SubStationAlarmDistributionCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

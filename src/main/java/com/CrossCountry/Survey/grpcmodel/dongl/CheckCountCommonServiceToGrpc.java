package com.CrossCountry.Survey.grpcmodel.dongl;

import com.CrossCountry.Survey.modelvo.dongl.CheckCountReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.CheckCountReply;

public class CheckCountCommonServiceToGrpc {
	public CheckCountReply getCheckCountReply(Object object) {
		CheckCountReply.Builder response = CheckCountReply.newBuilder();
		CheckCountReplyVO CheckCountReplyVO =new CheckCountReplyVO();
		if (null != object) {
			CheckCountReplyVO = (CheckCountReplyVO) object;
		}
		String uncompliancePo = CheckCountReplyVO.getUnCompliance();
		String safetyFlawPo = CheckCountReplyVO.getSafetyFlaw();
		String weakPassPo = CheckCountReplyVO.getWeakPass();
		String unComPercent = CheckCountReplyVO.getUnComPercent();
		String SafetyPercent = CheckCountReplyVO.getSafetyPercent();
		String getWeakPassPercent = CheckCountReplyVO.getWeakPassPercent();
		try {
			response.setUnCompliance(uncompliancePo);
			response.setSafetyFlaw(safetyFlawPo);
			response.setWeakPass(weakPassPo);
			response.setUnComPercent(unComPercent);
			response.setSafetyPercent(SafetyPercent);
			response.setWeakPassPercent(getWeakPassPercent);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CheckCountCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

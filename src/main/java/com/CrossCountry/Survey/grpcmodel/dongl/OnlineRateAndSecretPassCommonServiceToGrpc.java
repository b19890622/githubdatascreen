package com.CrossCountry.Survey.grpcmodel.dongl;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.OnlineRateAnadSecretPassVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.OnlineRateAndSecretPass;
import com.datascreen.OnlineRateAndSecretPassReply;

public class OnlineRateAndSecretPassCommonServiceToGrpc {
	public OnlineRateAndSecretPassReply getOnlineRateAnadSecretPass(Object object) {
		OnlineRateAndSecretPassReply.Builder response = OnlineRateAndSecretPassReply.newBuilder();
		OnlineRateAnadSecretPassVO safetyComplianceVo = (OnlineRateAnadSecretPassVO) object;
		try {
			OnlineRateAndSecretPass onlineRateAndSecretPass = (OnlineRateAndSecretPass) TransformToGrpcPo.convertToPojo(OnlineRateAndSecretPass.class,
					safetyComplianceVo);
			response.setOnelineRateAndSecretPass(onlineRateAndSecretPass);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(OnlineRateAndSecretPassCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

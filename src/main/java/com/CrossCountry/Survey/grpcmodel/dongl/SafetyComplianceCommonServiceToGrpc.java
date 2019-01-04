package com.CrossCountry.Survey.grpcmodel.dongl;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.SafetyComplianceVo;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.SafetyCompliance;
import com.datascreen.SafetyComplianceReply;

public class SafetyComplianceCommonServiceToGrpc {
	public SafetyComplianceReply getSafetyComplianceReply(Object object) {
		SafetyComplianceReply.Builder response = SafetyComplianceReply.newBuilder();
		SafetyComplianceVo safetyComplianceVo = new SafetyComplianceVo();
		try {
			if (object != null) {
				safetyComplianceVo = (SafetyComplianceVo) object;
				SafetyCompliance safetyCompliance = (SafetyCompliance) TransformToGrpcPo
						.convertToPojo(SafetyCompliance.class, safetyComplianceVo);
				response.setSafetyCompliance(safetyCompliance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(AlarmMonitorCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

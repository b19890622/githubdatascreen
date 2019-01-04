package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.Compliance;
import com.datascreen.ComplianceReply;
import com.datascreen.Protection;
import com.datascreen.ProtectionNumberReply;

public class ComplianceNameAndNumToGrpc {

	public ComplianceReply complianceTogrpc(Object object) {

		ComplianceReply.Builder response = ComplianceReply.newBuilder();
		List<CompliancePo> complianceNumbers = (List<CompliancePo>) object;
		List<Compliance> Numbers = new ArrayList<>();
		try {

		for (CompliancePo compliancePo : complianceNumbers) {
			
			Compliance change = (Compliance) TransformToGrpcPo.convertToPojo(Compliance.class, compliancePo);
			
				Numbers.add(change);
				
		}
		if (null != Numbers && Numbers.size() > 0) {
			
			response.addAllCompliance(Numbers);
		
		   }

		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

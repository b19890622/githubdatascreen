package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.AccessControlsPo;
import com.CrossCountry.Survey.modelvo.donggs.VisitTimesPo;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenEntity;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AccessControls;
import com.datascreen.AccessControlsReply;
import com.datascreen.SecretTopTen;
import com.datascreen.SecretTopTenReply;
import com.datascreen.VisitTimes;
import com.datascreen.VisitTimesReply;

public class AccessControlsServiceToGrpc {
	public AccessControlsReply getAccessControlsReply(Object object) {
		AccessControlsReply.Builder response = AccessControlsReply.newBuilder();
		List<AccessControlsPo> accessControlsPos = (List<AccessControlsPo>) object;
		List<AccessControls> accessControls =new ArrayList<>();
		try {
		
		for (AccessControlsPo accessControlsPo : accessControlsPos) {
			AccessControls visitTime = (AccessControls)TransformToGrpcPo
					.convertToPojo(AccessControls.class, accessControlsPo);
			accessControls.add(visitTime);
		}
		if(accessControls.size()>0 && accessControls != null){
			response.addAllAccessControls(accessControls);
			
		}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(AccessControlsServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

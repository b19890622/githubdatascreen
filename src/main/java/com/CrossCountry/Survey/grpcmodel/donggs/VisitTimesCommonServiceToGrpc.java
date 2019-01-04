package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.VisitTimesPo;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenEntity;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.SecretTopTen;
import com.datascreen.SecretTopTenReply;
import com.datascreen.VisitTimes;
import com.datascreen.VisitTimesReply;

public class VisitTimesCommonServiceToGrpc {
	public VisitTimesReply getVisitTimesPo(Object object) {
		VisitTimesReply.Builder response = VisitTimesReply.newBuilder();
		List<VisitTimesPo> visitTimesPos = (List<VisitTimesPo>) object;
		List<VisitTimes> visitTimes =new ArrayList<>();
		try {
		
		for (VisitTimesPo visitTimesPo : visitTimesPos) {
			VisitTimes visitTime = (VisitTimes) TransformToGrpcPo
					.convertToPojo(VisitTimes.class, visitTimesPo);
			visitTimes.add(visitTime);
		}
		if(visitTimes.size()>0 && visitTimes != null){
			response.addAllVisitTimes(visitTimes);
		}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(VisitTimesCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

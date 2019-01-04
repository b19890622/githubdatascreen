package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.SecurityAnalasisEntity;
import com.CrossCountry.Survey.modelvo.dongl.SecurityAnalasisReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.SecurityAnalasis;
import com.datascreen.SecurityAnalasisReply;

public class SecurityAnalasisCommonServiceToGrpc {
	public SecurityAnalasisReply getSecurityAnalasisReply(Object object) {
		SecurityAnalasisReply.Builder response = SecurityAnalasisReply.newBuilder();
		SecurityAnalasisReplyVO securityAnalasisReplyVO =new SecurityAnalasisReplyVO();
		if (null != object) {
			securityAnalasisReplyVO = (SecurityAnalasisReplyVO) object;
		}
		List<SecurityAnalasisEntity> a =securityAnalasisReplyVO.getSecurityAnalasis();
		if(null == a){
			a = new ArrayList<SecurityAnalasisEntity>();
		}
		List<SecurityAnalasis> securityAnalasislist  = new ArrayList<SecurityAnalasis>();
		
		try {
			for(SecurityAnalasisEntity securityAnalasisEntity:a){
				SecurityAnalasis securityAnalasis = (SecurityAnalasis) TransformToGrpcPo
						.convertToPojo(SecurityAnalasis.class, securityAnalasisEntity);
				securityAnalasislist.add(securityAnalasis);
			}
			if(securityAnalasislist.size()>0){
				response.addAllSecurityAnalasis(securityAnalasislist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(SecurityAnalasisCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

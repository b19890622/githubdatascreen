package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenEntity;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.SecretTopTen;
import com.datascreen.SecretTopTenReply;

public class SecretTopTenCommonServiceToGrpc {
	public SecretTopTenReply getSecretTopTenReply(Object object) {
		SecretTopTenReply.Builder response = SecretTopTenReply.newBuilder();
		List<SecretTopTenEntity> secretTopTenEntityss = (List<SecretTopTenEntity>) object;
		List<SecretTopTen> secretTopTens = new ArrayList<>();
		try {
		
		for (SecretTopTenEntity secretTopTenReply : secretTopTenEntityss) {
			SecretTopTen secretTopTen = (SecretTopTen) TransformToGrpcPo
					.convertToPojo(SecretTopTen.class, secretTopTenReply);
			secretTopTens.add(secretTopTen);
		}
		if(secretTopTens.size()>0){
			response.addAllSecretTopTen(secretTopTens);
		}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(SecretTopTenCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

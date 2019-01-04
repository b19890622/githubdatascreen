package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.LeftSecurityFlawReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.NumOneAreaLeftEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumThreeAreaLeftEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumTwoAreaLeftEntity;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.LeftSecurityFlawReply;
import com.datascreen.NumOneAreaLeft;
import com.datascreen.NumThreeAreLeft;
import com.datascreen.NumTwoAreaLeft;





public class LeftSecurityFlawCommonServiceToGrpc {
	public LeftSecurityFlawReply getLeftSecurityFlawReply(Object object) {
		LeftSecurityFlawReply.Builder response = LeftSecurityFlawReply.newBuilder();
		LeftSecurityFlawReplyVO leftSecurityFlawReplyVO =new LeftSecurityFlawReplyVO();
		if (null != object) {
			leftSecurityFlawReplyVO = (LeftSecurityFlawReplyVO) object;
		}
		List<NumOneAreaLeftEntity> numOne =leftSecurityFlawReplyVO.getNumOneAreaLeftList();
		if(null == numOne){
			numOne = new ArrayList<NumOneAreaLeftEntity>();
		}
		
		List<NumTwoAreaLeftEntity> numTwo =leftSecurityFlawReplyVO.getNumTwoAreaLeftList();
		if(null == numTwo){
			numTwo = new ArrayList<NumTwoAreaLeftEntity>();
		}
		
		List<NumThreeAreaLeftEntity> numThree =leftSecurityFlawReplyVO.getNumThreeAreaLeftList();
		if(null == numThree){
			numThree = new ArrayList<NumThreeAreaLeftEntity>();
		}
		
		
		List<NumOneAreaLeft> numOnelist  = new ArrayList<NumOneAreaLeft>();
		List<NumTwoAreaLeft> numTwolist  = new ArrayList<NumTwoAreaLeft>();
		List<NumThreeAreLeft> numThreelist  = new ArrayList<NumThreeAreLeft>();
		
		try {
			for(NumOneAreaLeftEntity numOneAreaLeftEntity:numOne){
				NumOneAreaLeft numOneAreaLeft = (NumOneAreaLeft) TransformToGrpcPo
						.convertToPojo(NumOneAreaLeft.class, numOneAreaLeftEntity);
				numOnelist.add(numOneAreaLeft);
			}
			
			for(NumTwoAreaLeftEntity numTwoAreaLeftEntity:numTwo){
				NumTwoAreaLeft numTwoAreaLeft = (NumTwoAreaLeft) TransformToGrpcPo
						.convertToPojo(NumTwoAreaLeft.class, numTwoAreaLeftEntity);
				numTwolist.add(numTwoAreaLeft);
			}
			for(NumThreeAreaLeftEntity numThreeAreaLeftEntity:numThree){
				NumThreeAreLeft numThreeAreaLeft = (NumThreeAreLeft) TransformToGrpcPo
						.convertToPojo(NumThreeAreLeft.class, numThreeAreaLeftEntity);
				numThreelist.add(numThreeAreaLeft);
			}
			if(numOnelist.size()>0){
				response.addAllNumOneAreaLeft(numOnelist);
			}
			if(numTwolist.size()>0){
				response.addAllNumTwoAreaLeft(numTwolist);
			}
			if(numThreelist.size()>0){
				response.addAllNumThreeAreaLeft(numThreelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(LeftSecurityFlawCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

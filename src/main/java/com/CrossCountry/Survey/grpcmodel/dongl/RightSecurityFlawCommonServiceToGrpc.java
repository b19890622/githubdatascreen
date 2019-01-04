package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.RightSecurityFlawReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.NumOneAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumThreeAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumTwoAreaRightEntity;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.RightSecurityFlawReply;
import com.datascreen.NumOneAreaRight;
import com.datascreen.NumThreeAreRight;
import com.datascreen.NumTwoAreaRight;

public class RightSecurityFlawCommonServiceToGrpc {
	public RightSecurityFlawReply getRightSecurityFlawReply(Object object) {
		RightSecurityFlawReply.Builder response = RightSecurityFlawReply.newBuilder();
		RightSecurityFlawReplyVO rightSecurityFlawReplyVO = new RightSecurityFlawReplyVO();
		if (null != object) {
			rightSecurityFlawReplyVO = (RightSecurityFlawReplyVO) object;
		}
		List<NumOneAreaRightEntity> numOne = rightSecurityFlawReplyVO.getNumOneAreaRightList();
		if (null == numOne) {
			numOne = new ArrayList<NumOneAreaRightEntity>();
		}

		List<NumTwoAreaRightEntity> numTwo = rightSecurityFlawReplyVO.getNumTwoAreaRightList();
		if (null == numTwo) {
			numTwo = new ArrayList<NumTwoAreaRightEntity>();
		}

		List<NumThreeAreaRightEntity> numThree = rightSecurityFlawReplyVO.getNumThreeAreaRightList();
		if (null == numThree) {
			numThree = new ArrayList<NumThreeAreaRightEntity>();
		}

		List<NumOneAreaRight> numOnelist = new ArrayList<NumOneAreaRight>();
		List<NumTwoAreaRight> numTwolist = new ArrayList<NumTwoAreaRight>();
		List<NumThreeAreRight> numThreelist = new ArrayList<NumThreeAreRight>();

		try {
			for (NumOneAreaRightEntity numOneAreaRightEntity : numOne) {
				NumOneAreaRight numOneAreaRight = (NumOneAreaRight) TransformToGrpcPo
						.convertToPojo(NumOneAreaRight.class, numOneAreaRightEntity);
				numOnelist.add(numOneAreaRight);
			}

			for (NumTwoAreaRightEntity numTwoAreaRightEntity : numTwo) {
				NumTwoAreaRight numTwoAreaRight = (NumTwoAreaRight) TransformToGrpcPo
						.convertToPojo(NumTwoAreaRight.class, numTwoAreaRightEntity);
				numTwolist.add(numTwoAreaRight);
			}
			for (NumThreeAreaRightEntity numThreeAreaRightEntity : numThree) {
				NumThreeAreRight numThreeAreaRight = (NumThreeAreRight) TransformToGrpcPo
						.convertToPojo(NumThreeAreRight.class, numThreeAreaRightEntity);
				numThreelist.add(numThreeAreaRight);
			}
			if (numOnelist.size() > 0) {
				response.addAllNumOneAreaRight(numOnelist);
			}
			if (numTwolist.size() > 0) {
				response.addAllNumTwoAreaRight(numTwolist);
			}
			if (numThreelist.size() > 0) {
				response.addAllNumThreeAreaRight(numThreelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(RightSecurityFlawCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

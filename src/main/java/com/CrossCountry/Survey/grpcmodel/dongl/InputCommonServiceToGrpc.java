package com.CrossCountry.Survey.grpcmodel.dongl;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.InputAreaEntity;
import com.CrossCountry.Survey.modelvo.dongl.InputAreaNumEntity;
import com.CrossCountry.Survey.modelvo.dongl.InputAreaPercentEntity;
import com.CrossCountry.Survey.modelvo.dongl.InputCommonReplyVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.InputAreaNum;
import com.datascreen.InputAreaPercent;
import com.datascreen.InputReply;

public class InputCommonServiceToGrpc {
	public InputReply getInputReply(Object object) {
		InputReply.Builder response = InputReply.newBuilder();
		InputCommonReplyVO inputCommonReplyVO = new InputCommonReplyVO();
		if (null != object) {
			inputCommonReplyVO = (InputCommonReplyVO) object;
		}
		InputAreaNumEntity inputAreaNumEntity = inputCommonReplyVO.getInputAreaNumEntity();
		InputAreaPercentEntity inputAreaPercentEntity = inputCommonReplyVO.getInputAreaPercentEntity();
		

		try {
			InputAreaNum inputAreaNum = (InputAreaNum) TransformToGrpcPo.convertToPojo(InputAreaNum.class,
					inputAreaNumEntity);
			InputAreaPercent inputAreaPercent = (InputAreaPercent) TransformToGrpcPo
					.convertToPojo(InputAreaPercent.class, inputAreaPercentEntity);
			response.setInputAreaNum(inputAreaNum);
			response.setInputAreaPercent(inputAreaPercent);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(AlarmMonitorCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();

	}
}

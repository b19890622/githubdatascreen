package com.CrossCountry.Survey.grpcmodel.donggs;

import org.springframework.beans.factory.annotation.Autowired;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceGuanKongService;
import com.CrossCountry.Survey.modelvo.donggs.GuangKongNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.NumberReply;

public class GetNumberToGrpc {
	@Autowired
	CommonServiceGuanKongService commonServiceGuanKongService;

	public NumberReply getNumberTogrpc(Object object) {

		NumberReply.Builder response = NumberReply.newBuilder();
		GuangKongNumber guangKongNumber = (GuangKongNumber) object;
		try {
			response.setBID(guangKongNumber.getBID());
			response.setFID(guangKongNumber.getFID());
			response.setFW(guangKongNumber.getFW());
			response.setIDS(guangKongNumber.getIDS());
			response.setVEAD(guangKongNumber.getVEAD());
		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(GetNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();

	}
}

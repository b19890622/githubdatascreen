package com.CrossCountry.Survey.grpcmodel.wangkun;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventList;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventResponse;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.SecurityEventGrpc;
import com.datascreen.SecurityEventReply;

public class CommonServiceSecurityEventToGrpc {
	/**
	 *
	 * @param object 普通结果集
	 * @return grpc结果集
	 */
	public SecurityEventReply getResponse(Object object) {
		SecurityEventReply.Builder response = SecurityEventReply.newBuilder();
		SecurityEventResponse eventResponse = (SecurityEventResponse) object;
		List<SecurityEventList> commonList = eventResponse.getSecurityEventVoList();
		long nums = eventResponse.getNums();
		long toSolvedNums = eventResponse.getNums();
		try {
			SecurityEventGrpc tempGrpc = null;
			List<SecurityEventGrpc> grpcList = new ArrayList<>();
			for (SecurityEventList tempCommon : commonList) {
				tempGrpc = (SecurityEventGrpc) TransformToGrpcPo.convertToPojo(SecurityEventGrpc.class, tempCommon);
				grpcList.add(tempGrpc);
			}
			response.setNums(nums);
			response.setToSolvedNums(toSolvedNums);
			response.addAllSecurityEvents(grpcList);

		} catch (Exception e) {
			Log4jUtil.error(CommonServiceSecurityEventToGrpc.class, "结果集转换错误！");
			e.printStackTrace();
		}
		return response.build();
	}

	public SecurityEventReply getSubResponse(Object object) {
		SecurityEventReply.Builder response = SecurityEventReply.newBuilder();
		SecurityEventResponse eventResponse = (SecurityEventResponse) object;
		List<SecurityEventList> commonList = eventResponse.getSecurityEventVoList();
		long nums = eventResponse.getNums();
		long toSolvedNums = eventResponse.getToSolvedNums();
		try {
			SecurityEventGrpc tempGrpc = null;
			List<SecurityEventGrpc> grpcList = new ArrayList<>();
			for (SecurityEventList tempCommon : commonList) {
				tempGrpc = (SecurityEventGrpc) TransformToGrpcPo.convertToPojo(SecurityEventGrpc.class, tempCommon);
				grpcList.add(tempGrpc);
			}
			response.setNums(nums);
			response.setToSolvedNums(toSolvedNums);
			response.addAllSecurityEvents(grpcList);

		} catch (Exception e) {
			Log4jUtil.error(CommonServiceSecurityEventToGrpc.class, "结果集转换错误！");
			e.printStackTrace();
		}
		return response.build();
	}
}

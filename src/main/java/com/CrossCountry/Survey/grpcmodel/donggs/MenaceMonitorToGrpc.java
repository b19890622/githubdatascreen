package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.AllData;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AllDateArrayProtobuf;
import com.datascreen.AllDateReply;

public class MenaceMonitorToGrpc {

	public AllDateReply getMenaceMonitorReplyToGrpc(Object object) {
		AllDateReply.Builder response = AllDateReply.newBuilder();
		List<AllData> menaceMonitor = (List<AllData>) object;

		if (null == menaceMonitor) {
			menaceMonitor = new ArrayList<>();
		}

		List<AllDateArrayProtobuf> allDateLists = new ArrayList<>();

		try {
			for (AllData allDateList : menaceMonitor) {

				AllDateArrayProtobuf changeRed = (AllDateArrayProtobuf) TransformToGrpcPo
						.convertToPojo(AllDateArrayProtobuf.class, allDateList);
				allDateLists.add(changeRed);
			}
			if (null != allDateLists && allDateLists.size() > 0) {
				response.addAllAllDateArrayProtobuf(allDateLists);
			}

		} catch (Exception e) {
			Log4jUtil.error(MenaceMonitorToGrpc.class, e.getMessage(), e);
		}

		return response.build();

	}
}
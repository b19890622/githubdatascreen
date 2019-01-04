package com.CrossCountry.Survey.grpcmodel.dongl;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.AlarmMonitorAllVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AlarmMonitorAll;
import com.datascreen.RunAllStateReply;

public class AlarmMonitorAllCommonServiceToGrpc {
	public RunAllStateReply getRunAllStateReply(Object object) {
		RunAllStateReply.Builder response = RunAllStateReply.newBuilder();
		AlarmMonitorAllVO alarmMonitorAllVOpojo = (AlarmMonitorAllVO) object;
		try {
			AlarmMonitorAll alarmMonitorAll = (AlarmMonitorAll) TransformToGrpcPo.convertToPojo(AlarmMonitorAll.class,
					alarmMonitorAllVOpojo);
			response.setAlrmMonitorAll(alarmMonitorAll);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(AlarmMonitorCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

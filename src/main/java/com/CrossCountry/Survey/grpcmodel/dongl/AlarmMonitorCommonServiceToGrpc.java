package com.CrossCountry.Survey.grpcmodel.dongl;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.AlarmMonitorLocalVo;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AlarmMonitorVO;
import com.datascreen.RunStateReply;

public class AlarmMonitorCommonServiceToGrpc {
	public RunStateReply getRunStateReply(Object object) {
		RunStateReply.Builder response = RunStateReply.newBuilder();
		AlarmMonitorLocalVo alarmMonitorLocalVo = (AlarmMonitorLocalVo) object;
		try {
			AlarmMonitorVO alarmMonitorVO = (AlarmMonitorVO) TransformToGrpcPo.convertToPojo(AlarmMonitorVO.class,
					alarmMonitorLocalVo);
			response.setAlrmMonitor(alarmMonitorVO);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(AlarmMonitorCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}

}

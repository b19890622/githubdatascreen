package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.AlarmEquipmentPo;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.ComplianceStatisticsPo;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AlarmEquipment;
import com.datascreen.AlarmEquipmentTopFiveReply;
import com.datascreen.Compliance;
import com.datascreen.ComplianceReply;
import com.datascreen.ComplianceStatistics;
import com.datascreen.ComplianceStatisticsReply;
import com.datascreen.Protection;
import com.datascreen.ProtectionNumberReply;

public class AlarmEquipmentTopFiveToGrpc {

	public AlarmEquipmentTopFiveReply alarmEquipmentTopFiveTogrpc(Object object) {

		AlarmEquipmentTopFiveReply.Builder response = AlarmEquipmentTopFiveReply.newBuilder();
		List<AlarmEquipmentPo> alarmEquipmentPoNumbers = (List<AlarmEquipmentPo>) object;
		List<AlarmEquipment> Numbers = new ArrayList<>();
		
		try {
		for (AlarmEquipmentPo alarmEquipmentPo: alarmEquipmentPoNumbers) {
			
			AlarmEquipment change = (AlarmEquipment) TransformToGrpcPo.convertToPojo(AlarmEquipment.class, alarmEquipmentPo);
			
				Numbers.add(change);
				
		}
		if (null != Numbers && Numbers.size() > 0) {
			
			response.addAllAlarmEquipment(Numbers);
		
		   }

		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

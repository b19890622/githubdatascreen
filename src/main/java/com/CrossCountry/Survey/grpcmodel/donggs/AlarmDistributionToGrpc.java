package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionEquipmentAndSafe;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionEquipmentPo;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionSafePo;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.ComplianceStatisticsPo;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AlarmDistributionEquipment;
import com.datascreen.AlarmDistributionReply;
import com.datascreen.AlarmDistributionSafe;
import com.datascreen.Compliance;
import com.datascreen.ComplianceReply;
import com.datascreen.ComplianceStatistics;
import com.datascreen.ComplianceStatisticsReply;
import com.datascreen.Protection;
import com.datascreen.ProtectionNumberReply;

public class AlarmDistributionToGrpc {

	public AlarmDistributionReply alarmDistributionReplyTogrpc(Object object) {

		AlarmDistributionReply.Builder response = AlarmDistributionReply.newBuilder();
		AlarmDistributionEquipmentAndSafe complianceStatisticsNumbers = (AlarmDistributionEquipmentAndSafe) object;
		List<AlarmDistributionSafe> safes = new ArrayList<>();
		List<AlarmDistributionEquipment> equipments = new ArrayList<>();
		try {
			
		for (AlarmDistributionEquipmentPo complianceStatistics: complianceStatisticsNumbers.getEquipment()) {
			if ("主机".equals(complianceStatistics.getAlarmDistributionEquipmentName())) {
				complianceStatistics.setAlarmDistributionEquipmentName("主机设备");
			}
			AlarmDistributionEquipment change = (AlarmDistributionEquipment) TransformToGrpcPo.convertToPojo(AlarmDistributionEquipment.class, complianceStatistics);
			
			equipments.add(change);
				
		}
		
       for (AlarmDistributionSafePo complianceStatistics: complianceStatisticsNumbers.getSafe()) {
			
    	   AlarmDistributionSafe change = (AlarmDistributionSafe) TransformToGrpcPo.convertToPojo(AlarmDistributionSafe.class, complianceStatistics);
			
    	   safes.add(change);
				
		}
       
		if (null != equipments && equipments.size() > 0) {
			
			response.addAllAlarmDistributionEquipment(equipments);
			
		   }
        if (null != safes && safes.size() > 0) {
        	
			response.addAllAlarmDistributionSafe(safes);
			
		   }
		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		
		}
		return response.build();
	}
}

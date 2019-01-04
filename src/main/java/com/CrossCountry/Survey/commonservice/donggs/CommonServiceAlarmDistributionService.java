package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.AlarmDistributionDao;
import com.CrossCountry.Survey.mapper.donggs.ComplianceNameAndNumDao;
import com.CrossCountry.Survey.mapper.donggs.ComplianceStatisticsDao;
import com.CrossCountry.Survey.mapper.donggs.ProtectionNumberDao;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionEquipmentAndSafe;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionEquipmentPo;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionSafePo;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.ComplianceStatisticsPo;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
@Component
public class CommonServiceAlarmDistributionService {
	@Autowired
	private AlarmDistributionDao alarmDistributionDao;
	
	public AlarmDistributionEquipmentAndSafe getAlarmDistribution( Object obj){
		AlarmDistributionEquipmentAndSafe equipmentAndSafe  = new AlarmDistributionEquipmentAndSafe();
		List<AlarmDistributionEquipmentPo> equipmentPo = alarmDistributionDao.getEquipmentPo();
		List<AlarmDistributionSafePo> safePo = alarmDistributionDao.getSafePo();
		equipmentAndSafe.setEquipment(equipmentPo);
		equipmentAndSafe.setSafe(safePo);
		return equipmentAndSafe ;
		
	}
	
}

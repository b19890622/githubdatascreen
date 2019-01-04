package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionEquipmentPo;
import com.CrossCountry.Survey.modelvo.donggs.AlarmDistributionSafePo;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.ComplianceStatisticsPo;


@Mapper
public interface AlarmDistributionDao {
	
	public List<AlarmDistributionEquipmentPo> getEquipmentPo();
	public List<AlarmDistributionSafePo> getSafePo();
	
}

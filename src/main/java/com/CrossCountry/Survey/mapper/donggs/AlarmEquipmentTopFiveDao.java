package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.AlarmEquipmentPo;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;


@Mapper
public interface AlarmEquipmentTopFiveDao {
	
	public List<AlarmEquipmentPo> getAlarmEquipmentTopFive();
	
}

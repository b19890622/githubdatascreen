package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.EquipmentNumber;

@Mapper
public interface EquipmentNumberDao {
	
	public List<EquipmentNumber> getEquipment();
	
}

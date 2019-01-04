package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.EquipmentNumberDao;
import com.CrossCountry.Survey.modelvo.donggs.EquipmentNumber;
@Component
public class CommonServiceEquipmentNumberService {
	@Autowired
	private EquipmentNumberDao equipmentNumberDao;
	
	public List<EquipmentNumber> getEquipment( Object obj){
		return equipmentNumberDao.getEquipment();
	}
	
}

package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.TerraceDeviceNumberDao;
import com.CrossCountry.Survey.modelvo.donggs.JustableNumber;
@Component
public class CommonServiceTerraceDeviceNumberService {
	@Autowired
	private TerraceDeviceNumberDao terraceDeviceNumberDao;
	
	public List<JustableNumber>  getTerraceDevice( Object obj){
		return terraceDeviceNumberDao.getTerraceDevice();
	}
	
}

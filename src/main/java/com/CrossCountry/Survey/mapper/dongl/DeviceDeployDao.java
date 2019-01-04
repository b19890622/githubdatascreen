package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.dongl.DeviceDeployEntity;
import com.CrossCountry.Survey.modelvo.dongl.PowerPlantEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubstationEntity;


@Mapper
public interface DeviceDeployDao {
	public List<DeviceDeployEntity> getDeviceList();
	
	public SubstationEntity getSubstationEntity();
	
	public PowerPlantEntity getPowerPlantEntity();
	
	
}

package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.JustableNumber;

@Mapper
public interface TerraceDeviceNumberDao {
	
	public List<JustableNumber> getTerraceDevice();
	
}

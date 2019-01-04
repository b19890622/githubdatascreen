package com.CrossCountry.Survey.mapper.shichf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.shichf.ScreenDayRiskTOPTenEntity;

@Mapper
public interface ScreenDayRiskTOPTenDao {
	// 本级风险top10
	public List<ScreenDayRiskTOPTenEntity> getScreenDayRiskTOPTenEntityList();
	
	// 下级风险top10
	public List<ScreenDayRiskTOPTenEntity> getScreenDayRiskTOPTenEntitylowList(@Param("name") String name);
	
	// 发生威胁单位top10下级
	public List<ScreenDayRiskTOPTenEntity> getScreenDayRiskUnitTOPTenEntitylowList(@Param("name") String name);
	
	// 发生威胁单位top10本级
	public List<ScreenDayRiskTOPTenEntity> getScreenDayRiskUnitTOPTenEntityList();
}

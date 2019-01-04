package com.CrossCountry.Survey.mapper.donggs;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.GuangKongNumber;

@Mapper
public interface GaoJingDao {
	
	public GuangKongNumber getNumber();
	
}

package com.CrossCountry.Survey.mapper.donggs;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;

@Mapper
public interface ProtectionNumberDao {
	
	public Mpcvf getProtection();
	
}

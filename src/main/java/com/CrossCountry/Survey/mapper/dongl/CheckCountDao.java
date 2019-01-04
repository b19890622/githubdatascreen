package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.dongl.SafetyCheckEntity;

@Mapper
public interface CheckCountDao {

	public List<SafetyCheckEntity> getSafetyCheckEntity();
	
}

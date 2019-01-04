package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;


@Mapper
public interface ComplianceNameAndNumDao {
	
	public List<CompliancePo> getComplianceNameAndNum();
	
}

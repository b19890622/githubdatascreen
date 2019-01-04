package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.ComplianceStatisticsPo;


@Mapper
public interface ComplianceStatisticsDao {
	
	public List<ComplianceStatisticsPo> getComplianceStatistics();
	
}

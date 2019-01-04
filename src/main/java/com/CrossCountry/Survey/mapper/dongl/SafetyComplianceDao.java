package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.dongl.SafetyComplianceEntity;
import com.CrossCountry.Survey.modelvo.dongl.SafetyComplianceLocalVo;

@Mapper
public interface SafetyComplianceDao {
	public List<SafetyComplianceEntity> getSafetyComplianceSub(@Param("name") String name);

	public SafetyComplianceLocalVo getSafetyComplianceLocal();
	
}

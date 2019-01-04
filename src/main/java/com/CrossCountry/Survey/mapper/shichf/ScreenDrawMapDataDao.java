package com.CrossCountry.Survey.mapper.shichf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.shichf.LowLevelMapEntity;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatWarningArrayEntity;
import com.CrossCountry.Survey.modelvo.shichf.MapRegionName;
import com.CrossCountry.Survey.modelvo.shichf.SafetyRateLocalEntity;
import com.CrossCountry.Survey.modelvo.shichf.SafetyRateSubEntity;

@Mapper
public interface ScreenDrawMapDataDao {
	public List<LowLevelMapEntity> getLowLevelMapEntityList(@Param("name") String name);

	public List<MapFormatWarningArrayEntity> getMapFormatWarningArrayEntityList(@Param("name") String name);

	public List<SafetyRateLocalEntity> getSafetyRateLocal();

	public List<SafetyRateSubEntity> getSafetyRateSub(@Param("name") String name);

	public List<LowLevelMapEntity> getLowLevelMapEntityCityList(@Param("name") String name);

	public List<MapFormatWarningArrayEntity> getMapFormatWarningArrayEntityCityList(@Param("name") String name);

	public List<MapRegionName> getMapRegionNameList();

	public List<SafetyRateSubEntity> getSafetyRateSubByCenter();

	public List<LowLevelMapEntity> getLowLevelMapEntityListByCenter();

}

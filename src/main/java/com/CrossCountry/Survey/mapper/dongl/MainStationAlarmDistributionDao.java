package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.dongl.ViewMainStationBannerEntity;
import com.CrossCountry.Survey.modelvo.dongl.ViewMainStationEntity;


@Mapper
public interface MainStationAlarmDistributionDao {
	public List<ViewMainStationEntity> getViewMainStationEntity(@Param("curstart")String curstart,@Param("curend")String curend);
	
	public ViewMainStationBannerEntity getViewMainStationBannerEntity();
	
}


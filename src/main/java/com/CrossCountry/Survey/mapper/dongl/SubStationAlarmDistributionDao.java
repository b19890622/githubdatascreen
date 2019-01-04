
package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.dongl.ViewSubStationEntity;


@Mapper
public interface SubStationAlarmDistributionDao {
	public List<ViewSubStationEntity> getViewSubStationEntityList(@Param("curstart")String curstart,@Param("curend")String curend);
}


package com.CrossCountry.Survey.mapper.dongl;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMonitorLocalDao {
	
	public Integer getMethod1LocalNum();
	public Integer getMethod2LocalNum();
	public Integer getMethod3LocalNum();
	public Integer getMethod4LocalNum();
	public Integer getMethod5LocalNum();
	public Integer getMethod6LocalNum();
//	public List<AlarmMonitorLocalEntity> getAlarmMonitorLocal();

}

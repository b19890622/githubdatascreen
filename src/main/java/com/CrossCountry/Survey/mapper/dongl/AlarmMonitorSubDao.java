package com.CrossCountry.Survey.mapper.dongl;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface AlarmMonitorSubDao {
	
	public Integer getMethod1AllNum(@Param("name") String name);
	public Integer getMethod2AllNum(@Param("name") String name);
	public Integer getMethod3AllNum(@Param("name") String name);
	public Integer getMethod4AllNum(@Param("name") String name);
	public Integer getMethod5AllNum(@Param("name") String name);
	public Integer getMethod6AllNum(@Param("name") String name);
//	public List<AlarmMonitorSubEntity> getAlarmMonitorSub();
}

package com.CrossCountry.Survey.mapper.shichf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.shichf.CascadeStatusAuditPo;
import com.CrossCountry.Survey.modelvo.shichf.SecuritySystemDeviceEntity;

@Mapper
public interface ScreenSecuritySystemDeviceDataDao {
	public List<SecuritySystemDeviceEntity> getSecuritySystemDeviceEntitys(@Param("table") String table,
			@Param("name") String name);

	public List<CascadeStatusAuditPo> getCustomPlatformMonitoring(@Param("name") String name);

	public Integer getCheckTextOneCount();
	
	public List<CascadeStatusAuditPo> getCustomPlatformMonitoringProvince();

}

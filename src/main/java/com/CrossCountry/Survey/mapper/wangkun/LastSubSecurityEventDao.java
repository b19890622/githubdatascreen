package com.CrossCountry.Survey.mapper.wangkun;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.shichf.ProvinceSecurityEventToSolvedNumVO;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventVo;

@Mapper
public interface LastSubSecurityEventDao {
	public long getSubSecurityEventNum(@Param("name") String name);

	public List<SecurityEventVo> getTopOneHundredEvent(@Param("name") String name);
	
	public long getSubSecurityEventNumByDisSub(@Param("name") String name);

	public List<SecurityEventVo> getTopOneHundredEventByDisSub(@Param("name") String name);
	
	public long getSubSecurityEventToSolvedNum(@Param("name") String name);
	
	public long getSubSecurityEventSolvedNumByDisSub(@Param("name") String name);
	
	public List<ProvinceSecurityEventToSolvedNumVO> getSubSecurityEventToSolvedNumByCenter();
	
	public List<ProvinceSecurityEventToSolvedNumVO> getSubSecurityEventToSolvedNumByProvince();
	
}

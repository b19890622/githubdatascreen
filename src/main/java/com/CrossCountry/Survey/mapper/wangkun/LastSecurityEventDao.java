package com.CrossCountry.Survey.mapper.wangkun;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventCondition;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventVo;

@Mapper
public interface LastSecurityEventDao {
	// 获取最新安全事件的总数
	public int getSecurityEventNums();

	// 获取最新的100条告警事件
	public List<SecurityEventVo> getTopOneHundredEvent(@Param("condition") SecurityEventCondition condition);
	
	// 获取未解决最新安全事件的总数
	public int getSecurityEventToSolvedNums();

	
}

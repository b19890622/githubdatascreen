package com.CrossCountry.Survey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.UserInfoVO;
import com.CrossCountry.Survey.modelvo.liujg.OnDutyPersionVO;
import com.CrossCountry.Survey.modelvo.liujg.RiskWarnVO;

@Mapper
public interface CustomUserPesonDao {
	
	public UserInfoVO getUserInfoFirst();
	//获取值班人员列表
	public List<OnDutyPersionVO> getOnDutyPersion();
	//获取风险预警列表
	public List<RiskWarnVO> getRiskWarn();
	//获取风险预警个数
	public int getRiskWarnCount();

}

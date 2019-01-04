package com.CrossCountry.Survey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.CrossCountry.Survey.modelvo.liujg.TendencyOnWeekVO;
import com.CrossCountry.Survey.modelvo.liujg.ZxDeviceWarnVO;

@Mapper
public interface CustomBorderProDao {

	@Select("select a.name as regionName, ISNULL(b.noconfirm,0) as unSolveNum, ISNULL(b.confirm,0) as solveNum " + 
			"   from LSV_PROVINCE_REGIONAL_VIEW a left join LSV_GJXQ b on a.name = b.name")
	public List<ZxDeviceWarnVO> getBroderProVOMsg();
	
	@Select("select name as eventName, count as eventNum from LSV_SAFETYEVENTWEEK")
	public List<TendencyOnWeekVO> getTendencyOnWeek();
	
}

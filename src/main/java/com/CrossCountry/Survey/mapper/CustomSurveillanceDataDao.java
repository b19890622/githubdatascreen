package com.CrossCountry.Survey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.CrossCountry.Survey.modelvo.liujg.AllNetWorkMsgVO;

@Mapper
public interface CustomSurveillanceDataDao {

	@Select("select name as regionName, warning_num as solveStatSum,   " + 
			"   resolve_num as solvedNum, confirm_num as confirmNum  from LVS_WHOLENETWARNINGINFO_VIEW order by warning_num desc limit 6 ")
	public List<AllNetWorkMsgVO> getAllNetWorkMsg();
}

package com.CrossCountry.Survey.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.CrossCountry.Survey.modelvo.liujg.SurveillanceRegionVO;
import com.CrossCountry.Survey.modelvo.liujg.ZxSurveillanceLable;

@Mapper
public interface CustomVerificationDao {
	
	@Select("select TUNNEL_ILLEGAL as tunnelRate, " + 
			"       IP_ILLEGAL as ipRate, PORT_ILLEGAL as portRate, POLICY_ILLEGAL as scrRate from VIEW_QTTMCWARNING ")
	public ZxSurveillanceLable getZxSurveillanceMsg();
	
	@Select("select sum(number1) as oneRegion, sum(number2) as twoRegion, sum(number3) as threeRegion from LSV_HGQKTJ_VIEW ")
	public SurveillanceRegionVO getSurveillanceRegionMsg();

}

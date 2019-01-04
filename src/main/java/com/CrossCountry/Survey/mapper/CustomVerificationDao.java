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
	
	@Select("select number1 as oneRegion, number2 as twoRegion, number3 as threeRegion from LSV_HGQKTJ_VIEW where name='纵向不合规数' ")
	public SurveillanceRegionVO getSurveillanceRegionMsg();

}

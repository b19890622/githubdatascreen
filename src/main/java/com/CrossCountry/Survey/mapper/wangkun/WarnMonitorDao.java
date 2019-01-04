package com.CrossCountry.Survey.mapper.wangkun;

import com.CrossCountry.Survey.modelvo.wangkun.WarnParamVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WarnMonitorDao {
    @Select("select distinct to_char(reporttime,'yyyy-mm-dd') AS date,stime as period,warnnum as nums from   LSV_SAFEAMOUNT_CURVE\n" +
            "    where reporttime  in (#{today},#{yestoday}) \n" +
            "    and stime <= #{hour}  order by date desc ")
    public List<WarnParamVO> getWarnFigure(@Param("today") String today,@Param("yestoday") String yestoday,@Param("hour") int hour);


    @Select("select province_name AS name," +
            "content As content ," +
            "To_CHAR(warningtime) AS alarmTime, " +
            "confirmstate AS confirmstate," +
            "warninglevel AS alarmLevel " +
            "from LSV_RISKMON_VIEW where warningtime > #{time} order by warningtime desc limit 10")
    public List<WarnParamVO> getSecurityRisk(@Param("time") String time);




    @Select("select province_name AS name," +
            "content As content ," +
            "To_CHAR(warningtime) AS alarmTime, " +
            "confirmstate AS confirmstate," +
            "warninglevel AS alarmLevel " +
            "from LSV_RISKDEAL_VIEW where warningtime > #{time}  order  by warningtime desc limit 10 ")
    public List<WarnParamVO> getSecurityRiskDeal(@Param("time") String time);

}

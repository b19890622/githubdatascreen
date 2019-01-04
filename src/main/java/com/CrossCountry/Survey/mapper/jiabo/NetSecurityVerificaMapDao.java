package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.NetSecurityVerificaMapPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NetSecurityVerificaMapDao {
    @Select("SELECT \n" +
            "        LPR.NAME AS name, \n" +
            "        NVL(LS.CVSLEGALNUM,0)+NVL(LS.CVSILEGALNUM,0) AS noCompliance, \n" +
            "        NVL(LS.VBSNUM,0) AS leakage \n" +
            "FROM \n" +
            "        LSV_PROVINCE_REGIONAL_VIEW AS LPR \n" +
            "LEFT JOIN \n" +
            "        LSV_CHECKAMOUNT_TOP_VIEW AS LS \n" +
            "ON \n" +
            "        LPR.NAME = LS.LOCAL_NAME")
    public List<NetSecurityVerificaMapPo> getNetSecurityVerificaMap();

}

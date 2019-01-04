package com.CrossCountry.Survey.mapper.wangkun;

import com.CrossCountry.Survey.modelvo.wangkun.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/30 12:19
 */
@Mapper
public interface AccessAndHighRiskDao {
    @Select("select name AS devicename," +
            "USBCONNUM AS usb ," +
            " CDDRIVEUSAGE AS cdRom, " +
            "SERIALPORTUSAGE AS serialPort, " +
            "PARALLELPORTUSAGE AS parallelPort"+
            " from LSV_PERIPHERALACCESSINFO_VIEW   " +
            "where (USBCONNUM+CDDRIVEUSAGE+SERIALPORTUSAGE+PARALLELPORTUSAGE) >0 ")
    public List<AccessVO> getAccessList();
    @Select("select " +
            "isnull(sum(USBCONNUM),0) AS usbNum," +
            "isnull(sum(CDDRIVEUSAGE),0) AS cdRomNum," +
            "isnull(sum(SERIALPORTUSAGE),0) AS serialNum," +
            "isnull(sum(PARALLELPORTUSAGE),0) AS parallelNum " +
            "from LSV_PERIPHERALACCESSINFO_VIEW ")
    public AccessListVO getAccesNum();
    @Select("select   devname AS deviceName ,\n" +
            "            servname AS serciceName,\n" +
            "           port AS port  \n" +
            "            from LVS_DANGER_SERVICE_MONITOR ")
    public List<HighRiskVO> getHighRiskList();
    @Select(" select\n" +
            "   Systype As sysType ," +
            "Sysversion as sysVersion," +
            " agentversion as agentVersion," +
            "agentnum  AS agentNum " +
            "from LSV_SYSAGENT_VERSIONNUM order by systype desc, sysversion asc, agentversion; ")
    public List<SystemVO> getSystemAndAgentList();



}

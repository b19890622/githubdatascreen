package com.CrossCountry.Survey.mapper.wangkun;

import com.CrossCountry.Survey.modelvo.wangkun.BugStatictisVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BugStatictisDao {
    @Select("select NAME AS bugType,COUNT AS num from LSV_HOLESCANTOTALCOUNT_VIEW")
    public List<BugStatictisVO> getBugNums();
    @Select("select riskLevel AS indexOne,NODENAME AS bugType,HOSTIPSTR AS ip,DEVICETYPE AS deviceType,NAME AS deviceName  from LSV_HOLESCANTOTALNAME_VIEW")
    public List<BugStatictisVO> getBugList();

}

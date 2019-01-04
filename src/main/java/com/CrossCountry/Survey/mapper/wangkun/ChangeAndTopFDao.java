package com.CrossCountry.Survey.mapper.wangkun;

import com.CrossCountry.Survey.modelvo.wangkun.ChangeNumVO;
import com.CrossCountry.Survey.modelvo.wangkun.TopFiveWarnVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangeAndTopFDao {
    public List<ChangeNumVO> getChangeNums();
    public List<TopFiveWarnVO> getTopFiveList();
}

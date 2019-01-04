package com.CrossCountry.Survey.mapper.wangkun;

import com.CrossCountry.Survey.modelvo.wangkun.AllDeviceWarnVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DeviceWarnNumDao {
    public List<AllDeviceWarnVO> getAllDeviceWarnNums();
}

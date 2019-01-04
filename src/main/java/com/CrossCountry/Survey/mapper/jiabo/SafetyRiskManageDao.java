package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.SafetyRiskManageAndTestPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SafetyRiskManageDao {

    public List<SafetyRiskManageAndTestPo> getSafetyRiskManage();
    public List<SafetyRiskManageAndTestPo> getSafetyRiskManageAll();

}

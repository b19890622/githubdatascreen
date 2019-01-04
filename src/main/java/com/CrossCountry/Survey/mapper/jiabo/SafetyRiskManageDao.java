package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.SafetyRiskManageAndTestPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SafetyRiskManageDao {

    public List<SafetyRiskManageAndTestPo> getSafetyRiskManage();
    public List<SafetyRiskManageAndTestPo> getSafetyRiskManageAll();
    public List<SafetyRiskManageAndTestPo> getSafetyRiskManageSupp(@Param("listName")List<String> listName, @Param("suppNum") int suppNum);
}

package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.SafetyRiskTestDao;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class SafetyRiskTestCommonService {

    @Autowired
    private SafetyRiskTestDao safetyRiskTestDao;

    public SafetyRiskManageAndTestVO getSafetyRiskTest(Object object) {
        SafetyRiskManageAndTestVO safetyRiskManageAndTestVO = new SafetyRiskManageAndTestVO();
        try {
            List<SafetyRiskManageAndTestPo> safetyRiskManageAndTestPo = safetyRiskTestDao.getSafetyRiskTest();
            List<SafetyRiskManageAndTestPo> safetyRiskManageAndTestList = new ArrayList<SafetyRiskManageAndTestPo>();
            for(SafetyRiskManageAndTestPo po : safetyRiskManageAndTestPo){
                SafetyRiskManageAndTestPo safetyRiskManageAndTest = new SafetyRiskManageAndTestPo();
                String name = po.getName();//名称
                int number = po.getNumber();//次数
                String nameReplace = name.replace("分中心","").replace("省调","").replace("市调","")
                        .replace("地调","").replace("中调","").replace("区调","").replace("网调","");
                safetyRiskManageAndTest.setName(nameReplace);
                safetyRiskManageAndTest.setNumber(number);
                safetyRiskManageAndTestList.add(safetyRiskManageAndTest);
            }
            safetyRiskManageAndTestVO.setSafetyRiskManageAndTestPoList(safetyRiskManageAndTestList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(SafetyRiskTestCommonService.class, e.getMessage(), e);
        }
        return safetyRiskManageAndTestVO;
    }
}

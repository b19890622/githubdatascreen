package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class SafetyRiskTestCommonServiceToGrpc {
    public SafetyRiskManageAndTestReply getSafetyRiskTest(Object object) {
        SafetyRiskManageAndTestReply.Builder response = SafetyRiskManageAndTestReply.newBuilder();
        SafetyRiskManageAndTestVO safetyRiskManageAndTestVO = (SafetyRiskManageAndTestVO) object;
        try {
            List<SafetyRiskManageAndTest> safetyRiskManageAndTestList = new ArrayList<SafetyRiskManageAndTest>();
            for(SafetyRiskManageAndTestPo s : safetyRiskManageAndTestVO.getSafetyRiskManageAndTestPoList()){
                SafetyRiskManageAndTest safetyRiskManageAndTest = (SafetyRiskManageAndTest) TransformToGrpcPo.convertToPojo(SafetyRiskManageAndTest.class,s);
                safetyRiskManageAndTestList.add(safetyRiskManageAndTest);
            }
            response.addAllSafetyRiskManageAndTest(safetyRiskManageAndTestList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(SafetyRiskTestCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

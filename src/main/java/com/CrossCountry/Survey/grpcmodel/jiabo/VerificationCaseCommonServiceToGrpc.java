package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class VerificationCaseCommonServiceToGrpc {
    public VerificationCaseReply getVerificationCase(Object object) {
        VerificationCaseReply.Builder response = VerificationCaseReply.newBuilder();
        VerificationCaseVO verificationCaseVO = (VerificationCaseVO) object;
        try {
            List<VerificationCase> verificationCaseList = new ArrayList<VerificationCase>();
            for(VerificationCasePo vp : verificationCaseVO.getVerificationCasePoList()){
                VerificationCase verificationCase = (VerificationCase) TransformToGrpcPo.convertToPojo(VerificationCase.class,vp);
                verificationCaseList.add(verificationCase);
            }
            response.addAllVerificationCase(verificationCaseList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(VerificationCaseCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

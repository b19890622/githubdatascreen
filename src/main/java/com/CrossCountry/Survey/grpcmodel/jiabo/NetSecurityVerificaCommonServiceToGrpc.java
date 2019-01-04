package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

public class NetSecurityVerificaCommonServiceToGrpc {
    public NetSecurityVerificaReply getNetSecurityVerifica(Object object) {
        NetSecurityVerificaReply.Builder response = NetSecurityVerificaReply.newBuilder();
        NetSecurityVerificaPo netSecurityVerificaPo = (NetSecurityVerificaPo) object;
        try {
            ComplianceAndLeakage complianceAndLeakage = (ComplianceAndLeakage) TransformToGrpcPo.convertToPojo(ComplianceAndLeakage.class,netSecurityVerificaPo);
            //response.addComplianceAndLeakage(complianceAndLeakage);
            response.setComplianceAndLeakage(complianceAndLeakage);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(NetSecurityVerificaCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class UnitsVerificationCommonServiceToGrpc {
    public UnitsVerificationReply getUnitsVerification(Object object) {
        UnitsVerificationReply.Builder response = UnitsVerificationReply.newBuilder();
        UnitsVerificationVO unitsVerificationVO = (UnitsVerificationVO) object;
        try {
            List<UnitsVerification> unitsVerificationList = new ArrayList<UnitsVerification>();
            for(UnitsVerificationPo u : unitsVerificationVO.getUnitsVerificationPoList()){
                UnitsVerification unitsVerification = (UnitsVerification) TransformToGrpcPo.convertToPojo(UnitsVerification.class,u);
                unitsVerificationList.add(unitsVerification);
            }
            response.addAllUnitsVerification(unitsVerificationList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(UnitsVerificationCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

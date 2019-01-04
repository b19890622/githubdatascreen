package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.UnitsVerificationDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class UnitsVerificationCommonService {

    @Autowired
    private UnitsVerificationDao unitsVerificationDao;

    public UnitsVerificationVO getUnitsVerification(Object object) {
        CommonArgs args = (CommonArgs) object;
        String nameParam = args.getName();
        UnitsVerificationVO unitsVerificationVO = new UnitsVerificationVO();
        try {
            List<UnitsVerificationPo> unitsVerificationPo = unitsVerificationDao.getUnitsVerification(nameParam);
            List<UnitsVerificationPo> unitsVerificationList = new ArrayList<UnitsVerificationPo>();
            for(UnitsVerificationPo po : unitsVerificationPo){
                UnitsVerificationPo unitsVerification = new UnitsVerificationPo();
                String name = po.getName();//名称
                String noComplianceRate = po.getComplianceRate();//名称
                String leakageRate = po.getLeakageRate();//名称
                String nameReplace = name.replace("分中心","").replace("省调","").replace("市调","")
                        .replace("地调","").replace("中调","").replace("区调","").replace("网调","");
                unitsVerification.setName(nameReplace);
                unitsVerification.setComplianceRate(noComplianceRate);
                unitsVerification.setLeakageRate(leakageRate);
                unitsVerificationList.add(unitsVerification);
            }
            unitsVerificationVO.setUnitsVerificationPoList(unitsVerificationList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(UnitsVerificationCommonService.class, e.getMessage(), e);
        }
        return unitsVerificationVO;
    }
}

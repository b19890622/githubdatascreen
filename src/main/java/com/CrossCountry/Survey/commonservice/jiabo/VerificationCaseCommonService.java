package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.VerificationCaseDao;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class VerificationCaseCommonService {

    @Autowired
    private VerificationCaseDao verificationCaseDao;

    public VerificationCaseVO getVerificationCase(Object object) {
        VerificationCaseVO verificationCaseVO = new VerificationCaseVO();
        try {
            List<VerificationCasePo> verificationCasePo = verificationCaseDao.getVerificationCase();
            verificationCaseVO.setVerificationCasePoList(verificationCasePo);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(VerificationCaseCommonService.class, e.getMessage(), e);
        }
        return verificationCaseVO;
    }
}

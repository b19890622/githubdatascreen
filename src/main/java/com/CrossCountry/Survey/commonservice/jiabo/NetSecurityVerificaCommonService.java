package com.CrossCountry.Survey.commonservice.jiabo;

import com.CrossCountry.Survey.mapper.jiabo.NetSecurityVerificaDao;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class NetSecurityVerificaCommonService {

    @Autowired
    private NetSecurityVerificaDao netSecurityVerificaDao;

    public NetSecurityVerificaPo getNetSecurityVerifica(Object object) {
        NetSecurityVerificaPo netSecurityVerificaPo = new NetSecurityVerificaPo();
        try {
            netSecurityVerificaPo = netSecurityVerificaDao.getNetSecurityVerifica();
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(NetSecurityVerificaCommonService.class, e.getMessage(), e);
        }
        return netSecurityVerificaPo;
    }
}

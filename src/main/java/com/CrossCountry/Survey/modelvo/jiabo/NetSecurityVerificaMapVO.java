package com.CrossCountry.Survey.modelvo.jiabo;

import java.util.List;

public class NetSecurityVerificaMapVO {
    private List<NetSecurityVerificaMapPo> VerificaMapCenterList;
    private List<NetSecurityVerificaMapPo> VerificaMapProvinceList;

    public List<NetSecurityVerificaMapPo> getVerificaMapCenterList() {
        return VerificaMapCenterList;
    }

    public void setVerificaMapCenterList(List<NetSecurityVerificaMapPo> verificaMapCenterList) {
        VerificaMapCenterList = verificaMapCenterList;
    }

    public List<NetSecurityVerificaMapPo> getVerificaMapProvinceList() {
        return VerificaMapProvinceList;
    }

    public void setVerificaMapProvinceList(List<NetSecurityVerificaMapPo> verificaMapProvinceList) {
        VerificaMapProvinceList = verificaMapProvinceList;
    }
}

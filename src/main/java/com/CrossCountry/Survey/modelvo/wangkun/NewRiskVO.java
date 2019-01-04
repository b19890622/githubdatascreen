package com.CrossCountry.Survey.modelvo.wangkun;

import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/31 15:25
 */
public class NewRiskVO {
    private List<WarnParamVO> warnRiskList;
    private List<WarnParamVO> warnRiskDealList;

    public List<WarnParamVO> getWarnRiskList() {
        return warnRiskList;
    }

    public void setWarnRiskList(List<WarnParamVO> warnRiskList) {
        this.warnRiskList = warnRiskList;
    }

    public List<WarnParamVO> getWarnRiskDealList() {
        return warnRiskDealList;
    }

    public void setWarnRiskDealList(List<WarnParamVO> warnRiskDealList) {
        this.warnRiskDealList = warnRiskDealList;
    }
}

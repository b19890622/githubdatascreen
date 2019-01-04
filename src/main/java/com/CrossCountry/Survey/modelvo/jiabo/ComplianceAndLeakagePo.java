package com.CrossCountry.Survey.modelvo.jiabo;

public class ComplianceAndLeakagePo {
    private String leakageRate;//漏扫率
    private String complianceRate;//漏扫率
    private int ncomplianceTotal;//次数
    private int holesTotal = 4;//漏洞总数

    public String getLeakageRate() {
        return leakageRate;
    }

    public void setLeakageRate(String leakageRate) {
        this.leakageRate = leakageRate;
    }

    public String getComplianceRate() {
        return complianceRate;
    }

    public void setComplianceRate(String complianceRate) {
        this.complianceRate = complianceRate;
    }

    public int getNcomplianceTotal() {
        return ncomplianceTotal;
    }

    public void setNcomplianceTotal(int ncomplianceTotal) {
        this.ncomplianceTotal = ncomplianceTotal;
    }

    public int getHolesTotal() {
        return holesTotal;
    }

    public void setHolesTotal(int holesTotal) {
        this.holesTotal = holesTotal;
    }
}

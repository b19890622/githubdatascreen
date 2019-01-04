package com.CrossCountry.Survey.modelvo.jiabo;

public class NetSecurityVerificaPo {
    private String leakageRate;//漏扫率
    private String complianceRate;//合规率
    private int ncomplianceTotal;//不合规总数
    private int holesTotal;//漏洞总数
    private int total;//配置总数
    private int compliance;//合规数
    private int vulnerabiAssets;//漏洞资产数

    public int getVulnerabiAssets() {
        return vulnerabiAssets;
    }

    public void setVulnerabiAssets(int vulnerabiAssets) {
        this.vulnerabiAssets = vulnerabiAssets;
    }

    public int getCompliance() {
        return compliance;
    }

    public void setCompliance(int compliance) {
        this.compliance = compliance;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFailureCompliance() {
        return failureCompliance;
    }

    public void setFailureCompliance(int failureCompliance) {
        this.failureCompliance = failureCompliance;
    }

    private int failureCompliance;//漏洞扫描不合规主机设备数

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

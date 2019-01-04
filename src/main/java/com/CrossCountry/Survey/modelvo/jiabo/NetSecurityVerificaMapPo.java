package com.CrossCountry.Survey.modelvo.jiabo;

public class NetSecurityVerificaMapPo {
    private String name;//省、地调名称
    private int noCompliance;//不合规数
    private int leakage;//漏洞数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoCompliance() {
        return noCompliance;
    }

    public void setNoCompliance(int noCompliance) {
        this.noCompliance = noCompliance;
    }

    public int getLeakage() {
        return leakage;
    }

    public void setLeakage(int leakage) {
        this.leakage = leakage;
    }
}

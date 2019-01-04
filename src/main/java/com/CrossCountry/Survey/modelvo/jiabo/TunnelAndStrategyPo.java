package com.CrossCountry.Survey.modelvo.jiabo;

public class TunnelAndStrategyPo {
    private String name;//公司名称
    private int equipVerified;//已核查设备数
    private int problemsNumber;//问题总数
    private String propStrategyProblems;//策略问题占比
    private String propTunnelProblems;//隧道问题占比

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEquipVerified() {
        return equipVerified;
    }

    public void setEquipVerified(int equipVerified) {
        this.equipVerified = equipVerified;
    }

    public int getProblemsNumber() {
        return problemsNumber;
    }

    public void setProblemsNumber(int problemsNumber) {
        this.problemsNumber = problemsNumber;
    }

    public String getPropStrategyProblems() {
        return propStrategyProblems;
    }

    public void setPropStrategyProblems(String propStrategyProblems) {
        this.propStrategyProblems = propStrategyProblems;
    }

    public String getPropTunnelProblems() {
        return propTunnelProblems;
    }

    public void setPropTunnelProblems(String propTunnelProblems) {
        this.propTunnelProblems = propTunnelProblems;
    }
}

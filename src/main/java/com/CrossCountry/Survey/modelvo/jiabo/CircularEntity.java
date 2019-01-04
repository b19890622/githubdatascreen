package com.CrossCountry.Survey.modelvo.jiabo;

public class CircularEntity {
    private String name;//分中心名称
    private int warningNumber;//告警数
    private String secretPass;//密通率
    private String online;//在线率

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    private int resolved;//已解决

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarningNumber() {
        return warningNumber;
    }

    public void setWarningNumber(int warningNumber) {
        this.warningNumber = warningNumber;
    }

    public String getSecretPass() {
        return secretPass;
    }

    public void setSecretPass(String secretPass) {
        this.secretPass = secretPass;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}

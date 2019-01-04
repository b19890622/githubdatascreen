package com.CrossCountry.Survey.modelvo.jiabo;

public class HistogramEntity {
    private String name;//省、地调名称
    private int unsolved;//待解决
    private int resolved;//已解决
    private String secretPass;//密通率
    private String online;//在线率

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUnsolved() {
        return unsolved;
    }

    public void setUnsolved(int unsolved) {
        this.unsolved = unsolved;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }
}

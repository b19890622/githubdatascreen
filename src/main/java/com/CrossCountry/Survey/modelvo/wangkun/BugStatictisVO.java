package com.CrossCountry.Survey.modelvo.wangkun;

/**
 * @auther wangkun
 * @date 2018/12/29 11:44
 */
public class BugStatictisVO {
    private String bugType;//漏洞类型
    private int num;//漏洞数量
    private int indexOne ;//表格中序号
    private String ip ;//ip
    private String deviceType ;//设备类型
    private String deviceName ;//设备名称
    private int index;

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIndexOne() {
        return indexOne;
    }

    public void setIndexOne(int indexOne) {
        this.indexOne = indexOne;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

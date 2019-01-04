package com.CrossCountry.Survey.modelvo.wangkun;

/**
 * @auther wangkun
 * @date 2018/12/30 12:21
 */
public class AccessVO {
    private int index;
    private String devicename;
    private String usb;
    private String cdRom;
    private String serialPort;
    private String parallelPort;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }

    public String getCdRom() {
        return cdRom;
    }

    public void setCdRom(String cdRom) {
        this.cdRom = cdRom;
    }

    public String getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(String serialPort) {
        this.serialPort = serialPort;
    }

    public String getParallelPort() {
        return parallelPort;
    }

    public void setParallelPort(String parallelPort) {
        this.parallelPort = parallelPort;
    }
}

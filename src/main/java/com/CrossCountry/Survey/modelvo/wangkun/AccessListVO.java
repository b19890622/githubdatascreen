package com.CrossCountry.Survey.modelvo.wangkun;

import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/30 12:29
 */
public class AccessListVO {
    private List<AccessVO> accessVOS;
    private int usbNum;//usb数
    private int cdRomNum;//光驱数
    private int serialNum;//串口数
    private int parallelNum;//并口数

    public List<AccessVO> getAccessVOS() {
        return accessVOS;
    }

    public void setAccessVOS(List<AccessVO> accessVOS) {
        this.accessVOS = accessVOS;
    }

    public int getUsbNum() {
        return usbNum;
    }

    public void setUsbNum(int usbNum) {
        this.usbNum = usbNum;
    }

    public int getCdRomNum() {
        return cdRomNum;
    }

    public void setCdRomNum(int cdRomNum) {
        this.cdRomNum = cdRomNum;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getParallelNum() {
        return parallelNum;
    }

    public void setParallelNum(int parallelNum) {
        this.parallelNum = parallelNum;
    }
}

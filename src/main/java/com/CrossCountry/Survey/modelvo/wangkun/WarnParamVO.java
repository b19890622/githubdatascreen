package com.CrossCountry.Survey.modelvo.wangkun;

/**
 * @auther wangkun
 * @date 2018/12/29 13:58
 */
public class WarnParamVO {
    private int period;//时间点
    private int nums;//这个时间段的数量
    private String date;//当天日期
    private String name;//调度机构
    private String content;//告警描述
    private String alarmTime;//告警时间
    private String confirmstate;//告警状态
    private String alarmLevel;//告警级别
    private String flag;//flag标识 安全标识
    private String isNew;//0是非新数据，1是新数据

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getConfirmstate() {
        return confirmstate;
    }

    public void setConfirmstate(String confirmstate) {
        this.confirmstate = confirmstate;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
}

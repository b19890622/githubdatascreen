package com.CrossCountry.Survey.modelvo.wangkun;

public class SecurityEventList {
	// 序号
	public String index;
	// 涉及单位
	public String department;
	// 攻击事件描述
	public String describe;
	// 最新发生时间
	public String warningTime;
	// 告警是否解决状态（0：未解决，1：已解决）
	public String warningSloveState;

	public String getWarningSloveState() {
		return warningSloveState;
	}

	public void setWarningSloveState(String warningSloveState) {
		this.warningSloveState = warningSloveState;
	}

	// 是否需要变色
	public boolean eventFlag;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	public boolean getEventFlag() {
		return eventFlag;
	}

	public void setEventFlag(boolean eventFlag) {
		this.eventFlag = eventFlag;
	}
}

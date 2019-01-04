package com.CrossCountry.Survey.modelvo.wangkun;

public class SecurityEventVo {
	// 序号
	public int index;
	// 涉及单位
	public String department;
	// 攻击事件描述
	public String describe;
	// 最新发生时间
	public String warningTime;
	// 主站名称
	public String proviceName;
	// 远程源头名称
	public String remoteProvice;
	// 目的名称
	public String localProvice;
	// 告警是否解决状态（0：未解决，1：已解决）
	public String warningSloveState;

	public String getWarningSloveState() {
		return warningSloveState;
	}

	public void setWarningSloveState(String warningSloveState) {
		this.warningSloveState = warningSloveState;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
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

	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}

	public String getRemoteProvice() {
		return remoteProvice;
	}

	public void setRemoteProvice(String remoteProvice) {
		this.remoteProvice = remoteProvice;
	}

	public String getLocalProvice() {
		return localProvice;
	}

	public void setLocalProvice(String localProvice) {

		this.localProvice = localProvice;
	}

}

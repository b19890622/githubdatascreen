package com.CrossCountry.Survey.modelvo.donggs;

public class AlarmNameAndNumber {
	private String id;
	private String num;
	private String devicename;
	private String nodename;
	private String ip;
	private String warningState;

	public String getWarningState() {
		return warningState;
	}

	public void setWarningState(String warningState) {
		this.warningState = warningState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

}

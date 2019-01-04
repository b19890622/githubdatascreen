package com.CrossCountry.Survey.modelvo.donggs;

public class VisitTimesPo {
	private String ip ;//ip
	private int localnum ;//本机登入次数
	private int sshnum ;//ssh登入次数
	private String type ;//访问类型
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getLocalnum() {
		return localnum;
	}
	public void setLocalnum(int localnum) {
		this.localnum = localnum;
	}
	public int getSshnum() {
		return sshnum;
	}
	public void setSshnum(int sshnum) {
		this.sshnum = sshnum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}

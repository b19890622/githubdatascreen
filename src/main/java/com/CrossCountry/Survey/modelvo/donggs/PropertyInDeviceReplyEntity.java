package com.CrossCountry.Survey.modelvo.donggs;

import java.sql.Timestamp;

public class PropertyInDeviceReplyEntity {
	private String swIp;
	private String inIp;
	private Timestamp time;

	public String getSwIp() {
		return swIp;
	}

	public void setSwIp(String swIp) {
		this.swIp = swIp;
	}

	public String getInIp() {
		return inIp;
	}

	public void setInIp(String inIp) {
		this.inIp = inIp;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}

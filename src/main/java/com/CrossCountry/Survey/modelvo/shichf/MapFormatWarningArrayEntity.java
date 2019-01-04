package com.CrossCountry.Survey.modelvo.shichf;

import java.sql.Timestamp;

public class MapFormatWarningArrayEntity {
	private String remoteProvince;// 目的地
	private String localProvince;// 源地
	private String provinceName;// 截断城市判断
	private String localName;
	private Timestamp warningTime;

	public Timestamp getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(Timestamp warningTime) {
		this.warningTime = warningTime;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getRemoteProvince() {
		return remoteProvince;
	}

	public void setRemoteProvince(String remoteProvince) {
		this.remoteProvince = remoteProvince;
	}

	public String getLocalProvince() {
		return localProvince;
	}

	public void setLocalProvince(String localProvince) {
		this.localProvince = localProvince;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}

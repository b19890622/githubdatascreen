package com.CrossCountry.Survey.modelvo.shichf;

public class LowLevelMapEntity {
	private String areaname;
	private Integer unsolvedemergnum;// 未解决紧急告警数
	private Integer unsolvederrnum;// 未解决重要告警数
	private Integer unsolvedwarnningnum;// 未解决一般告警数
	private Integer devicenum;// 接入设备数
	private Integer offlinedevicenum;// 离线设备数
	private double decryptpackage;// 明文流量
	private double encryptpackage;// 秘文流量

	public Integer getUnsolvedemergnum() {
		return unsolvedemergnum;
	}

	public void setUnsolvedemergnum(Integer unsolvedemergnum) {
		this.unsolvedemergnum = unsolvedemergnum;
	}

	public Integer getUnsolvederrnum() {
		return unsolvederrnum;
	}

	public void setUnsolvederrnum(Integer unsolvederrnum) {
		this.unsolvederrnum = unsolvederrnum;
	}

	public Integer getUnsolvedwarnningnum() {
		return unsolvedwarnningnum;
	}

	public void setUnsolvedwarnningnum(Integer unsolvedwarnningnum) {
		this.unsolvedwarnningnum = unsolvedwarnningnum;
	}

	public Integer getDevicenum() {
		return devicenum;
	}

	public void setDevicenum(Integer devicenum) {
		this.devicenum = devicenum;
	}

	public Integer getOfflinedevicenum() {
		return offlinedevicenum;
	}

	public void setOfflinedevicenum(Integer offlinedevicenum) {
		this.offlinedevicenum = offlinedevicenum;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public double getDecryptpackage() {
		return decryptpackage;
	}

	public void setDecryptpackage(double decryptpackage) {
		this.decryptpackage = decryptpackage;
	}

	public double getEncryptpackage() {
		return encryptpackage;
	}

	public void setEncryptpackage(double encryptpackage) {
		this.encryptpackage = encryptpackage;
	}

}

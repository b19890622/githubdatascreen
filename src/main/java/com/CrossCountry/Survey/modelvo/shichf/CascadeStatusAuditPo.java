package com.CrossCountry.Survey.modelvo.shichf;

public class CascadeStatusAuditPo {
	private int id;// ID
	private String province;// 网省
	private String cities;// 地调
	private String status;// 状态
	private String dispatchlevel;// 调度级次
	private int fid;// FID

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDispatchlevel() {
		return dispatchlevel;
	}

	public void setDispatchlevel(String dispatchlevel) {
		this.dispatchlevel = dispatchlevel;
	}
}

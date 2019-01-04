package com.CrossCountry.Survey.modelvo.donggs;

import java.util.Date;

public class ChangeRed {

	private String remark;
	private String regional;
	private String shortname;
	private String voltageclas;
	private String addOrNo;
	private String warningtime;
	private Date warningtimes;
	private Integer warningType;

	public Integer getWarningType() {
		return warningType;
	}

	public void setWarningType(Integer warningType) {
		this.warningType = warningType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getVoltageclas() {
		return voltageclas;
	}

	public void setVoltageclas(String voltageclas) {
		this.voltageclas = voltageclas;
	}

	public String getAddOrNo() {
		return addOrNo;
	}

	public void setAddOrNo(String addOrNo) {
		this.addOrNo = addOrNo;
	}

	public String getWarningtime() {
		return warningtime;
	}

	public void setWarningtime(String warningtime) {
		this.warningtime = warningtime;
	}

	public Date getWarningtimes() {
		return warningtimes;
	}

	public void setWarningtimes(Date warningtimes) {
		this.warningtimes = warningtimes;
	}

}

package com.CrossCountry.Survey.modelvo.shichf;

public class SafetyRateSubEntity {
	private String id;
	private String provinceName;
	private Integer checkDimen;
	private Integer checkCount;
	private String localName;

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCheckDimen() {
		return checkDimen;
	}

	public void setCheckDimen(Integer checkDimen) {
		this.checkDimen = checkDimen;
	}

	public Integer getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}

}

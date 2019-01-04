package com.CrossCountry.Survey.modelvo.wangkun;

import java.util.List;

public class SecurityEventResponse {
	public List<SecurityEventList> securityEventVoList;
	public long nums;
	public long toSolvedNums;

	public long getToSolvedNums() {
		return toSolvedNums;
	}

	public void setToSolvedNums(long toSolvedNums) {
		this.toSolvedNums = toSolvedNums;
	}

	public List<SecurityEventList> getSecurityEventVoList() {
		return securityEventVoList;
	}

	public void setSecurityEventVoList(List<SecurityEventList> securityEventVoList) {
		this.securityEventVoList = securityEventVoList;
	}

	public long getNums() {
		return nums;
	}

	public void setNums(long nums) {
		this.nums = nums;
	}

}

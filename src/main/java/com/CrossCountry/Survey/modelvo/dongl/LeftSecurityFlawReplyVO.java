package com.CrossCountry.Survey.modelvo.dongl;

import java.util.List;

public class LeftSecurityFlawReplyVO {
	private List<NumOneAreaLeftEntity> numOneAreaLeftList;// 左图一区
	private List<NumTwoAreaLeftEntity> numTwoAreaLeftList;// 左图二区
	private List<NumThreeAreaLeftEntity> numThreeAreaLeftList;// 左图三区

	public List<NumOneAreaLeftEntity> getNumOneAreaLeftList() {
		return numOneAreaLeftList;
	}

	public void setNumOneAreaLeftList(List<NumOneAreaLeftEntity> numOneAreaLeftList) {
		this.numOneAreaLeftList = numOneAreaLeftList;
	}

	public List<NumTwoAreaLeftEntity> getNumTwoAreaLeftList() {
		return numTwoAreaLeftList;
	}

	public void setNumTwoAreaLeftList(List<NumTwoAreaLeftEntity> numTwoAreaLeftList) {
		this.numTwoAreaLeftList = numTwoAreaLeftList;
	}

	public List<NumThreeAreaLeftEntity> getNumThreeAreaLeftList() {
		return numThreeAreaLeftList;
	}

	public void setNumThreeAreaLeftList(List<NumThreeAreaLeftEntity> numThreeAreaLeftList) {
		this.numThreeAreaLeftList = numThreeAreaLeftList;
	}

}

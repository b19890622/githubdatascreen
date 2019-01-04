package com.CrossCountry.Survey.modelvo.dongl;

import java.util.List;

public class RightSecurityFlawReplyVO {
	private List<NumOneAreaRightEntity> numOneAreaRightList;// 右图一区
	private List<NumTwoAreaRightEntity> numTwoAreaRightList;// 右图二区
	private List<NumThreeAreaRightEntity> numThreeAreaRightList;// 右图三区

	public List<NumOneAreaRightEntity> getNumOneAreaRightList() {
		return numOneAreaRightList;
	}

	public void setNumOneAreaRightList(List<NumOneAreaRightEntity> numOneAreaRightList) {
		this.numOneAreaRightList = numOneAreaRightList;
	}

	public List<NumTwoAreaRightEntity> getNumTwoAreaRightList() {
		return numTwoAreaRightList;
	}

	public void setNumTwoAreaRightList(List<NumTwoAreaRightEntity> numTwoAreaRightList) {
		this.numTwoAreaRightList = numTwoAreaRightList;
	}

	public List<NumThreeAreaRightEntity> getNumThreeAreaRightList() {
		return numThreeAreaRightList;
	}

	public void setNumThreeAreaRightList(List<NumThreeAreaRightEntity> numThreeAreaRightList) {
		this.numThreeAreaRightList = numThreeAreaRightList;
	}

}

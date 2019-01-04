package com.CrossCountry.Survey.modelvo.wangkun;

import java.util.List;

public class ResultMsgVO {

	private List<ResultVO> resultList;
	
	private int countSum;

	public List<ResultVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResultVO> resultList) {
		this.resultList = resultList;
	}

	public int getCountSum() {
		return countSum;
	}

	public void setCountSum(int countSum) {
		this.countSum = countSum;
	}
	
}

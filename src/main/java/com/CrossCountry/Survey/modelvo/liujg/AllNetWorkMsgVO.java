package com.CrossCountry.Survey.modelvo.liujg;

public class AllNetWorkMsgVO {

	private String regionName; //地域名称
	
	private int solvedNum; //解决数量
	
	private int confirmNum  = 3 ; //确认数量 
	
	private int  solveStatSum = 4 ; //合计

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getSolvedNum() {
		return solvedNum;
	}

	public void setSolvedNum(int solvedNum) {
		this.solvedNum = solvedNum;
	}

	public int getConfirmNum() {
		return confirmNum;
	}

	public void setConfirmNum(int confirmNum) {
		this.confirmNum = confirmNum;
	}

	public int getSolveStatSum() {
		return solveStatSum;
	}

	public void setSolveStatSum(int solveStatSum) {
		this.solveStatSum = solveStatSum;
	}
	
}

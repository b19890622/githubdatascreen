package com.CrossCountry.Survey.modelvo.donggs;

import java.util.List;

public class TerraceDeviceNumber {
	private List<JustableNumber> Justable; // 省调
	private int terraceDeviceNumber; // 平台部署总数
	public List<JustableNumber> getJustable() {
		return Justable;
	}
	public void setJustable(List<JustableNumber> justable) {
		Justable = justable;
	}
	public int getTerraceDeviceNumber() {
		return terraceDeviceNumber;
	}
	public void setTerraceDeviceNumber(int terraceDeviceNumber) {
		this.terraceDeviceNumber = terraceDeviceNumber;
	} 
	
}

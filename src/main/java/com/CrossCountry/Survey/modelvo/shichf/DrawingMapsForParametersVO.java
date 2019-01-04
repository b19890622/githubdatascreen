package com.CrossCountry.Survey.modelvo.shichf;

import java.util.List;

public class DrawingMapsForParametersVO {
	private List<MapFormatSecurityArrayVO> securityArray;
	private List<MapFormatWarningArrayVO> warningArray;
	private List<MapFormatCenterIndexVO> centerIndexArray;

	public List<MapFormatCenterIndexVO> getCenterIndexArray() {
		return centerIndexArray;
	}

	public void setCenterIndexArray(List<MapFormatCenterIndexVO> centerIndexArray) {
		this.centerIndexArray = centerIndexArray;
	}

	public List<MapFormatSecurityArrayVO> getSecurityArray() {
		return securityArray;
	}

	public void setSecurityArray(List<MapFormatSecurityArrayVO> securityArray) {
		this.securityArray = securityArray;
	}

	public List<MapFormatWarningArrayVO> getWarningArray() {
		return warningArray;
	}

	public void setWarningArray(List<MapFormatWarningArrayVO> warningArray) {
		this.warningArray = warningArray;
	}

}

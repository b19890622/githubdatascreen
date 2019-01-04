package com.CrossCountry.Survey.modelvo.donggs;

import java.util.List;

public class SecureTopology {
	private List<AlarmNameAndNumber> alarmNameAndNumber;
	private List<PropertyDataAndOftenData> propertyDataAndOftenData;
	private List<PropertyInDeviceReplyData> propertyInDeviceReply;

	public List<AlarmNameAndNumber> getAlarmNameAndNumber() {
		return alarmNameAndNumber;
	}

	public void setAlarmNameAndNumber(List<AlarmNameAndNumber> alarmNameAndNumber) {
		this.alarmNameAndNumber = alarmNameAndNumber;
	}

	public List<PropertyDataAndOftenData> getPropertyDataAndOftenData() {
		return propertyDataAndOftenData;
	}

	public void setPropertyDataAndOftenData(List<PropertyDataAndOftenData> propertyDataAndOftenData) {
		this.propertyDataAndOftenData = propertyDataAndOftenData;
	}

	public List<PropertyInDeviceReplyData> getPropertyInDeviceReply() {
		return propertyInDeviceReply;
	}

	public void setPropertyInDeviceReply(List<PropertyInDeviceReplyData> propertyInDeviceReply) {
		this.propertyInDeviceReply = propertyInDeviceReply;
	}

}

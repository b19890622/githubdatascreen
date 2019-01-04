package com.CrossCountry.Survey.modelvo.shichf;

public class ScreenDayRiskTOPTenEntity {
	private String alarmType;
	private int count;

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScreenDayRiskTOPTenEntity screenDayRiskTOPTenEntity = (ScreenDayRiskTOPTenEntity) o;
		if (count != screenDayRiskTOPTenEntity.count)
			return false;
		return alarmType.equals(screenDayRiskTOPTenEntity.alarmType);
	}

	@Override
	public int hashCode() {
		int result = Long.valueOf(count).hashCode();
		result = 31 * result + alarmType.hashCode();
		return result;
	}
}

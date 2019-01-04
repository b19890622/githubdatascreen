package com.CrossCountry.Survey.modelvo.shichf;

public class MapFormatSecurityArrayVO {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MapFormatSecurityArrayVO mapFormatSecurityArrayVO = (MapFormatSecurityArrayVO) o;
		if (!name.equals(mapFormatSecurityArrayVO.name) || !value.equals(mapFormatSecurityArrayVO.value))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		String str = name + value;
		return str.hashCode();
	}
}

package com.CrossCountry.Survey.modelvo.shichf;

public class MapFormatWarningArrayVO {
	private String localprovince;
	private String remoteprovince;
	private String cutprovince;
	private String warningstate;
	private String warningTime;

	public String getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	public String getLocalprovince() {
		return localprovince;
	}

	public void setLocalprovince(String localprovince) {
		this.localprovince = localprovince;
	}

	public String getRemoteprovince() {
		return remoteprovince;
	}

	public void setRemoteprovince(String remoteprovince) {
		this.remoteprovince = remoteprovince;
	}

	public String getCutprovince() {
		return cutprovince;
	}

	public void setCutprovince(String cutprovince) {
		this.cutprovince = cutprovince;
	}

	public String getWarningstate() {
		return warningstate;
	}

	public void setWarningstate(String warningstate) {
		this.warningstate = warningstate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MapFormatWarningArrayVO mapFormatWarningArrayVO = (MapFormatWarningArrayVO) o;
		if (!localprovince.equals(mapFormatWarningArrayVO.localprovince)
				|| !remoteprovince.equals(mapFormatWarningArrayVO.remoteprovince)
				|| !cutprovince.equals(mapFormatWarningArrayVO.cutprovince)
				|| !warningstate.equals(mapFormatWarningArrayVO.warningstate)
				|| !warningTime.equals(mapFormatWarningArrayVO.warningTime))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		String str = localprovince + remoteprovince + cutprovince + warningstate + warningTime;
		return str.hashCode();
	}
}

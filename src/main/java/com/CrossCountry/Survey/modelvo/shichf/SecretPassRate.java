package com.CrossCountry.Survey.modelvo.shichf;

public class SecretPassRate {
	private String stime;// 时间点

	private String secretpassflow;// 密通流量

	private String cleartraffic;// 明文流量
	private String traffic;// 总流量

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getSecretpassflow() {
		return secretpassflow;
	}

	public void setSecretpassflow(String secretpassflow) {
		this.secretpassflow = secretpassflow;
	}

	public String getCleartraffic() {
		return cleartraffic;
	}

	public void setCleartraffic(String cleartraffic) {
		this.cleartraffic = cleartraffic;
	}

}

package com.CrossCountry.Survey.modelvo.shichf;

public class SafetyRateLocalEntity {
	//基线核查合规项个数
	private Integer cvsLegalNum;
	//基线核查不合规项个数
	private Integer cvsIlegalNum;
	//漏洞扫描主机设备个数
	private Integer vbsNum;
	//漏洞扫描不合规主机设备个数
	private Integer vbsIlegalNum;
	//弱口令扫描主机数
	private Integer userCheckNum;
	//弱口令扫描不合规主机数
	private Integer userCheckIlegalNum;
	//核查纵向隧道数
	private Integer veadTunnelNum;
	//核查纵向策略数
	private Integer veadPolicyNum;
	//核查纵向问题隧道数
	private Integer veadIlegalTunnelNum;
	//核查纵向问题策略数
	private Integer veadIlegalPolicyNum;

	public Integer getCvsLegalNum() {
		return cvsLegalNum;
	}

	public void setCvsLegalNum(Integer cvsLegalNum) {
		this.cvsLegalNum = cvsLegalNum;
	}

	public Integer getCvsIlegalNum() {
		return cvsIlegalNum;
	}

	public void setCvsIlegalNum(Integer cvsIlegalNum) {
		this.cvsIlegalNum = cvsIlegalNum;
	}

	public Integer getVbsNum() {
		return vbsNum;
	}

	public void setVbsNum(Integer vbsNum) {
		this.vbsNum = vbsNum;
	}

	public Integer getVbsIlegalNum() {
		return vbsIlegalNum;
	}

	public void setVbsIlegalNum(Integer vbsIlegalNum) {
		this.vbsIlegalNum = vbsIlegalNum;
	}

	public Integer getUserCheckNum() {
		return userCheckNum;
	}

	public void setUserCheckNum(Integer userCheckNum) {
		this.userCheckNum = userCheckNum;
	}

	public Integer getUserCheckIlegalNum() {
		return userCheckIlegalNum;
	}

	public void setUserCheckIlegalNum(Integer userCheckIlegalNum) {
		this.userCheckIlegalNum = userCheckIlegalNum;
	}

	public Integer getVeadTunnelNum() {
		return veadTunnelNum;
	}

	public void setVeadTunnelNum(Integer veadTunnelNum) {
		this.veadTunnelNum = veadTunnelNum;
	}

	public Integer getVeadPolicyNum() {
		return veadPolicyNum;
	}

	public void setVeadPolicyNum(Integer veadPolicyNum) {
		this.veadPolicyNum = veadPolicyNum;
	}

	public Integer getVeadIlegalTunnelNum() {
		return veadIlegalTunnelNum;
	}

	public void setVeadIlegalTunnelNum(Integer veadIlegalTunnelNum) {
		this.veadIlegalTunnelNum = veadIlegalTunnelNum;
	}

	public Integer getVeadIlegalPolicyNum() {
		return veadIlegalPolicyNum;
	}

	public void setVeadIlegalPolicyNum(Integer veadIlegalPolicyNum) {
		this.veadIlegalPolicyNum = veadIlegalPolicyNum;
	}

}

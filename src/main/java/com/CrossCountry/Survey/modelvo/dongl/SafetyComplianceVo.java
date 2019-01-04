package com.CrossCountry.Survey.modelvo.dongl;

public class SafetyComplianceVo {
	private int CVSLegalNum;
	private int CVSIlegalNum;
	private int VBSNum;
	private int VBSIlegalNum;
	private int UserCheckNum;
	private int UserCheckIlegalNum;
	private int VEADTunnelNum;
	private int VEADPolicyNum;
	private int VEADIlegalTunnelNum;
	private int VEADIlegalPolicyNum;

	public int getCVSLegalNum() {
		return CVSLegalNum;
	}

	public void setCVSLegalNum(int cVSLegalNum) {
		CVSLegalNum = cVSLegalNum;
	}

	public int getCVSIlegalNum() {
		return CVSIlegalNum;
	}

	public void setCVSIlegalNum(int cVSIlegalNum) {
		CVSIlegalNum = cVSIlegalNum;
	}

	public int getVBSNum() {
		return VBSNum;
	}

	public void setVBSNum(int vBSNum) {
		VBSNum = vBSNum;
	}

	public int getVBSIlegalNum() {
		return VBSIlegalNum;
	}

	public void setVBSIlegalNum(int vBSIlegalNum) {
		VBSIlegalNum = vBSIlegalNum;
	}

	public int getUserCheckNum() {
		return UserCheckNum;
	}

	public void setUserCheckNum(int userCheckNum) {
		UserCheckNum = userCheckNum;
	}

	public int getUserCheckIlegalNum() {
		return UserCheckIlegalNum;
	}

	public void setUserCheckIlegalNum(int userCheckIlegalNum) {
		UserCheckIlegalNum = userCheckIlegalNum;
	}

	public int getVEADTunnelNum() {
		return VEADTunnelNum;
	}

	public void setVEADTunnelNum(int vEADTunnelNum) {
		VEADTunnelNum = vEADTunnelNum;
	}

	public int getVEADPolicyNum() {
		return VEADPolicyNum;
	}

	public void setVEADPolicyNum(int vEADPolicyNum) {
		VEADPolicyNum = vEADPolicyNum;
	}

	public int getVEADIlegalTunnelNum() {
		return VEADIlegalTunnelNum;
	}

	public void setVEADIlegalTunnelNum(int vEADIlegalTunnelNum) {
		VEADIlegalTunnelNum = vEADIlegalTunnelNum;
	}

	public int getVEADIlegalPolicyNum() {
		return VEADIlegalPolicyNum;
	}

	public void setVEADIlegalPolicyNum(int vEADIlegalPolicyNum) {
		VEADIlegalPolicyNum = vEADIlegalPolicyNum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SafetyComplianceVo safetyComplianceVo = (SafetyComplianceVo) o;
		if (CVSLegalNum != safetyComplianceVo.CVSLegalNum || CVSIlegalNum != safetyComplianceVo.CVSIlegalNum
				|| VBSNum != safetyComplianceVo.VBSNum || VBSIlegalNum != safetyComplianceVo.VBSIlegalNum
				|| UserCheckNum != safetyComplianceVo.UserCheckNum
				|| UserCheckIlegalNum != safetyComplianceVo.UserCheckIlegalNum
				|| VEADTunnelNum != safetyComplianceVo.VEADTunnelNum
				|| VEADPolicyNum != safetyComplianceVo.VEADPolicyNum
				|| VEADIlegalTunnelNum != safetyComplianceVo.VEADIlegalTunnelNum
				|| VEADIlegalPolicyNum != safetyComplianceVo.VEADIlegalPolicyNum)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = 31 + CVSLegalNum;
		result = 31 * result + CVSIlegalNum;
		result = 31 * result + VBSNum;
		result = 31 * result + VBSIlegalNum;
		result = 31 * result + UserCheckNum;
		result = 31 * result + UserCheckIlegalNum;
		result = 31 * result + VEADTunnelNum;
		result = 31 * result + VEADPolicyNum;
		result = 31 * result + VEADIlegalTunnelNum;
		result = 31 * result + VEADIlegalPolicyNum;
		return result;
	}
}

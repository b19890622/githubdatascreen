package com.CrossCountry.Survey.intervalutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SafetyVerificationIntervalUtils {

	//电力监控系统安全核查：全网安全核查情况：地图外部数据
	@Value("${screen_securityverification_dtw:5000}")
	private long SECURITYVERIFICATION_DTW;
	
	//电力监控系统安全核查：全网安全核查情况：地图内部数据
	@Value("${screen_securityverification_dtn:5000}")
	private long SECURITYVERIFICATION_DTN;
	
	//电力监控系统安全核查：各单位核查top10
	@Value("${screen_securityverification_dwhct:5000}")
	private long SECURITYVERIFICATION_DWHCT;
	
	//电力监控系统安全核查：主机合规情况统计
	@Value("${screen_securityverification_zjh:5000}")
	private long SECURITYVERIFICATION_ZJH;
	
	//电力监控系统安全核查：不合规详情及处置
	@Value("${screen_securityverification_bhgxq:5000}")
	private long SECURITYVERIFICATION_BHGXQ;
	
	//电力监控系统安全核查：安全漏洞扫描：I/II/III区漏洞：左图
	@Value("${screen_areaflawleft_sqld:5000}")
	private long AREAFLAWLEFT_SQLD;
	
	//电力监控系统安全核查：安全漏洞扫描：I/II/III区漏洞：右图
	@Value("${screen_areaflawright_sqld:5000}")
	private long AREAFLAWRIGHT_SQLD;
	
	//电力监控系统安全核查：安全漏洞扫描：漏洞统计环形
	@Value("${screen_holescantotalcount_ldtjhx:5000}")
	private long HOLESCANTOTALCOUNT_LDTJHX;
	
	//电力监控系统安全核查：安全漏洞扫描：漏洞统计列表
	@Value("${screen_holescantotalname_ldtjlb:5000}")
	private long HOLESCANTOTALNAME_LDTJLB;
	
	//电力监控系统安全核查：纵向合规情况统计
	@Value("${screen_safetyverification_zxhgqk:5000}")
	private long SAFETYVERIFICATION_ZXHGQK;
	
	//电力监控系统安全核查：合规情况统计
	@Value("${screen_safetyverification_hgqktj:5000}")
	private long SAFETYVERIFICATION_HGQKTJ;

	public long getSECURITYVERIFICATION_DTW() {
		return SECURITYVERIFICATION_DTW;
	}

	public void setSECURITYVERIFICATION_DTW(long sECURITYVERIFICATION_DTW) {
		SECURITYVERIFICATION_DTW = sECURITYVERIFICATION_DTW;
	}

	public long getSECURITYVERIFICATION_DTN() {
		return SECURITYVERIFICATION_DTN;
	}

	public void setSECURITYVERIFICATION_DTN(long sECURITYVERIFICATION_DTN) {
		SECURITYVERIFICATION_DTN = sECURITYVERIFICATION_DTN;
	}

	public long getSECURITYVERIFICATION_DWHCT() {
		return SECURITYVERIFICATION_DWHCT;
	}

	public void setSECURITYVERIFICATION_DWHCT(long sECURITYVERIFICATION_DWHCT) {
		SECURITYVERIFICATION_DWHCT = sECURITYVERIFICATION_DWHCT;
	}

	public long getSECURITYVERIFICATION_ZJH() {
		return SECURITYVERIFICATION_ZJH;
	}

	public void setSECURITYVERIFICATION_ZJH(long sECURITYVERIFICATION_ZJH) {
		SECURITYVERIFICATION_ZJH = sECURITYVERIFICATION_ZJH;
	}

	public long getSECURITYVERIFICATION_BHGXQ() {
		return SECURITYVERIFICATION_BHGXQ;
	}

	public void setSECURITYVERIFICATION_BHGXQ(long sECURITYVERIFICATION_BHGXQ) {
		SECURITYVERIFICATION_BHGXQ = sECURITYVERIFICATION_BHGXQ;
	}

	public long getAREAFLAWLEFT_SQLD() {
		return AREAFLAWLEFT_SQLD;
	}

	public void setAREAFLAWLEFT_SQLD(long aREAFLAWLEFT_SQLD) {
		AREAFLAWLEFT_SQLD = aREAFLAWLEFT_SQLD;
	}

	public long getAREAFLAWRIGHT_SQLD() {
		return AREAFLAWRIGHT_SQLD;
	}

	public void setAREAFLAWRIGHT_SQLD(long aREAFLAWRIGHT_SQLD) {
		AREAFLAWRIGHT_SQLD = aREAFLAWRIGHT_SQLD;
	}

	public long getHOLESCANTOTALCOUNT_LDTJHX() {
		return HOLESCANTOTALCOUNT_LDTJHX;
	}

	public void setHOLESCANTOTALCOUNT_LDTJHX(long hOLESCANTOTALCOUNT_LDTJHX) {
		HOLESCANTOTALCOUNT_LDTJHX = hOLESCANTOTALCOUNT_LDTJHX;
	}

	public long getHOLESCANTOTALNAME_LDTJLB() {
		return HOLESCANTOTALNAME_LDTJLB;
	}

	public void setHOLESCANTOTALNAME_LDTJLB(long hOLESCANTOTALNAME_LDTJLB) {
		HOLESCANTOTALNAME_LDTJLB = hOLESCANTOTALNAME_LDTJLB;
	}

	public long getSAFETYVERIFICATION_ZXHGQK() {
		return SAFETYVERIFICATION_ZXHGQK;
	}

	public void setSAFETYVERIFICATION_ZXHGQK(long sAFETYVERIFICATION_ZXHGQK) {
		SAFETYVERIFICATION_ZXHGQK = sAFETYVERIFICATION_ZXHGQK;
	}

	public long getSAFETYVERIFICATION_HGQKTJ() {
		return SAFETYVERIFICATION_HGQKTJ;
	}

	public void setSAFETYVERIFICATION_HGQKTJ(long sAFETYVERIFICATION_HGQKTJ) {
		SAFETYVERIFICATION_HGQKTJ = sAFETYVERIFICATION_HGQKTJ;
	}
	
	
	
}

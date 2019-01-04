package com.CrossCountry.Survey.intervalutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HostProtectIntervalUtils {

	//主机防护：重要业务主机状态
	@Value("${screen_hostprotect_zyywzjzt:5000}")
	private long HOSTPROTECT_ZYYWZJZT;
	
	//主机防护: 服务器访问次数
	@Value("${screen_hostprotect_fwqfw:5000}")
	private long HOSTPROTECT_FWQFW;
	
	//主机防护: 访问管控
	@Value("${screen_hostprotect_fwgk:5000}")
	private long HOSTPROTECT_FWGK;
	
	//主机防护：安全防护：接入概况
	@Value("${screen_inputsurvey_jrgk:5000}")
	private long INPUTSURVEY_JRGK;
	
	//主机防护：安全防护：安全核查统计
	@Value("${screen_checkcount_aqhctj:5000}")
	private long CHECKCOUNT_AQHCTJ;
	
	//主机防护：安全防护：主机系统agent统计
	@Value("${screen_sysagent_versionnum_zjxtdltj:5000}")
	private long SYSAGENT_VERSIONNUM_ZJXTDLTJ;
	
	//主机防护：安全防护：外设接入监视
	@Value("${screen_peripheralaccessinfo_wsjrjs:5000}")
	private long PERIPHERALACCESSINFO_WSJRJS;
	
	//主机防护：安全防护：高危服务监视
	@Value("${screen_danger_service_monitor_gwfwjs:5000}")
	private long DANGER_SERVICE_MONITOR_GWFWJS;

	public long getHOSTPROTECT_ZYYWZJZT() {
		return HOSTPROTECT_ZYYWZJZT;
	}

	public void setHOSTPROTECT_ZYYWZJZT(long hOSTPROTECT_ZYYWZJZT) {
		HOSTPROTECT_ZYYWZJZT = hOSTPROTECT_ZYYWZJZT;
	}

	public long getHOSTPROTECT_FWQFW() {
		return HOSTPROTECT_FWQFW;
	}

	public void setHOSTPROTECT_FWQFW(long hOSTPROTECT_FWQFW) {
		HOSTPROTECT_FWQFW = hOSTPROTECT_FWQFW;
	}

	public long getHOSTPROTECT_FWGK() {
		return HOSTPROTECT_FWGK;
	}

	public void setHOSTPROTECT_FWGK(long hOSTPROTECT_FWGK) {
		HOSTPROTECT_FWGK = hOSTPROTECT_FWGK;
	}

	public long getINPUTSURVEY_JRGK() {
		return INPUTSURVEY_JRGK;
	}

	public void setINPUTSURVEY_JRGK(long iNPUTSURVEY_JRGK) {
		INPUTSURVEY_JRGK = iNPUTSURVEY_JRGK;
	}

	public long getCHECKCOUNT_AQHCTJ() {
		return CHECKCOUNT_AQHCTJ;
	}

	public void setCHECKCOUNT_AQHCTJ(long cHECKCOUNT_AQHCTJ) {
		CHECKCOUNT_AQHCTJ = cHECKCOUNT_AQHCTJ;
	}

	public long getSYSAGENT_VERSIONNUM_ZJXTDLTJ() {
		return SYSAGENT_VERSIONNUM_ZJXTDLTJ;
	}

	public void setSYSAGENT_VERSIONNUM_ZJXTDLTJ(long sYSAGENT_VERSIONNUM_ZJXTDLTJ) {
		SYSAGENT_VERSIONNUM_ZJXTDLTJ = sYSAGENT_VERSIONNUM_ZJXTDLTJ;
	}

	public long getPERIPHERALACCESSINFO_WSJRJS() {
		return PERIPHERALACCESSINFO_WSJRJS;
	}

	public void setPERIPHERALACCESSINFO_WSJRJS(long pERIPHERALACCESSINFO_WSJRJS) {
		PERIPHERALACCESSINFO_WSJRJS = pERIPHERALACCESSINFO_WSJRJS;
	}

	public long getDANGER_SERVICE_MONITOR_GWFWJS() {
		return DANGER_SERVICE_MONITOR_GWFWJS;
	}

	public void setDANGER_SERVICE_MONITOR_GWFWJS(long dANGER_SERVICE_MONITOR_GWFWJS) {
		DANGER_SERVICE_MONITOR_GWFWJS = dANGER_SERVICE_MONITOR_GWFWJS;
	}
	
	
}

package com.CrossCountry.Survey.intervalutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityMonitoringIntervalUtils {

	//电力监控系统网络安全告警监视：当日安全风险处置top10
	@Value("${screen_alarmmonitor_aqfxczt:5000}")
	private long ALARMMONITOR_AQFXCZT;
	
	//电力监控系统网络安全告警监视：当日安全风险监测top10
	@Value("${screen_alarmmonitor_aqfxjct:5000}")
	private long ALARMMONITOR_AQFXJCT;
	
	//电力监控系统网络安全告警监视：告警分布
	@Value("${screen_alarmmonitor_gjfb:5000}")
	private long ALARMMONITOR_GJFB;
	
	//电力监控系统网络安全告警监视：当日发生告警设备
	@Value("${screen_alarmmonitor_drgj:5000}")
	private long ALARMMONITOR_DRGJ;
	
	//电力监控系统网络安全告警监视：告警统计：主站告警分布
	@Value("${screen_mainstationalarmdistribution_zzgjfb:5000}")
	private long MAINSTATIONALARMDISTRIBUTION_ZZGJFB;
	
	//电力监控系统网络安全告警监视：告警统计：厂站告警情况
	@Value("${screen_substationalarmdistribution_czgjqk:5000}")
	private long SUBSTATIONALARMDISTRIBUTION_CZGJQK;
	
	//电力监控系统网络安全告警监视：告警统计：告警曲线
	@Value("${screen_riskmon_view_gjqx:5000}")
	private long RISKMON_VIEW_GJQX;
	
	//电力监控系统网络安全告警监视：告警统计
	@Value("${screen_alarmmonitor_gjtj:5000}")
	private long ALARMMONITOR_GJTJ;

	public long getALARMMONITOR_AQFXCZT() {
		return ALARMMONITOR_AQFXCZT;
	}

	public void setALARMMONITOR_AQFXCZT(long aLARMMONITOR_AQFXCZT) {
		ALARMMONITOR_AQFXCZT = aLARMMONITOR_AQFXCZT;
	}

	public long getALARMMONITOR_AQFXJCT() {
		return ALARMMONITOR_AQFXJCT;
	}

	public void setALARMMONITOR_AQFXJCT(long aLARMMONITOR_AQFXJCT) {
		ALARMMONITOR_AQFXJCT = aLARMMONITOR_AQFXJCT;
	}

	public long getALARMMONITOR_GJFB() {
		return ALARMMONITOR_GJFB;
	}

	public void setALARMMONITOR_GJFB(long aLARMMONITOR_GJFB) {
		ALARMMONITOR_GJFB = aLARMMONITOR_GJFB;
	}

	public long getALARMMONITOR_DRGJ() {
		return ALARMMONITOR_DRGJ;
	}

	public void setALARMMONITOR_DRGJ(long aLARMMONITOR_DRGJ) {
		ALARMMONITOR_DRGJ = aLARMMONITOR_DRGJ;
	}

	public long getMAINSTATIONALARMDISTRIBUTION_ZZGJFB() {
		return MAINSTATIONALARMDISTRIBUTION_ZZGJFB;
	}

	public void setMAINSTATIONALARMDISTRIBUTION_ZZGJFB(long mAINSTATIONALARMDISTRIBUTION_ZZGJFB) {
		MAINSTATIONALARMDISTRIBUTION_ZZGJFB = mAINSTATIONALARMDISTRIBUTION_ZZGJFB;
	}

	public long getSUBSTATIONALARMDISTRIBUTION_CZGJQK() {
		return SUBSTATIONALARMDISTRIBUTION_CZGJQK;
	}

	public void setSUBSTATIONALARMDISTRIBUTION_CZGJQK(long sUBSTATIONALARMDISTRIBUTION_CZGJQK) {
		SUBSTATIONALARMDISTRIBUTION_CZGJQK = sUBSTATIONALARMDISTRIBUTION_CZGJQK;
	}

	public long getRISKMON_VIEW_GJQX() {
		return RISKMON_VIEW_GJQX;
	}

	public void setRISKMON_VIEW_GJQX(long rISKMON_VIEW_GJQX) {
		RISKMON_VIEW_GJQX = rISKMON_VIEW_GJQX;
	}

	public long getALARMMONITOR_GJTJ() {
		return ALARMMONITOR_GJTJ;
	}

	public void setALARMMONITOR_GJTJ(long aLARMMONITOR_GJTJ) {
		ALARMMONITOR_GJTJ = aLARMMONITOR_GJTJ;
	}
	
}

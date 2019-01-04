package com.CrossCountry.Survey.intervalutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NetWorkMonitorIntervalUtils {

	//网络监测能力：安全运行指数(原来的告警率及告警解决率)
	@Value("${screen_networkmonitor_aqyxzs:5000}")
	private long NETWORKMONITOR_AQYXZS;
	
	//网络监测能力：平台部署情况
	@Value("${screen_networkmonitor_ptbsqk:5000}")
	private long NETWORKMONITOR_PTBSQK;
	
	//网络监测能力：各省厂站装置部署数量、厂站装置部署情况
	@Value("${screen_substationdevicedeploy_czzzbs:5000}")
	private long SUBSTATIONDEVICEDEPLOY_CZZZBS;
	
	//网络监测能力：各类告警设备
	@Value("${screen_supsecuritydevnum_glgjsb:5000}")
	private long SUPSECURITYDEVNUM_GLGJSB;

	public long getNETWORKMONITOR_AQYXZS() {
		return NETWORKMONITOR_AQYXZS;
	}

	public void setNETWORKMONITOR_AQYXZS(long nETWORKMONITOR_AQYXZS) {
		NETWORKMONITOR_AQYXZS = nETWORKMONITOR_AQYXZS;
	}

	public long getNETWORKMONITOR_PTBSQK() {
		return NETWORKMONITOR_PTBSQK;
	}

	public void setNETWORKMONITOR_PTBSQK(long nETWORKMONITOR_PTBSQK) {
		NETWORKMONITOR_PTBSQK = nETWORKMONITOR_PTBSQK;
	}

	public long getSUBSTATIONDEVICEDEPLOY_CZZZBS() {
		return SUBSTATIONDEVICEDEPLOY_CZZZBS;
	}

	public void setSUBSTATIONDEVICEDEPLOY_CZZZBS(long sUBSTATIONDEVICEDEPLOY_CZZZBS) {
		SUBSTATIONDEVICEDEPLOY_CZZZBS = sUBSTATIONDEVICEDEPLOY_CZZZBS;
	}

	public long getSUPSECURITYDEVNUM_GLGJSB() {
		return SUPSECURITYDEVNUM_GLGJSB;
	}

	public void setSUPSECURITYDEVNUM_GLGJSB(long sUPSECURITYDEVNUM_GLGJSB) {
		SUPSECURITYDEVNUM_GLGJSB = sUPSECURITYDEVNUM_GLGJSB;
	}
	
	
}

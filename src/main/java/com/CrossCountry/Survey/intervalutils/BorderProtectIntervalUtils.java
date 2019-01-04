package com.CrossCountry.Survey.intervalutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BorderProtectIntervalUtils {

	//边界防护：边界核查：近一周的核查情况
	@Value("${screen_borderprotect_yzhcqk:5000}")
	private long BORDERPROTECT_YZHCQK;
	
	//边界防护：边界核查：各网省公司纵向情况
	@Value("${screen_borderprotect_wsgszxqk:5000}")
	private long BORDERPROTECT_WSGSZXQK;
	
	//边界防护：安全防护体系
	@Value("${screen_borderprotect_aqfhtx:5000}")
	private long BORDERPROTECT_AQFHTX;
	
	//边界防护：全网电力专用安防设备建设情况
	@Value("${screen_borderprotect_qwdlzy:5000}")
	private long BORDERPROTECT_QWDLZY;
	
	//边界防护：密通情况TOP10
	@Value("${screen_borderprotect_mtqk:5000}")
	private long BORDERPROTECT_MTQK;
	
	//边界防护：边界分析：安全事件分析
	@Value("${screen_securityeventanalysis_aqsjfx:5000}")
	private long SECURITYEVENTANALYSIS_AQSJFX;
	
	//边界防护：边界审计：总变更次数
	@Value("${screen_totalchangetimes_zbgcs:5000}")
	private long TOTALCHANGETIMES_ZBGCS;
	
	//边界防护：告警审计top5
	@Value("${screen_safetyeventweek_gjsjtw:5000}")
	private long SAFETYEVENTWEEK_GJSJTW;
	
	//边界防护：纵向隔离消缺
	@Value("${screen_borderprotect_zxglxq:5000}")
	private long BORDERPROTECT_ZXGLXQ;
	
	//边界防护：典型事件一周事件分析
	@Value("${screen_borderprotect_dxsjfx:5000}")
	private long BORDERPROTECT_DXSJFX;

	public long getBORDERPROTECT_YZHCQK() {
		return BORDERPROTECT_YZHCQK;
	}

	public void setBORDERPROTECT_YZHCQK(long bORDERPROTECT_YZHCQK) {
		BORDERPROTECT_YZHCQK = bORDERPROTECT_YZHCQK;
	}

	public long getBORDERPROTECT_WSGSZXQK() {
		return BORDERPROTECT_WSGSZXQK;
	}

	public void setBORDERPROTECT_WSGSZXQK(long bORDERPROTECT_WSGSZXQK) {
		BORDERPROTECT_WSGSZXQK = bORDERPROTECT_WSGSZXQK;
	}

	public long getBORDERPROTECT_AQFHTX() {
		return BORDERPROTECT_AQFHTX;
	}

	public void setBORDERPROTECT_AQFHTX(long bORDERPROTECT_AQFHTX) {
		BORDERPROTECT_AQFHTX = bORDERPROTECT_AQFHTX;
	}

	public long getBORDERPROTECT_QWDLZY() {
		return BORDERPROTECT_QWDLZY;
	}

	public void setBORDERPROTECT_QWDLZY(long bORDERPROTECT_QWDLZY) {
		BORDERPROTECT_QWDLZY = bORDERPROTECT_QWDLZY;
	}

	public long getBORDERPROTECT_MTQK() {
		return BORDERPROTECT_MTQK;
	}

	public void setBORDERPROTECT_MTQK(long bORDERPROTECT_MTQK) {
		BORDERPROTECT_MTQK = bORDERPROTECT_MTQK;
	}

	public long getSECURITYEVENTANALYSIS_AQSJFX() {
		return SECURITYEVENTANALYSIS_AQSJFX;
	}

	public void setSECURITYEVENTANALYSIS_AQSJFX(long sECURITYEVENTANALYSIS_AQSJFX) {
		SECURITYEVENTANALYSIS_AQSJFX = sECURITYEVENTANALYSIS_AQSJFX;
	}

	public long getTOTALCHANGETIMES_ZBGCS() {
		return TOTALCHANGETIMES_ZBGCS;
	}

	public void setTOTALCHANGETIMES_ZBGCS(long tOTALCHANGETIMES_ZBGCS) {
		TOTALCHANGETIMES_ZBGCS = tOTALCHANGETIMES_ZBGCS;
	}

	public long getSAFETYEVENTWEEK_GJSJTW() {
		return SAFETYEVENTWEEK_GJSJTW;
	}

	public void setSAFETYEVENTWEEK_GJSJTW(long sAFETYEVENTWEEK_GJSJTW) {
		SAFETYEVENTWEEK_GJSJTW = sAFETYEVENTWEEK_GJSJTW;
	}

	public long getBORDERPROTECT_ZXGLXQ() {
		return BORDERPROTECT_ZXGLXQ;
	}

	public void setBORDERPROTECT_ZXGLXQ(long bORDERPROTECT_ZXGLXQ) {
		BORDERPROTECT_ZXGLXQ = bORDERPROTECT_ZXGLXQ;
	}

	public long getBORDERPROTECT_DXSJFX() {
		return BORDERPROTECT_DXSJFX;
	}

	public void setBORDERPROTECT_DXSJFX(long bORDERPROTECT_DXSJFX) {
		BORDERPROTECT_DXSJFX = bORDERPROTECT_DXSJFX;
	}
	
	
	
	
}

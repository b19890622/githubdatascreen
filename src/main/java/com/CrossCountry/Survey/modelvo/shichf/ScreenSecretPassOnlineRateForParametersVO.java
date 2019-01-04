package com.CrossCountry.Survey.modelvo.shichf;

import java.util.List;

public class ScreenSecretPassOnlineRateForParametersVO {
	private String allOnlineRate;// 全网在线率
	private String allSecretPass;// 全网密通率
	private String urgentWarningsCount;// 全网紧急告警数
	private String importantWarningsCount; // 全网重要告警数
	private String secrityDays;// 安全运行天数
	private List<ScreenSecretPassOnlineRateProtobufVO> list;
	private String onlineName;// 显示的名字是全网在线率还是辽宁省在线率
	private String secretPassName; // 显示的名字是全网密通率还是辽宁省密通率
	private String urgentWarningsName; // 显示的名字是全网紧急告警数还是辽宁省紧急告警数
	private String importantWarningsName; // 显示的名字是全网重要告警数还是辽宁省重要告警数

	public String getOnlineName() {
		return onlineName;
	}

	public void setOnlineName(String onlineName) {
		this.onlineName = onlineName;
	}

	public String getSecretPassName() {
		return secretPassName;
	}

	public void setSecretPassName(String secretPassName) {
		this.secretPassName = secretPassName;
	}

	public String getUrgentWarningsName() {
		return urgentWarningsName;
	}

	public void setUrgentWarningsName(String urgentWarningsName) {
		this.urgentWarningsName = urgentWarningsName;
	}

	public String getImportantWarningsName() {
		return importantWarningsName;
	}

	public void setImportantWarningsName(String importantWarningsName) {
		this.importantWarningsName = importantWarningsName;
	}

	public String getUrgentWarningsCount() {
		return urgentWarningsCount;
	}

	public void setUrgentWarningsCount(String urgentWarningsCount) {
		this.urgentWarningsCount = urgentWarningsCount;
	}

	public String getImportantWarningsCount() {
		return importantWarningsCount;
	}

	public void setImportantWarningsCount(String importantWarningsCount) {
		this.importantWarningsCount = importantWarningsCount;
	}

	public String getSecrityDays() {
		return secrityDays;
	}

	public void setSecrityDays(String secrityDays) {
		this.secrityDays = secrityDays;
	}

	public List<ScreenSecretPassOnlineRateProtobufVO> getList() {
		return list;
	}

	public void setList(List<ScreenSecretPassOnlineRateProtobufVO> list) {
		this.list = list;
	}

	public String getAllOnlineRate() {
		return allOnlineRate;
	}

	public void setAllOnlineRate(String allOnlineRate) {
		this.allOnlineRate = allOnlineRate;
	}

	public String getAllSecretPass() {
		return allSecretPass;
	}

	public void setAllSecretPass(String allSecretPass) {
		this.allSecretPass = allSecretPass;
	}

}

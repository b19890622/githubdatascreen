package com.CrossCountry.Survey.commonservice.dongl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.CheckCountDao;
import com.CrossCountry.Survey.modelvo.dongl.CheckCountReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.SafetyCheckEntity;

@Component
public class CheckCountCommonService {

	@Autowired
	private CheckCountDao checkCountDao;

	public CheckCountReplyVO getCheckCount(Object object) {
		CheckCountReplyVO checkCountReplyVO = new CheckCountReplyVO();
		List<SafetyCheckEntity> safetyCheckEntityList = checkCountDao.getSafetyCheckEntity();
		int mainNum = 0;
		String uncomTotal = "";
		String safeTotal = "";
		String weekPassTotal = "";
		double comBeforeNum = 0.00;
		double safeBeforeNum= 0.00;
		double weekBeforeNum = 0.00;
		if (null == safetyCheckEntityList) {
			safetyCheckEntityList = new ArrayList<SafetyCheckEntity>();
		}

		for (SafetyCheckEntity aa : safetyCheckEntityList) {
			if (aa.getCheckName().equals("主机总数")) {
				mainNum = Integer.parseInt(aa.getCheckNum());
				break;
			}
		}

		for (SafetyCheckEntity a : safetyCheckEntityList) {
			if (a.getCheckName().equals("不合规项")) {
				checkCountReplyVO.setUnCompliance(a.getCheckNum());
				uncomTotal = a.getTotal();
				comBeforeNum = Double.parseDouble(a.getCheckNum())/ (Double.parseDouble(uncomTotal) * mainNum);
				checkCountReplyVO.setUnComPercent(String.valueOf(Math.ceil(comBeforeNum*100) ));
			}
			if (a.getCheckName().equals("安全漏洞")) {
				checkCountReplyVO.setSafetyFlaw(a.getCheckNum());
				safeTotal= a.getTotal();
				safeBeforeNum= Double.parseDouble(a.getCheckNum()) / (Double.parseDouble(safeTotal) * mainNum);
				checkCountReplyVO.setSafetyPercent(String.valueOf(Math.ceil(safeBeforeNum*100)));
			}
			if (a.getCheckName().equals("弱口令")) {
				checkCountReplyVO.setWeakPass(a.getCheckNum());
				weekPassTotal= a.getTotal();
				weekBeforeNum = Double.parseDouble(a.getCheckNum()) / (Double.parseDouble(weekPassTotal) * mainNum);
				checkCountReplyVO.setWeakPassPercent(String.valueOf(Math.ceil(weekBeforeNum*100)));
			}
		}

		if (checkCountReplyVO.getUnCompliance() == "" || checkCountReplyVO.getUnCompliance() == null) {
			checkCountReplyVO.setUnCompliance("0");
		} else if (checkCountReplyVO.getSafetyFlaw() == "" || checkCountReplyVO.getSafetyFlaw() == null) {
			checkCountReplyVO.setSafetyFlaw("0");
		} else if (checkCountReplyVO.getWeakPass() == "" || checkCountReplyVO.getWeakPass() == null) {
			checkCountReplyVO.setWeakPass("0");
		}
		return checkCountReplyVO;
	}
}

package com.CrossCountry.Survey.commonservice.dongl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.shichf.ScreenDrawMapDataService;
import com.CrossCountry.Survey.mapper.dongl.OnlineRateAndSecretPassDao;
import com.CrossCountry.Survey.modelvo.dongl.OnlineRateAnadSecretPassVO;
import com.CrossCountry.Survey.modelvo.dongl.SecretPassRateEntity;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.CrossCountry.Survey.modelvo.dongl.OnlineRateEntity;

@Component
public class OnlineRateAndSecretPassCommonService {

	@Autowired
	private OnlineRateAndSecretPassDao onelineRateAndSecretPassDao;

	public OnlineRateAnadSecretPassVO getOnlineRateAnadSecretPass(Object object) {
		OnlineRateAnadSecretPassVO onlineRateAnadSecretPassVO = new OnlineRateAnadSecretPassVO();
		SecretPassRateEntity secretPassRateEntity = new SecretPassRateEntity();
		OnlineRateEntity onlineRateEntity = new OnlineRateEntity();

		// 密通率
		double secretPassRate = 0;
		DecimalFormat dfSecretPass = new DecimalFormat("#0.00");
		try {
			secretPassRateEntity = onelineRateAndSecretPassDao.getSecretPassRateEntity();
			double secretPassFlow = secretPassRateEntity.getSECRETPASSFLOW();
			double traffic = secretPassRateEntity.getTRAFFIC();
			if (0 == traffic) {
				secretPassRate = 100;
			} else {
				secretPassRate = (secretPassFlow / traffic) * 100;
			}
			onlineRateAnadSecretPassVO.setSecretPass(dfSecretPass.format(secretPassRate) + "%");
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataService.class, e.getMessage(), e);
			secretPassRate = 100;
			onlineRateAnadSecretPassVO.setSecretPass(dfSecretPass.format(secretPassRate) + "%");
		}

		// 密通率颜色
		if (secretPassRate < 90) {
			onlineRateAnadSecretPassVO.setSecretPassColor("3");
		} else if (secretPassRate >= 90 && secretPassRate < 95) {
			onlineRateAnadSecretPassVO.setSecretPassColor("2");
		} else {
			onlineRateAnadSecretPassVO.setSecretPassColor("1");
		}
		// 在线率

		double onlineRate = 0;
		DecimalFormat dfOnlineRate = new DecimalFormat("#0.00");
		try {
			onlineRateEntity = onelineRateAndSecretPassDao.getOnlineRateEntity();
			double online = onlineRateEntity.getONLINE();
			double total = onlineRateEntity.getTOTAL();

			if (0 == total) {
				onlineRate = 0;
			} else {
				onlineRate = (online / total) * 100;
			}
			onlineRateAnadSecretPassVO.setOnlineRate(dfOnlineRate.format(onlineRate) + "%");
		} catch (Exception e) {
			onlineRate=0;
			onlineRateAnadSecretPassVO.setOnlineRate(dfOnlineRate.format(onlineRate) + "%");
		}

		// 在线率颜色
		if (onlineRate < 90) {
			onlineRateAnadSecretPassVO.setOnlineRateColor("3");
		} else if (onlineRate >= 90 && onlineRate < 95) {
			onlineRateAnadSecretPassVO.setOnlineRateColor("2");
		} else {
			onlineRateAnadSecretPassVO.setOnlineRateColor("1");
		}
		return onlineRateAnadSecretPassVO;
	}
}

package com.CrossCountry.Survey.commonservice.dongl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.dongl.AlarmMonitorSubDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.dongl.AlarmMonitorAllVO;

@Component
public class AlarmMonitorAllCommonService {

	@Autowired
	private AlarmMonitorSubDao alarmMonitorSubDao;
	@Autowired
	private DataChangePublicService dataChangePublicService;

	public AlarmMonitorAllVO getAlarmMonitorAll(Object object) {
		AlarmMonitorAllVO alarmMonitorAllVO = new AlarmMonitorAllVO();
		int outThreatAll = 0;
		int innerThreatAll = 0;
		int externalDeviceAll = 0;
		int exceptionLoginAll = 0;
		int dangerhandleAll = 0;
		int outVisitAll = 0;
		CommonArgs commonArgs = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		externalDeviceAll = alarmMonitorSubDao.getMethod1AllNum(name);
		exceptionLoginAll = alarmMonitorSubDao.getMethod2AllNum(name);
		dangerhandleAll = alarmMonitorSubDao.getMethod3AllNum(name);
		outVisitAll = alarmMonitorSubDao.getMethod4AllNum(name);
		innerThreatAll = alarmMonitorSubDao.getMethod5AllNum(name);
		outThreatAll = alarmMonitorSubDao.getMethod6AllNum(name);

		alarmMonitorAllVO.setOutThreatAll(outThreatAll);
		alarmMonitorAllVO.setInnerThreatAll(innerThreatAll);
		alarmMonitorAllVO.setExternalDeviceAll(externalDeviceAll);
		alarmMonitorAllVO.setExceptionLoginAll(exceptionLoginAll);
		alarmMonitorAllVO.setDangerhandleAll(dangerhandleAll);
		alarmMonitorAllVO.setOutVisitAll(outVisitAll);
		if (StringUtils.isBlank(name)) {
			alarmMonitorAllVO.setName("风险监视");
		} else {
			alarmMonitorAllVO.setName(commonArgs.getName() + "风险监视");
		}
		return alarmMonitorAllVO;
	}

}

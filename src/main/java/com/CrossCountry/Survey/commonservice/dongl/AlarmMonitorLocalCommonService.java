package com.CrossCountry.Survey.commonservice.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.AlarmMonitorLocalDao;
import com.CrossCountry.Survey.modelvo.dongl.AlarmMonitorLocalVo;

@Component
public class AlarmMonitorLocalCommonService {

	@Autowired
	private AlarmMonitorLocalDao alarmMonitorLocalDao;

	/*
	 * 告警监视本级
	 * 
	 */
	public AlarmMonitorLocalVo getAlarmMonitorLocal(Object object) {
		AlarmMonitorLocalVo alarmmonitorLocalVo = new AlarmMonitorLocalVo();
		int outThreatLocal = 0;
		int innerThreatLocal = 0;
		int externalDeviceLocal = 0;
		int exceptionLoginLocal = 0;
		int dangerhandleLocal = 0;
		int outVisitLocal = 0;
		innerThreatLocal = alarmMonitorLocalDao.getMethod6LocalNum();
		outThreatLocal =  alarmMonitorLocalDao.getMethod5LocalNum();
		externalDeviceLocal = alarmMonitorLocalDao.getMethod1LocalNum();
		exceptionLoginLocal = alarmMonitorLocalDao.getMethod2LocalNum();
		dangerhandleLocal = alarmMonitorLocalDao.getMethod3LocalNum();
		outVisitLocal = alarmMonitorLocalDao.getMethod4LocalNum();

		alarmmonitorLocalVo.setOutThreatLocal(outThreatLocal);
		alarmmonitorLocalVo.setInnerThreatLocal(innerThreatLocal);
		alarmmonitorLocalVo.setExternalDeviceLocal(externalDeviceLocal);
		alarmmonitorLocalVo.setExceptionLoginLocal(exceptionLoginLocal);
		alarmmonitorLocalVo.setOutVisitLocal(outVisitLocal);
		alarmmonitorLocalVo.setDangerhandleLocal(dangerhandleLocal);
		return alarmmonitorLocalVo;
	}

}

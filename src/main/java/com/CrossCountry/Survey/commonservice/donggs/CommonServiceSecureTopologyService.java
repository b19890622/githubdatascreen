package com.CrossCountry.Survey.commonservice.donggs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.SecureTopologyDao;
import com.CrossCountry.Survey.modelvo.donggs.AlarmNameAndNumber;
import com.CrossCountry.Survey.modelvo.donggs.PropertyDataAndOftenData;
import com.CrossCountry.Survey.modelvo.donggs.PropertyInDeviceReplyData;
import com.CrossCountry.Survey.modelvo.donggs.PropertyInDeviceReplyEntity;
import com.CrossCountry.Survey.modelvo.donggs.SecureTopology;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class CommonServiceSecureTopologyService {
	@Autowired
	SecureTopologyDao secureTopologyDao;

	public SecureTopology getSecureTopology(Object obj) {
		SecureTopology secureTopology = new SecureTopology();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<AlarmNameAndNumber> alarmNameAndNumber = secureTopologyDao.getAlarmNameAndNumber();
		for (AlarmNameAndNumber alarmNameAndNumbervo : alarmNameAndNumber) {
			String warningState = alarmNameAndNumbervo.getWarningState();
			long warnningStartTime;
			try {
				warnningStartTime = sFormat.parse(warningState).getTime();
				if ((new Date().getTime() - warnningStartTime) / 1000 > 30) {
					alarmNameAndNumbervo.setWarningState("0");
				} else {
					alarmNameAndNumbervo.setWarningState("1");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				Log4jUtil.error(CommonServiceSecureTopologyService.class, e.getMessage(), e);
			}
		}
		List<PropertyDataAndOftenData> propertyDataAndOftenData = secureTopologyDao.getPropertyDataAndOftenData();
		secureTopology.setAlarmNameAndNumber(alarmNameAndNumber);
		secureTopology.setPropertyDataAndOftenData(propertyDataAndOftenData);
		List<PropertyInDeviceReplyEntity> list = secureTopologyDao.getPropertyInDeviceReplyEntity();
		Map<String, PropertyInDeviceReplyEntity> map = new HashMap<>();
		List<PropertyInDeviceReplyData> propertyInDeviceReply = new ArrayList<>();
		for (PropertyInDeviceReplyEntity propertyInDeviceReplyEntity : list) {
			map.put(propertyInDeviceReplyEntity.getSwIp(), propertyInDeviceReplyEntity);
		}
		for (Entry<String, PropertyInDeviceReplyEntity> entry : map.entrySet()) {
			String swIp = entry.getKey();
			PropertyInDeviceReplyData propertyInDeviceReplyData = new PropertyInDeviceReplyData();
			List<String> inIps = new ArrayList<>();
			for (PropertyInDeviceReplyEntity propertyInDeviceReplyEntity : list) {
				if (swIp.equals(propertyInDeviceReplyEntity.getSwIp())) {
					inIps.add(propertyInDeviceReplyEntity.getInIp());
				}
			}
			propertyInDeviceReplyData.setSwIp(swIp);
			propertyInDeviceReplyData.setInIp(inIps);
			propertyInDeviceReply.add(propertyInDeviceReplyData);
		}
//		// 超过30秒不返接入设备
//		if(list != null && list.size() > 0) {
//			long inTime = list.get(0).getTime().getTime();
//			if ((new Date().getTime() - inTime) / 1000 > 30) {
//				propertyInDeviceReply = null;
//			}
//		}
		secureTopology.setPropertyInDeviceReply(propertyInDeviceReply);
		return secureTopology;
	}
}

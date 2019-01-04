
package com.CrossCountry.Survey.commonservice.dongl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.MainStationAlarmDistributionDao;
import com.CrossCountry.Survey.modelvo.dongl.DistributionBannerEntity;
import com.CrossCountry.Survey.modelvo.dongl.MainStationAlarmDistributionEntity;
import com.CrossCountry.Survey.modelvo.dongl.MainStationAlarmDistributionReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.ViewMainStationEntity;

@Component
public class MainStationAlarmDistributionCommonService {

	@Autowired
	private MainStationAlarmDistributionDao mainStationAlarmDistributionDao;

	public MainStationAlarmDistributionReplyVO getMainStationAlarmDistribution(Object object) {
		MainStationAlarmDistributionReplyVO mainStationAlarmDistributionReplyVO = new MainStationAlarmDistributionReplyVO();
		DistributionBannerEntity distributionBannerEntity = new DistributionBannerEntity();
		List<String> curSevenDays = getStartTimeWeek();
		String curstart = curSevenDays.get(0);
		String curend = curSevenDays.get(curSevenDays.size() - 1);
		List<ViewMainStationEntity> allList = mainStationAlarmDistributionDao.getViewMainStationEntity(curstart,
				curend);
		List<MainStationAlarmDistributionEntity> checkList = new ArrayList<MainStationAlarmDistributionEntity>();
		List<MainStationAlarmDistributionEntity> realList = new ArrayList<MainStationAlarmDistributionEntity>();
		MainStationAlarmDistributionEntity en = null;
		for (String cur : curSevenDays) {
			en = new MainStationAlarmDistributionEntity();
			for (ViewMainStationEntity entity : allList) {
				if (entity.getXdate() != null) {
					if (cur.equals(entity.getXdate())) {
						if (entity.getDeviceType().equals("主机")) {
							en.setMasterNum(entity.getWarnNum());
						} else if (entity.getDeviceType().equals("网络设备")) {
							en.setNetNum(entity.getWarnNum());
						} else if (entity.getDeviceType().equals("数据库")) {
							en.setDbNum(entity.getWarnNum());
						} else if (entity.getDeviceType().equals("安防设备")) {
							en.setSafetyNum(entity.getWarnNum());
						}
					}
				}
			}
			en.setXdate(cur);
			checkList.add(en);
		}
		
		for (MainStationAlarmDistributionEntity m : checkList) {
			m.setDbNum(m.getDbNum() == null ? "0" : m.getDbNum());
			m.setNetNum(m.getNetNum() == null ? "0" : m.getNetNum());
			m.setMasterNum(m.getMasterNum() == null ? "0" : m.getMasterNum());
			m.setSafetyNum(m.getSafetyNum() == null ? "0" : m.getSafetyNum());
			realList.add(m);
		}

		MainStationAlarmDistributionEntity mainStationAlarmDistributionEntity = realList.get(checkList.size() - 1);
		distributionBannerEntity.setDbNum(mainStationAlarmDistributionEntity.getDbNum());
		distributionBannerEntity.setMasterNum(mainStationAlarmDistributionEntity.getMasterNum());
		distributionBannerEntity.setNetNum(mainStationAlarmDistributionEntity.getNetNum());
		distributionBannerEntity.setSafetyNum(mainStationAlarmDistributionEntity.getSafetyNum());

		mainStationAlarmDistributionReplyVO.setMainStationAlarmDistributionList(realList);
		mainStationAlarmDistributionReplyVO.setDistributionBannerEntity(distributionBannerEntity);
		return mainStationAlarmDistributionReplyVO;
	}


	public List<String> getStartTimeWeek() {
		SimpleDateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DATE, -7);
		List<String> dateList = new ArrayList<String>();
		String calDate = null;
		for (int i = 0; i < 7; i++) {
			rightNow.add(Calendar.DATE, 1);
			calDate = dfdate.format(rightNow.getTime());
			dateList.add(calDate);
		}
		return dateList;
	}
}


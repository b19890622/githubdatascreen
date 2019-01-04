package com.CrossCountry.Survey.commonservice.dongl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.SubStationAlarmDistributionDao;
import com.CrossCountry.Survey.modelvo.dongl.DistributionBannerEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubStationAlarmDistributionEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubStationAlarmDistributionReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.SubStationAlarmDistributionEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubStationAlarmDistributionReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.ViewSubStationEntity;
import com.CrossCountry.Survey.modelvo.dongl.ViewSubStationEntity;

@Component
public class SubStationAlarmDistributionCommonService {

	@Autowired
	private SubStationAlarmDistributionDao subStationAlarmDistributionDao;

	public SubStationAlarmDistributionReplyVO getSubStationAlarmDistribution(Object object) {
		SubStationAlarmDistributionReplyVO subStationAlarmDistributionReplyVO = new SubStationAlarmDistributionReplyVO();
		List<String> curSevenDays = getStartTimeWeek();
		String curstart = curSevenDays.get(0);
		String curend = curSevenDays.get(curSevenDays.size() - 1);
		List<ViewSubStationEntity> allList = subStationAlarmDistributionDao.getViewSubStationEntityList(curstart,
				curend);
		List<SubStationAlarmDistributionEntity> checkList = new ArrayList<SubStationAlarmDistributionEntity>();
		List<SubStationAlarmDistributionEntity> realList = new ArrayList<SubStationAlarmDistributionEntity>();
		SubStationAlarmDistributionEntity en = null;
		for (String cur : curSevenDays) {
			en = new SubStationAlarmDistributionEntity();
			for (ViewSubStationEntity entity : allList) {
				if (entity.getXdate() != null) {
					if (cur.equals(entity.getXdate())) {
						if (entity.getDeviceType().equals("电厂")) {
							en.setPowerPlantNum(entity.getWarnNum());
						} else if (entity.getDeviceType().equals("变电站")) {
							en.setSubstationNum(entity.getWarnNum());
						} 
					}
				}
			}
			en.setXdate(cur);
			checkList.add(en);
		}
		
		for (SubStationAlarmDistributionEntity m : checkList) {
			m.setPowerPlantNum(m.getPowerPlantNum() == null ? "0" : m.getPowerPlantNum());
			m.setSubstationNum(m.getSubstationNum() == null ? "0" : m.getSubstationNum());
			realList.add(m);
		}
		subStationAlarmDistributionReplyVO.setSubStationAlarmDistributionList(realList);
		return subStationAlarmDistributionReplyVO;
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


package com.CrossCountry.Survey.commonservice.dongl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.SecurityAnalasisDao;
import com.CrossCountry.Survey.modelvo.dongl.MainStationAlarmDistributionEntity;
import com.CrossCountry.Survey.modelvo.dongl.SecurityAnalasisEntity;
import com.CrossCountry.Survey.modelvo.dongl.SecurityAnalasisReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.ViewMainStationEntity;

@Component
public class SecurityAnalasisCommonService {

	@Autowired
	private SecurityAnalasisDao securityAnalasisDao;
	
	public SecurityAnalasisReplyVO getSecurityAnalasis(Object object) {
		SecurityAnalasisReplyVO securityAnalasisReplyVO = new SecurityAnalasisReplyVO();
		List<String> curSevenDays = getStartTimeWeek();
		String curstart = curSevenDays.get(0);
		String curend = curSevenDays.get(curSevenDays.size() - 1);
		List<SecurityAnalasisEntity> allList = securityAnalasisDao.getSecurityAnalasis(curstart,
				curend);
		List<SecurityAnalasisEntity> checkList = new ArrayList<SecurityAnalasisEntity>();
		List<SecurityAnalasisEntity> realList = new ArrayList<SecurityAnalasisEntity>();
		SecurityAnalasisEntity en = null;
		for (String cur : curSevenDays) {
			en = new SecurityAnalasisEntity();
			for (SecurityAnalasisEntity entity : allList) {
				if (entity.getXdate() != null) {
					if (cur.equals(entity.getXdate())) {
						if (entity.getXdate().equals(cur)) {
							en.setWnum0(entity.getWnum0());
							en.setWnum1(entity.getWnum1());
						} 
					}
				}
			}
			en.setXdate(cur);
			checkList.add(en);
		}
		
		for (SecurityAnalasisEntity m : checkList) {
			m.setWnum0(m.getWnum0() == null ? "0" : m.getWnum0());
			m.setWnum1(m.getWnum1() == null ? "0" : m.getWnum1());
			realList.add(m);
		}
		securityAnalasisReplyVO.setSecurityAnalasis(realList);
		return securityAnalasisReplyVO;
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

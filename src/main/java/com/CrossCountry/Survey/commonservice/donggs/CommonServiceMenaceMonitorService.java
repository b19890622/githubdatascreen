package com.CrossCountry.Survey.commonservice.donggs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.MenaceMonitorDao;
import com.CrossCountry.Survey.modelvo.donggs.AllData;
import com.CrossCountry.Survey.modelvo.donggs.ChangeRed;
import com.CrossCountry.Survey.modelvo.donggs.Power;
import com.CrossCountry.Survey.modelvo.donggs.RedWire;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class CommonServiceMenaceMonitorService {
	@Autowired
	MenaceMonitorDao menaceMonitorDao;
	@Autowired
	GoogleCacheUtils googleCacheUtils;

	/**
	 * @param obj
	 * @return
	 */
	public List<AllData> getAllData(Object obj) {

		List<ChangeRed> changeReds = menaceMonitorDao.getChangeRed();
		List<Power> powers = menaceMonitorDao.getPower();
		List<RedWire> redWires = menaceMonitorDao.getRedWire();
		Date newDateTime = new Date();
		Object objOnduty = googleCacheUtils.getCacheValue(obj.toString());

		// 判断有没有新的告警
		SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		for (ChangeRed changes : changeReds) {
			if (changes.getWarningtimes() != null) {
				changes.setWarningtime(aDate.format(changes.getWarningtimes()));
			}
			changes.setAddOrNo("false");
		}
		if (objOnduty == null) {
			// 储存第一次查询时间
			googleCacheUtils.putCacheValue(obj.toString(), newDateTime);
		} else if (objOnduty != null) {
			try {
				for (ChangeRed changes : changeReds) {
					// 比较第一次查询后有没有新的告警
					if (changes.getWarningtimes() != null) {

						if (((Date) googleCacheUtils.getCacheValue(obj.toString())).before(changes.getWarningtimes())) {

							changes.setAddOrNo("true");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(CommonServiceMenaceMonitorService.class, e.getMessage(), e);
			}
			googleCacheUtils.putCacheValue(obj.toString(), newDateTime);
		}
		Log4jUtil.fatal(CommonServiceMenaceMonitorService.class, "开始封装AllData");
		AllData allData = null;
		List<AllData> allDatas = new ArrayList<>();
		for (Power power : powers) {
			allData = new AllData();
			allData.setRegional(power.getRegional());
			allData.setRemark(power.getRemark());
			allData.setShortname(power.getShortname());
			for (ChangeRed changeRed : changeReds) {
				if (power.getRegional().equals(changeRed.getRegional())) {
					allData.setVoltageclas(changeRed.getVoltageclas());
					allData.setAddOrNo(changeRed.getAddOrNo());
					allData.setWarningtime(changeRed.getWarningtime());
					if (null == changeRed.getWarningType()) {
						allData.setWarningType("");
					} else {
						if (0 == changeRed.getWarningType()) {
							allData.setWarningType("0");
						} else if (1 == changeRed.getWarningType()) {
							allData.setWarningType("1");
						}
					}
				}
			}

			for (RedWire redWire : redWires) {
				if (power.getRegional().equals(redWire.getRegional())) {

					allData.setDevicestate(redWire.getDevicestate());
					allData.setNodename(redWire.getNodename());
				}

			}

			allDatas.add(allData);
		}

		return allDatas;
	}
}

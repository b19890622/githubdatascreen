package com.CrossCountry.Survey.commonservice.wangkun;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.wangkun.LastSecurityEventDao;
import com.CrossCountry.Survey.mapper.wangkun.LastSubSecurityEventDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventCondition;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventList;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventResponse;
import com.CrossCountry.Survey.modelvo.wangkun.SecurityEventVo;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.CrossCountry.Survey.utils.shichf.StringFilterUtil;

@Component
public class CommonServiceSecurityEventService {
	@Autowired
	private LastSecurityEventDao lastSecurityEventDao;
	@Autowired
	private LastSubSecurityEventDao lastSubSecurityEventDao;
	@Autowired
	GoogleCacheUtils cacheUtils;
	@Autowired
	private DataChangePublicService dataChangePublicService;

	/**
	 *
	 * @param object
	 * @return
	 */
	public SecurityEventResponse getSecurityEvent(Object object) throws ParseException {
		Date dateNew = new Date();
		SecurityEventResponse securityEventResponse = new SecurityEventResponse();
		Date memoryDate = (Date) cacheUtils.getCacheValue(object.toString());
		int i = 0;
		if (memoryDate == null) {
			i = 1;
			memoryDate = dateNew;
			cacheUtils.putCacheValue(object.toString(), memoryDate);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String beforeTime = sdf.format(memoryDate);
		// Date beforeDate = sdf.parse(beforeTime);
		List<SecurityEventVo> tempList = new ArrayList<>();
		List<SecurityEventList> list = new ArrayList<>();
		long nums = 0;
		long toSolvedNums = 0;
		nums = lastSecurityEventDao.getSecurityEventNums();
		toSolvedNums = lastSecurityEventDao.getSecurityEventToSolvedNums();
		tempList = lastSecurityEventDao.getTopOneHundredEvent(new SecurityEventCondition());
		int orderPostionLength = String.valueOf(nums + 1).length();
		int tempNum = Integer.valueOf(String.valueOf(nums)).intValue() + 1;
		// boolean flag = false;
		for (SecurityEventVo securityEventVo : tempList) {
			SecurityEventList securityEventList = new SecurityEventList();
			// index
			--tempNum;
			int tempNumLength = String.valueOf(tempNum).length();
			if (tempNumLength > 3) {
				int zeroCount = orderPostionLength - tempNumLength;
				StringBuffer order = new StringBuffer();
				for (int j = 0; j <= zeroCount; j++) {
					order.append(0);
				}
				securityEventList.setIndex(order.append(tempNum).toString());
			} else if (tempNumLength < 4) {
				int zeroCount = 4 - tempNumLength;
				StringBuffer order = new StringBuffer();
				for (int j = 0; j < zeroCount; j++) {
					order.append(0);
				}
				securityEventList.setIndex(order.append(tempNum).toString());
			} else {
				securityEventList.setIndex(String.valueOf(tempNum));
			}
			// warningTime
			securityEventList.setWarningTime(sdf.format(sdf.parse(securityEventVo.getWarningTime())));
			String remoteProvice = StringFilterUtil.filterName("NULL".equals(securityEventVo.getRemoteProvice())
					|| "null".equals(securityEventVo.getRemoteProvice()) ? "" : securityEventVo.getRemoteProvice());
			String localProvice = StringFilterUtil.filterName(
					"NULL".equals(securityEventVo.getLocalProvice()) || "null".equals(securityEventVo.getLocalProvice())
							? ""
							: securityEventVo.getLocalProvice());
			String proviceName = StringFilterUtil.filterName(
					"NULL".equals(securityEventVo.getProviceName()) || "null".equals(securityEventVo.getProviceName())
							? ""
							: securityEventVo.getProviceName());
			String describe = securityEventVo.getDescribe();
			// department
			if ("国调中心".equals(remoteProvice) && "国调中心".equals(localProvice)) {
				proviceName = "国调中心";
				securityEventList.setDepartment(proviceName);
			} else {
				if (StringUtils.isNotBlank(remoteProvice)) {
					if (StringUtils.isNotBlank(localProvice)) {
						if (remoteProvice.equals(localProvice)) {
							securityEventList.setDepartment(proviceName);
						} else {
							securityEventList.setDepartment(remoteProvice + "-->" + localProvice);
						}
					} else {
						securityEventList.setDepartment(remoteProvice + "-->" + "未知");

					}
				} else {
					if (StringUtils.isNotBlank(localProvice)) {
						securityEventList.setDepartment("未知" + "-->" + localProvice);
					} else {
						securityEventList.setDepartment(proviceName);

					}
				}
			}
			// describe
			securityEventList.setDescribe(describe);
			// eventFlag
			if (i != 1) { // 不是第一次
				Date dateWarn = sdf.parse(securityEventVo.getWarningTime());
				// 增加15秒动画消失
				long warnningStartTime = dateWarn.getTime();
				if ((dateNew.getTime() - warnningStartTime) / 1000 >= 20) {
					securityEventList.setEventFlag(false);
				} else {
					securityEventList.setEventFlag(true);
				}
//				if (dateWarn.after(beforeDate)) {
//					flag = true;
//					securityEventList.setEventFlag(true);
//				} else {
//					securityEventList.setEventFlag(false);
//
//				}
			} else {
				securityEventList.setEventFlag(false);
			}

			list.add(securityEventList);
		}
//		if (!flag && i != 1) {
//			// 跟上一次比较没有任何变化，就返回null。
//			return null;
//		}
//		if (flag) {
		cacheUtils.putCacheValue(object.toString(), sdf.parse(tempList.get(0).getWarningTime()));
//		}

		securityEventResponse.setSecurityEventVoList(list);
		securityEventResponse.setNums(nums);
		securityEventResponse.setToSolvedNums(toSolvedNums);
		return securityEventResponse;
	}

	/**
	 *
	 * @param object 传入的条件
	 * @return SecurityEventResponse
	 */
	public SecurityEventResponse getSubSecurityEvent(Object object) throws ParseException {
		Date dateNew = new Date();
		CommonArgs commonArgs = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		String key = commonArgs.getKey();
		SecurityEventResponse securityEventResponse = new SecurityEventResponse();
		Date memoryDate = (Date) cacheUtils.getCacheValue(key);
		int i = 0;
		if (memoryDate == null) {
			i = 1;
			memoryDate = dateNew;
			cacheUtils.putCacheValue(key, memoryDate);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String beforeTime = sdf.format(memoryDate);
		// Date beforeDate = sdf.parse(beforeTime);
		List<SecurityEventVo> tempList = new ArrayList<>();
		long nums = 0;
		long toSolvedNums = 0;
		if ("0".equals(StaticConfig.disSubValue)) {
			tempList = lastSubSecurityEventDao.getTopOneHundredEventByDisSub(name);
			nums = lastSubSecurityEventDao.getSubSecurityEventNumByDisSub(name);
			toSolvedNums = lastSubSecurityEventDao.getSubSecurityEventSolvedNumByDisSub(name);
		} else {
			tempList = lastSubSecurityEventDao.getTopOneHundredEvent(name);
			nums = lastSubSecurityEventDao.getSubSecurityEventNum(name);
			toSolvedNums = lastSubSecurityEventDao.getSubSecurityEventToSolvedNum(name);
		}
		List<SecurityEventList> list = new ArrayList<>();
		int orderPostionLength = String.valueOf(nums + 1).length();
		int tempNum = Integer.valueOf(String.valueOf(nums)).intValue() + 1;
		// boolean flag = false;
		for (SecurityEventVo securityEventVo : tempList) {
			SecurityEventList securityEventList = new SecurityEventList();
			// index
			--tempNum;
			if (orderPostionLength > 4) {
				int tempNumLength = String.valueOf(tempNum).length();
				int zeroCount = orderPostionLength - tempNumLength;
				StringBuffer order = new StringBuffer();
				for (int j = 0; j <= zeroCount; j++) {
					order.append(0);
				}
				securityEventList.setIndex(order.append(tempNum).toString());
			} else if (orderPostionLength < 5) {
				int tempNumLength = String.valueOf(tempNum).length();
				int zeroCount = 5 - tempNumLength;
				StringBuffer order = new StringBuffer();
				for (int j = 0; j < zeroCount; j++) {
					order.append(0);
				}
				securityEventList.setIndex(order.append(tempNum).toString());
			} else {
				securityEventList.setIndex(String.valueOf(tempNum));
			}
			// warningTime
			securityEventList.setWarningTime(sdf.format(sdf.parse(securityEventVo.getWarningTime())));
			String remoteProvice = StringFilterUtil.filterName("NULL".equals(securityEventVo.getRemoteProvice())
					|| "null".equals(securityEventVo.getRemoteProvice()) ? "" : securityEventVo.getRemoteProvice());
			String localProvice = StringFilterUtil.filterName(
					"NULL".equals(securityEventVo.getLocalProvice()) || "null".equals(securityEventVo.getLocalProvice())
							? ""
							: securityEventVo.getLocalProvice());
			String proviceName = StringFilterUtil.filterName(
					"NULL".equals(securityEventVo.getProviceName()) || "null".equals(securityEventVo.getProviceName())
							? ""
							: securityEventVo.getProviceName());
			String describe = securityEventVo.getDescribe();
			// department
			if (StringUtils.isBlank(name)) {
				if ("国调中心".equals(remoteProvice) && "国调中心".equals(localProvice)) {
					proviceName = "国调中心";
					securityEventList.setDepartment(proviceName);
				} else {
					if (StringUtils.isNotBlank(remoteProvice)) {
						if (StringUtils.isNotBlank(localProvice)) {
							if (remoteProvice.equals(localProvice)) {
								securityEventList.setDepartment(proviceName);
							} else {
								securityEventList.setDepartment(remoteProvice + "-->" + localProvice);
							}
						} else {
							securityEventList.setDepartment(remoteProvice + "-->" + "未知");

						}
					} else {
						if (StringUtils.isNotBlank(localProvice)) {
							securityEventList.setDepartment("未知" + "-->" + localProvice);
						} else {
							securityEventList.setDepartment(proviceName);

						}
					}
				}
			} else {
				if (StringUtils.isNotBlank(remoteProvice)) {
					if (StringUtils.isNotBlank(localProvice)) {
						if (remoteProvice.equals(localProvice)) {
							securityEventList.setDepartment(proviceName);
						} else {
							securityEventList.setDepartment(remoteProvice + "-->" + localProvice);
						}
					} else {
						securityEventList.setDepartment(remoteProvice + "-->" + "未知");

					}
				} else {
					if (StringUtils.isNotBlank(localProvice)) {
						securityEventList.setDepartment("未知" + "-->" + localProvice);
					} else {
						securityEventList.setDepartment(proviceName);

					}
				}
			}

			// describe
			securityEventList.setDescribe(describe);
			// eventFlag
			if (i != 1) { // 不是第一次
				Date dateWarn = sdf.parse(securityEventVo.getWarningTime());
				// 增加15秒动画消失
				long warnningStartTime = dateWarn.getTime();
				if ((dateNew.getTime() - warnningStartTime) / 1000 >= 20) {
					securityEventList.setEventFlag(false);
				} else {
					securityEventList.setEventFlag(true);
				}
//				if (dateWarn.after(beforeDate)) {
//					flag = true;
//					securityEventList.setEventFlag(true);
//				} else {
//					securityEventList.setEventFlag(false);
//
//				}
			} else {
				securityEventList.setEventFlag(false);
			}

			list.add(securityEventList);
		}
//		if (!flag && i != 1)
//
//		{
//			// 跟上一次比较没有任何变化，就返回null。
//		return null;
//		}
//		if (flag) {
		String randomKey = UUID.randomUUID().toString();
		commonArgs.setKey(randomKey);
		cacheUtils.putCacheValue(randomKey, sdf.parseObject(tempList.get(0).getWarningTime()));
//		}

		securityEventResponse.setSecurityEventVoList(list);
		securityEventResponse.setNums(nums);
		securityEventResponse.setToSolvedNums(toSolvedNums);
		return securityEventResponse;
	}
}

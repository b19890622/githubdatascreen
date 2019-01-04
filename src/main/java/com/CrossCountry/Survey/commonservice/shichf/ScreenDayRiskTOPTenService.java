package com.CrossCountry.Survey.commonservice.shichf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.shichf.ScreenDayRiskTOPTenDao;
import com.CrossCountry.Survey.mapper.wangkun.LastSubSecurityEventDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.shichf.ProvinceSecurityEventToSolvedNumVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenDayRiskTOPTenEntity;
import com.CrossCountry.Survey.modelvo.shichf.ScreenDayRiskTOPTenForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenDayRiskTOPTenProtobufVO;
import com.CrossCountry.Survey.utils.shichf.StringFilterUtil;

@Component
public class ScreenDayRiskTOPTenService {
	@Autowired
	private ScreenDayRiskTOPTenDao screenDayRiskTOPTenDao;
	@Autowired
	private DataChangePublicService dataChangePublicService;
	@Autowired
	private LastSubSecurityEventDao lastSubSecurityEventDao;

	public ScreenDayRiskTOPTenForParametersVO getScreenDayRiskTOPTenForParametersVO(Object object) {
		ScreenDayRiskTOPTenForParametersVO screenDayRiskTOPTenForParametersVO = new ScreenDayRiskTOPTenForParametersVO();
		List<ScreenDayRiskTOPTenProtobufVO> dayRiskTOPTenArray = new ArrayList<>();
		List<ScreenDayRiskTOPTenEntity> localList = new ArrayList<>();
		List<ScreenDayRiskTOPTenEntity> lowList = new ArrayList<>();
		CommonArgs commonArgs = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		if (StringUtils.isBlank(name)) {
			localList = screenDayRiskTOPTenDao.getScreenDayRiskTOPTenEntityList();
		} else {
			lowList = screenDayRiskTOPTenDao.getScreenDayRiskTOPTenEntitylowList(name);
		}
		List<ScreenDayRiskTOPTenEntity> list = new ArrayList<>();
		list.addAll(localList);
		list.addAll(lowList);
		Collections.sort(list, new Comparator<ScreenDayRiskTOPTenEntity>() {
			public int compare(ScreenDayRiskTOPTenEntity arg0, ScreenDayRiskTOPTenEntity arg1) {
				int count0 = arg0.getCount();
				int count1 = arg1.getCount();
				if (count1 > count0) {
					return 1;
				} else if (count1 == count0) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		for (int i = 0; i < list.size(); i++) {
			if (i > 9) {
				break;
			}
			ScreenDayRiskTOPTenEntity screenDayRiskTOPTenEntity = list.get(i);
			ScreenDayRiskTOPTenProtobufVO screenDayRiskTOPTenProtobufVO = new ScreenDayRiskTOPTenProtobufVO();
			screenDayRiskTOPTenProtobufVO.setKey(screenDayRiskTOPTenEntity.getAlarmType());
			screenDayRiskTOPTenProtobufVO.setValue(String.valueOf(screenDayRiskTOPTenEntity.getCount()));
			dayRiskTOPTenArray.add(screenDayRiskTOPTenProtobufVO);
		}
		screenDayRiskTOPTenForParametersVO.setDayRiskTOPTenArray(dayRiskTOPTenArray);
		return screenDayRiskTOPTenForParametersVO;
	}

	public ScreenDayRiskTOPTenForParametersVO getScreenDayRiskUnitTOPTenForParametersVO(Object object) {
		ScreenDayRiskTOPTenForParametersVO screenDayRiskTOPTenForParametersVO = new ScreenDayRiskTOPTenForParametersVO();
		List<ScreenDayRiskTOPTenProtobufVO> dayRiskTOPTenArray = new ArrayList<>();
		CommonArgs commonArgs = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		List<ProvinceSecurityEventToSolvedNumVO> provinceToSolvedNum = new ArrayList<>();
		List<ProvinceSecurityEventToSolvedNumVO> centerToSolvedNum = new ArrayList<>();
		List<ProvinceSecurityEventToSolvedNumVO> listAll = new ArrayList<>();
		if (StringUtils.isBlank(name)) {
			centerToSolvedNum = lastSubSecurityEventDao.getSubSecurityEventToSolvedNumByCenter();
			provinceToSolvedNum = lastSubSecurityEventDao.getSubSecurityEventToSolvedNumByProvince();
			listAll.addAll(provinceToSolvedNum);
			listAll.addAll(centerToSolvedNum);
			Collections.sort(listAll, new Comparator<ProvinceSecurityEventToSolvedNumVO>() {
				public int compare(ProvinceSecurityEventToSolvedNumVO arg0, ProvinceSecurityEventToSolvedNumVO arg1) {
					int count0 = Integer.valueOf(arg0.getCount());
					int count1 = Integer.valueOf(arg1.getCount());
					if (count1 > count0) {
						return 1;
					} else if (count1 == count0) {
						return 0;
					} else {
						return -1;
					}
				}
			});

			for (int i = 0; i < listAll.size(); i++) {
				if (i > 9) {
					break;
				}
				ProvinceSecurityEventToSolvedNumVO provinceSecurityEventToSolvedNumVO = listAll.get(i);
				ScreenDayRiskTOPTenProtobufVO screenDayRiskTOPTenProtobufVO = new ScreenDayRiskTOPTenProtobufVO();
				screenDayRiskTOPTenProtobufVO
						.setKey(StringFilterUtil.filterName(provinceSecurityEventToSolvedNumVO.getProvinceName()));
				screenDayRiskTOPTenProtobufVO.setValue(String.valueOf(provinceSecurityEventToSolvedNumVO.getCount()));
				dayRiskTOPTenArray.add(screenDayRiskTOPTenProtobufVO);
			}

		} else {
			List<ScreenDayRiskTOPTenEntity> unitList = screenDayRiskTOPTenDao
					.getScreenDayRiskUnitTOPTenEntitylowList(name);
			if (null != unitList && unitList.size() > 0) {
				for (int i = 0; i < unitList.size(); i++) {
					ScreenDayRiskTOPTenEntity screenDayRiskTOPTenEntity = unitList.get(i);
					ScreenDayRiskTOPTenProtobufVO screenDayRiskTOPTenProtobufVO = new ScreenDayRiskTOPTenProtobufVO();
					screenDayRiskTOPTenProtobufVO
							.setKey(StringFilterUtil.filterName(screenDayRiskTOPTenEntity.getAlarmType()));
					screenDayRiskTOPTenProtobufVO.setValue(String.valueOf(screenDayRiskTOPTenEntity.getCount()));
					dayRiskTOPTenArray.add(screenDayRiskTOPTenProtobufVO);
				}
			}
		}
		screenDayRiskTOPTenForParametersVO.setDayRiskTOPTenArray(dayRiskTOPTenArray);
		return screenDayRiskTOPTenForParametersVO;
	}
}

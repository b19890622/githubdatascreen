package com.CrossCountry.Survey.commonservice.shichf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.shichf.ScreenSecuritySystemDeviceDataDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.shichf.CascadeStatusAuditPo;
import com.CrossCountry.Survey.modelvo.shichf.SecuritySystemDeviceEntity;
import com.CrossCountry.Survey.modelvo.shichf.SecuritySystemStateForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.SecuritySystemStateVO;
import com.CrossCountry.Survey.utils.shichf.CommonCheck;

@Component
public class ScreenSecuritySystemDeviceDataService {
	@Autowired
	private ScreenSecuritySystemDeviceDataDao securitySystemDeviceDataDao;
	@Autowired
	private DataChangePublicService dataChangePublicService;

	public SecuritySystemStateForParametersVO getSecuritySystemStateForParametersVO(Object object) {
		SecuritySystemStateForParametersVO securitySystemStateForParametersVO = new SecuritySystemStateForParametersVO();
		List<SecuritySystemStateVO> list = new ArrayList<>();
		CommonArgs commonArgs = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		// 计算省调，地调平台数目
		int cityValue = createCustomPlatformMonitoringCount(list, name);
		// 计算设备数目
		createSecuritySystemDeviceCount(list, name, cityValue);
		securitySystemStateForParametersVO.setList(list);
		return securitySystemStateForParametersVO;
	}

	private int createCustomPlatformMonitoringCount(List<SecuritySystemStateVO> list, String name) {
		int areadiscenonline = 0;// 区域调度中心在线数
		// int areadiscenoutline = 0;// 区域调度中心离线数
		int provdiscenonline = 0;// 网省调度中心在线数
		// int provdiscenoutline = 0;// 网省调度中心离线数
		int citydiscenonline = 0;// 地市调度中心在线数
		// int citydiscenoutline = 0;// 地市调度中心离线数
		List<CascadeStatusAuditPo> cascadeStatusAuditPosAll = new ArrayList<>();
		List<CascadeStatusAuditPo> cascadeStatusAuditPosLow = new ArrayList<>();
		if (StringUtils.isBlank(name)) {
			cascadeStatusAuditPosAll = securitySystemDeviceDataDao.getCustomPlatformMonitoringProvince();
		} else {
			cascadeStatusAuditPosAll = securitySystemDeviceDataDao.getCustomPlatformMonitoringProvince();
			cascadeStatusAuditPosLow = securitySystemDeviceDataDao.getCustomPlatformMonitoring(name);
		}
		if (StringUtils.isBlank(name)) {
			for (CascadeStatusAuditPo po : cascadeStatusAuditPosAll) {
				// String status = po.getStatus();
				String level = po.getDispatchlevel();
//				if ("0".equals(status)) {
//					if ("1".equals(level)) {
//						areadiscenoutline++;
//					} else if ("2".equals(level)) {
//						provdiscenoutline++;
//					} else if ("3".equals(level)) {
//						citydiscenoutline++;
//					}
//				} else if ("1".equals(status)) {
				if ("1".equals(level)) {
					areadiscenonline++;
				} else if ("2".equals(level)) {
					provdiscenonline++;
				} else if ("3".equals(level)) {
					citydiscenonline++;
				}
				// }
			}
		} else {
			for (CascadeStatusAuditPo po : cascadeStatusAuditPosAll) {
				String level = po.getDispatchlevel();
				if ("1".equals(level)) {
					areadiscenonline++;
				} else if ("2".equals(level)) {
					provdiscenonline++;
				}
			}
			for (CascadeStatusAuditPo po : cascadeStatusAuditPosLow) {
				String level = po.getDispatchlevel();
				if ("3".equals(level)) {
					citydiscenonline++;
				}

			}
		}
		SecuritySystemStateVO provdiscenonVO = new SecuritySystemStateVO();
		SecuritySystemStateVO citydiscenonVO = new SecuritySystemStateVO();
		if (StringUtils.isBlank(name)) {
			provdiscenonVO.setFlag(34 > (provdiscenonline + areadiscenonline + 1) ? 1 : 0);
			provdiscenonVO.setName("省级以上平台");
			provdiscenonVO.setValue(
					34 > (provdiscenonline + areadiscenonline + 1) ? (provdiscenonline + areadiscenonline + 1) : 34);
		} else {
			provdiscenonVO.setFlag(0);
			provdiscenonVO.setName("省级以上平台");
			provdiscenonVO.setValue(1);
		}
		if (StringUtils.isBlank(name)) {
			citydiscenonVO.setFlag(313 > citydiscenonline ? 1 : 0);
		} else {
			citydiscenonVO.setFlag(0);
		}
		citydiscenonVO.setName("地调平台");
		citydiscenonVO.setValue(313 > citydiscenonline ? citydiscenonline : 313);
		list.add(provdiscenonVO);
		list.add(citydiscenonVO);
		return citydiscenonVO.getValue();
	}

	private void createSecuritySystemDeviceCount(List<SecuritySystemStateVO> list, String name, int cityValue) {
		String table = "DATA_UPREPORTSTATIONDEVINFO" + CommonCheck.getNowYear();
		List<SecuritySystemDeviceEntity> securitySystemList = securitySystemDeviceDataDao
				.getSecuritySystemDeviceEntitys(table, name);
		int devcount4 = 0;
		int devcount5 = 0;
		int devcount0 = 0;
		int devcount1 = 0;
		int devcount8 = 0;
		int devcount6 = 0;
		int devcount7 = 0;
		int devcount11 = 0;
		int devcount12 = 0;
		int devcount164 = 0;
		int devcount165 = 0;
		if (null != securitySystemList) {
			for (SecuritySystemDeviceEntity securitySystemDeviceEntity : securitySystemList) {
				if (0 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount0 = securitySystemDeviceEntity.getDevcount();
				}
				if (1 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount1 = securitySystemDeviceEntity.getDevcount();
				}
				if (4 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount4 = securitySystemDeviceEntity.getDevcount();
				}
				if (5 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount5 = securitySystemDeviceEntity.getDevcount();
				}
				if (6 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount6 = securitySystemDeviceEntity.getDevcount();
				}
				if (7 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount7 = securitySystemDeviceEntity.getDevcount();
				}
				if (8 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount8 = securitySystemDeviceEntity.getDevcount();
				}
				if (11 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount11 = securitySystemDeviceEntity.getDevcount();
				}
				if (12 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount12 = securitySystemDeviceEntity.getDevcount();
				}
				if (16 == securitySystemDeviceEntity.getDevicetype().intValue()) {
					devcount165 = securitySystemDeviceEntity.getDevcount();
				}
			}
		}
		SecuritySystemStateVO vo4 = new SecuritySystemStateVO();
		// 未来存入子设备类型会去掉这段代码
		if (StringUtils.isBlank(name)) {
			Integer checkTextOneCount = securitySystemDeviceDataDao.getCheckTextOneCount();
			if (null == checkTextOneCount) {
				devcount164 = 0;
			} else {
				devcount164 = checkTextOneCount.intValue() * 3;
			}
		} else {
			devcount164 = cityValue * 3;
		}
		vo4.setName("Ⅰ型监测装置");
		vo4.setValue(devcount164);
		vo4.setFlag(0);
		list.add(vo4);
		SecuritySystemStateVO vo5 = new SecuritySystemStateVO();
		vo5.setName("Ⅱ型监测装置");
		vo5.setValue((devcount165 - devcount164) < 0 ? 0 : (devcount165 - devcount164));
		vo5.setFlag(0);
		list.add(vo5);
		SecuritySystemStateVO vo01 = new SecuritySystemStateVO();
		vo01.setName("纵向加密");
		vo01.setValue(devcount6);
		vo01.setFlag(0);
		list.add(vo01);
		SecuritySystemStateVO voz = new SecuritySystemStateVO();
		voz.setName("正向隔离");
		voz.setValue(devcount4);
		voz.setFlag(0);
		list.add(voz);
		SecuritySystemStateVO vof = new SecuritySystemStateVO();
		vof.setName("反向隔离");
		vof.setValue(devcount5);
		vof.setFlag(0);
		list.add(vof);
		SecuritySystemStateVO vo6 = new SecuritySystemStateVO();
		vo6.setName("防火墙");
		vo6.setValue(devcount0);
		vo6.setFlag(0);
		list.add(vo6);
		SecuritySystemStateVO vo7 = new SecuritySystemStateVO();
		vo7.setName("入侵检测");
		vo7.setValue(devcount1);
		vo7.setFlag(0);
		list.add(vo7);
		SecuritySystemStateVO voAll = new SecuritySystemStateVO();
		voAll.setName("接入设备");
		voAll.setValue(devcount4 + devcount5 + devcount0 + devcount1 + devcount8 + devcount6 + devcount7 + devcount11
				+ devcount12 + devcount165);
		voAll.setFlag(0);
		list.add(voAll);

	}
}

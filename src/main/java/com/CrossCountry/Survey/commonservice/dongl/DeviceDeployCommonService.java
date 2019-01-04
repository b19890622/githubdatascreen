package com.CrossCountry.Survey.commonservice.dongl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.DeviceDeployDao;
import com.CrossCountry.Survey.modelvo.dongl.DeviceDeployEntity;
import com.CrossCountry.Survey.modelvo.dongl.DeviceDeployReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.PowerPlantEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubstationEntity;

@Component
public class DeviceDeployCommonService {

	@Autowired
	private DeviceDeployDao deviceDeployDao;

	public DeviceDeployReplyVO getDeviceDeploy(Object object) {
		DeviceDeployReplyVO deviceDeployReplyVO = new DeviceDeployReplyVO();
		List<DeviceDeployEntity> deviceDeployList = deviceDeployDao.getDeviceList();
		SubstationEntity substationEntity = deviceDeployDao.getSubstationEntity();
		PowerPlantEntity powerPlantEntity = deviceDeployDao.getPowerPlantEntity();
		deviceDeployReplyVO.setStationDeploy(deviceDeployList);
		deviceDeployReplyVO.setSubstaion(substationEntity);
		deviceDeployReplyVO.setPowerPlant(powerPlantEntity);
		deviceDeployReplyVO.setTotalNum(String.valueOf(Integer.parseInt(substationEntity.getSubSum()) + Integer.parseInt(powerPlantEntity.getPowerSum())));
		return deviceDeployReplyVO;
	}

}

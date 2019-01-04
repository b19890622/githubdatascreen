package com.CrossCountry.Survey.grpcmodel.dongl;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.dongl.DeviceDeployEntity;
import com.CrossCountry.Survey.modelvo.dongl.DeviceDeployReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.PowerPlantEntity;
import com.CrossCountry.Survey.modelvo.dongl.SubstationEntity;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.DeviceDeployReply;
import com.datascreen.PowerPlant;
import com.datascreen.StationDeploy;
import com.datascreen.Substation;

public class DeviceDeployCommonServiceToGrpc {
	public DeviceDeployReply getDeviceDeployReply(Object object) {
		DeviceDeployReply.Builder response = DeviceDeployReply.newBuilder();
		DeviceDeployReplyVO deviceDeployReplyVO =new DeviceDeployReplyVO();
		if (null != object) {
			deviceDeployReplyVO = (DeviceDeployReplyVO) object;
		}
		List<DeviceDeployEntity> a =deviceDeployReplyVO.getStationDeploy();
		if(null == a){
			a = new ArrayList<DeviceDeployEntity>();
		}
		List<StationDeploy> stationDeploylist  = new ArrayList<StationDeploy>();
		SubstationEntity substationEntity = deviceDeployReplyVO.getSubstaion();
		PowerPlantEntity powerPlantEntity = deviceDeployReplyVO.getPowerPlant();
		String totalNum = deviceDeployReplyVO.getTotalNum();
		try {
			for(DeviceDeployEntity deviceDeployEntity:a){
				StationDeploy stationDeploy = (StationDeploy) TransformToGrpcPo
						.convertToPojo(StationDeploy.class, deviceDeployEntity);
				stationDeploylist.add(stationDeploy);
			}
			if(stationDeploylist.size()>0){
				response.addAllStationDeploy(stationDeploylist);
			}
			
			Substation substation = (Substation) TransformToGrpcPo.convertToPojo(Substation.class,
					substationEntity);
			response.setSubstaion(substation);
			PowerPlant powerPlant = (PowerPlant) TransformToGrpcPo.convertToPojo(PowerPlant.class,
					powerPlantEntity);
			response.setPowerPlant(powerPlant);
			
			response.setTotalNum(totalNum);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(DeviceDeployCommonServiceToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

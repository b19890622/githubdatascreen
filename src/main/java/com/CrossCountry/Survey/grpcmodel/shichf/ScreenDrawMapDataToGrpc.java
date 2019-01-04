package com.CrossCountry.Survey.grpcmodel.shichf;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.shichf.DrawingMapsForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatCenterIndexVO;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatSecurityArrayVO;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatWarningArrayVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenDayRiskTOPTenForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenDayRiskTOPTenProtobufVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenSecretPassOnlineRateForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenSecretPassOnlineRateProtobufVO;
import com.CrossCountry.Survey.modelvo.shichf.SecuritySystemStateForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.SecuritySystemStateVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.DrawingMapsForParameters;
import com.datascreen.MapFormatCenterIndexProtobuf;
import com.datascreen.MapFormatSecurityArrayProtobuf;
import com.datascreen.MapFormatWarningArrayProtobuf;
import com.datascreen.ScreenDayRiskTOPTenForParameters;
import com.datascreen.ScreenDayRiskTOPTenProtobuf;
import com.datascreen.ScreenSecretPassOnlineRateForParameters;
import com.datascreen.ScreenSecretPassOnlineRateProtobuf;
import com.datascreen.SecuritySystemStateForParameters;
import com.datascreen.SecuritySystemStateProtobuf;

public class ScreenDrawMapDataToGrpc {
	/**
	 * 地图用转换grpc
	 * 
	 * @param object
	 * @return
	 */
	public DrawingMapsForParameters getDrawingMapsForParameters(Object object) {
		DrawingMapsForParameters.Builder response = DrawingMapsForParameters.newBuilder();
		DrawingMapsForParametersVO drawingMapsForParametersVO = new DrawingMapsForParametersVO();
		if (null != object) {
			drawingMapsForParametersVO = (DrawingMapsForParametersVO) object;
		}
		List<MapFormatSecurityArrayVO> securityArrayVOs = drawingMapsForParametersVO.getSecurityArray();
		List<MapFormatWarningArrayVO> warningArrayVOs = drawingMapsForParametersVO.getWarningArray();
		List<MapFormatCenterIndexVO> centerIndexArrayVOs = drawingMapsForParametersVO.getCenterIndexArray();
		if (null == securityArrayVOs) {
			securityArrayVOs = new ArrayList<>();
		}
		if (null == warningArrayVOs) {
			warningArrayVOs = new ArrayList<>();
		}
		if (null == centerIndexArrayVOs) {
			centerIndexArrayVOs = new ArrayList<>();
		}
		List<MapFormatSecurityArrayProtobuf> securityArrayProtobufList = new ArrayList<>();
		List<MapFormatWarningArrayProtobuf> warningArrayProtobufList = new ArrayList<>();
		List<MapFormatCenterIndexProtobuf> centerIndexProtobufList = new ArrayList<>();
		try {
			for (MapFormatSecurityArrayVO mapFormatSecurityArrayVO : securityArrayVOs) {
				// 复制对象转换成grpc流格式
				MapFormatSecurityArrayProtobuf securityArrayProtobuf = (MapFormatSecurityArrayProtobuf) TransformToGrpcPo
						.convertToPojo(MapFormatSecurityArrayProtobuf.class, mapFormatSecurityArrayVO);
				securityArrayProtobufList.add(securityArrayProtobuf);
			}
			for (MapFormatWarningArrayVO warningArrayVO : warningArrayVOs) {
				// 复制对象转换成grpc流格式
				MapFormatWarningArrayProtobuf warningArrayProtobuf = (MapFormatWarningArrayProtobuf) TransformToGrpcPo
						.convertToPojo(MapFormatWarningArrayProtobuf.class, warningArrayVO);
				warningArrayProtobufList.add(warningArrayProtobuf);
			}
			for (MapFormatCenterIndexVO mapFormatCenterIndexVO : centerIndexArrayVOs) {
				// 复制对象转换成grpc流格式
				MapFormatCenterIndexProtobuf centerIndexProtobuf = (MapFormatCenterIndexProtobuf) TransformToGrpcPo
						.convertToPojo(MapFormatCenterIndexProtobuf.class, mapFormatCenterIndexVO);
				centerIndexProtobufList.add(centerIndexProtobuf);
			}
			if (securityArrayProtobufList.size() > 0) {
				response.addAllSecurityArray(securityArrayProtobufList);
			}
			if (warningArrayProtobufList.size() > 0) {
				response.addAllWarningArray(warningArrayProtobufList);
			}
			if (centerIndexProtobufList.size() > 0) {
				response.addAllCenterIndexArray(centerIndexProtobufList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}

	/**
	 * 安防系统及设备在线状态转换grpc
	 * 
	 * @param object
	 * @return
	 */
	public SecuritySystemStateForParameters getSecuritySystemStateForParameters(Object object) {
		SecuritySystemStateForParameters.Builder response = SecuritySystemStateForParameters.newBuilder();
		SecuritySystemStateForParametersVO securitySystemStateForParametersVO = (SecuritySystemStateForParametersVO) object;
		List<SecuritySystemStateVO> securitySystemStateVOs = securitySystemStateForParametersVO.getList();
		if (null == securitySystemStateVOs) {
			securitySystemStateVOs = new ArrayList<>();
		}
		List<SecuritySystemStateProtobuf> systemStateProtobufList = new ArrayList<>();
		try {
			for (SecuritySystemStateVO securitySystemStateVO : securitySystemStateVOs) {
				// 复制对象转换成grpc流格式
				SecuritySystemStateProtobuf securitySystemStateProtobuf = (SecuritySystemStateProtobuf) TransformToGrpcPo
						.convertToPojo(SecuritySystemStateProtobuf.class, securitySystemStateVO);
				systemStateProtobufList.add(securitySystemStateProtobuf);
			}
			if (systemStateProtobufList.size() > 0) {
				response.addAllSystemArray(systemStateProtobufList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}

	/**
	 * 网络安全水平在线率密通率
	 * 
	 * @param object
	 * @return
	 */
	public ScreenSecretPassOnlineRateForParameters getScreenSecretPassOnlineRateForParameters(Object object) {
		ScreenSecretPassOnlineRateForParameters.Builder response = ScreenSecretPassOnlineRateForParameters.newBuilder();
		ScreenSecretPassOnlineRateForParametersVO securitySystemStateForParametersVO = (ScreenSecretPassOnlineRateForParametersVO) object;
		String allOnlineRate = securitySystemStateForParametersVO.getAllOnlineRate();
		String allSecretPass = securitySystemStateForParametersVO.getAllSecretPass();
		String urgentWarningsCount = securitySystemStateForParametersVO.getUrgentWarningsCount();
		String importantWarningsCount = securitySystemStateForParametersVO.getImportantWarningsCount();
		String secrityDays = securitySystemStateForParametersVO.getSecrityDays();
		List<ScreenSecretPassOnlineRateProtobufVO> list = securitySystemStateForParametersVO.getList();
		if (null == list) {
			list = new ArrayList<>();
		}
		List<ScreenSecretPassOnlineRateProtobuf> secretPassOnlineRateProtobufList = new ArrayList<>();
		try {
			for (ScreenSecretPassOnlineRateProtobufVO screenSecretPassOnlineRateProtobufVO : list) {
				String color = screenSecretPassOnlineRateProtobufVO.getColor();
				String name = screenSecretPassOnlineRateProtobufVO.getName();
				List<String> times = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
				List<String> values = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
				ScreenSecretPassOnlineRateProtobuf.Builder secretPassOnlineRateProtobuf = ScreenSecretPassOnlineRateProtobuf
						.newBuilder();
				secretPassOnlineRateProtobuf.setColor(color);
				secretPassOnlineRateProtobuf.setName(name);
				secretPassOnlineRateProtobuf.addAllTime(times);
				secretPassOnlineRateProtobuf.addAllValue(values);
				secretPassOnlineRateProtobufList.add(secretPassOnlineRateProtobuf.build());
			}
			response.setAllOnlineRate(allOnlineRate);
			response.setAllSecretPass(allSecretPass);
			response.setImportantWarningsCount(importantWarningsCount);
			response.setUrgentWarningsCount(urgentWarningsCount);
			response.setSecrityDays(secrityDays);
			response.setImportantWarningsName(securitySystemStateForParametersVO.getImportantWarningsName());
			response.setUrgentWarningsName(securitySystemStateForParametersVO.getUrgentWarningsName());
			response.setOnlineName(securitySystemStateForParametersVO.getOnlineName());
			response.setSecretPassName(securitySystemStateForParametersVO.getSecretPassName());
			if (secretPassOnlineRateProtobufList.size() > 0) {
				response.addAllSecretPassOnlineRateArray(secretPassOnlineRateProtobufList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}

	/**
	 * 当日安全风险top10转换grpc
	 * 
	 * @param object
	 * @return
	 */
	public ScreenDayRiskTOPTenForParameters getScreenDayRiskTOPTenForParameters(Object object) {
		ScreenDayRiskTOPTenForParameters.Builder response = ScreenDayRiskTOPTenForParameters.newBuilder();
		ScreenDayRiskTOPTenForParametersVO screenDayRiskTOPTenForParametersVO = (ScreenDayRiskTOPTenForParametersVO) object;
		List<ScreenDayRiskTOPTenProtobufVO> dayRiskTOPTenProtobufVO = screenDayRiskTOPTenForParametersVO
				.getDayRiskTOPTenArray();
		if (null == dayRiskTOPTenProtobufVO) {
			dayRiskTOPTenProtobufVO = new ArrayList<>();
		}
		List<ScreenDayRiskTOPTenProtobuf> dayRiskTOPTenProtobufList = new ArrayList<>();
		try {
			for (ScreenDayRiskTOPTenProtobufVO screenDayRiskTOPTenProtobufVO : dayRiskTOPTenProtobufVO) {
				// 复制对象转换成grpc流格式
				ScreenDayRiskTOPTenProtobuf screenDayRiskTOPTenProtobuf = (ScreenDayRiskTOPTenProtobuf) TransformToGrpcPo
						.convertToPojo(ScreenDayRiskTOPTenProtobuf.class, screenDayRiskTOPTenProtobufVO);
				dayRiskTOPTenProtobufList.add(screenDayRiskTOPTenProtobuf);
			}
			if (dayRiskTOPTenProtobufList.size() > 0) {
				response.addAllDayRiskTOPTenArray(dayRiskTOPTenProtobufList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}

	/**
	 * 发生威胁单位top10转换grpc
	 * 
	 * @param object
	 * @return
	 */
	public ScreenDayRiskTOPTenForParameters getScreenDayRiskUnitTOPTenForParameters(Object object) {
		ScreenDayRiskTOPTenForParameters.Builder response = ScreenDayRiskTOPTenForParameters.newBuilder();
		ScreenDayRiskTOPTenForParametersVO screenDayRiskTOPTenForParametersVO = (ScreenDayRiskTOPTenForParametersVO) object;
		List<ScreenDayRiskTOPTenProtobufVO> dayRiskTOPTenProtobufVO = screenDayRiskTOPTenForParametersVO
				.getDayRiskTOPTenArray();
		if (null == dayRiskTOPTenProtobufVO) {
			dayRiskTOPTenProtobufVO = new ArrayList<>();
		}
		List<ScreenDayRiskTOPTenProtobuf> dayRiskTOPTenProtobufList = new ArrayList<>();
		try {
			for (ScreenDayRiskTOPTenProtobufVO screenDayRiskTOPTenProtobufVO : dayRiskTOPTenProtobufVO) {
				// 复制对象转换成grpc流格式
				ScreenDayRiskTOPTenProtobuf screenDayRiskTOPTenProtobuf = (ScreenDayRiskTOPTenProtobuf) TransformToGrpcPo
						.convertToPojo(ScreenDayRiskTOPTenProtobuf.class, screenDayRiskTOPTenProtobufVO);
				dayRiskTOPTenProtobufList.add(screenDayRiskTOPTenProtobuf);
			}
			if (dayRiskTOPTenProtobufList.size() > 0) {
				response.addAllDayRiskTOPTenArray(dayRiskTOPTenProtobufList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

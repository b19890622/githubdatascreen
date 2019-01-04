package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.AlarmNameAndNumber;
import com.CrossCountry.Survey.modelvo.donggs.PropertyDataAndOftenData;
import com.CrossCountry.Survey.modelvo.donggs.PropertyInDeviceReplyData;
import com.CrossCountry.Survey.modelvo.donggs.SecureTopology;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AlarmNameAndNumberReply;
import com.datascreen.PropertyDataAndOftenReply;
import com.datascreen.PropertyInDeviceReply;
import com.datascreen.SecureTopologyReply;

public class SecureTopologyToGrpc {
	public SecureTopologyReply getSecureTopologyReplyToGrpc(Object object) {
		SecureTopologyReply.Builder response = SecureTopologyReply.newBuilder();
		SecureTopology secureTopology = (SecureTopology) object;

		List<AlarmNameAndNumber> listAlarmNameAndNumber = secureTopology.getAlarmNameAndNumber();
		List<PropertyDataAndOftenData> listPropertyDataAndOftenData = secureTopology.getPropertyDataAndOftenData();
		List<PropertyInDeviceReplyData> propertyInDeviceReplyDatas = secureTopology.getPropertyInDeviceReply();

		if (null == listAlarmNameAndNumber) {
			listAlarmNameAndNumber = new ArrayList<>();
		}
		if (null == listPropertyDataAndOftenData) {
			listPropertyDataAndOftenData = new ArrayList<>();
		}
		if (null == propertyInDeviceReplyDatas) {
			propertyInDeviceReplyDatas = new ArrayList<>();
		}

		List<AlarmNameAndNumberReply> alarmNameAndNumberList = new ArrayList<>();
		List<PropertyDataAndOftenReply> propertyDataAndOftenReplyList = new ArrayList<>();
		List<PropertyInDeviceReply> propertyInDeviceReplyList = new ArrayList<>();

		try {
			for (AlarmNameAndNumber alarmNameAndNumber : listAlarmNameAndNumber) {

				AlarmNameAndNumberReply nameAndNumber = (AlarmNameAndNumberReply) TransformToGrpcPo
						.convertToPojo(AlarmNameAndNumberReply.class, alarmNameAndNumber);
				alarmNameAndNumberList.add(nameAndNumber);
			}
			if (null != alarmNameAndNumberList && alarmNameAndNumberList.size() > 0) {
				response.addAllAlarmNameAndNumberArrayProtobuf(alarmNameAndNumberList);
			}
			for (PropertyDataAndOftenData propertyDataAndOften : listPropertyDataAndOftenData) {

				PropertyDataAndOftenReply changeRed = (PropertyDataAndOftenReply) TransformToGrpcPo
						.convertToPojo(PropertyDataAndOftenReply.class, propertyDataAndOften);
				propertyDataAndOftenReplyList.add(changeRed);
			}
			if (null != propertyDataAndOftenReplyList && propertyDataAndOftenReplyList.size() > 0) {
				response.addAllPropertyDataAndOftenArrayProtobuf(propertyDataAndOftenReplyList);
			}
			for (PropertyInDeviceReplyData propertyInDeviceReplyData : propertyInDeviceReplyDatas) {

				PropertyInDeviceReply propertyInDeviceReply = (PropertyInDeviceReply) TransformToGrpcPo
						.convertToPojo(PropertyInDeviceReply.class, propertyInDeviceReplyData);
				propertyInDeviceReplyList.add(propertyInDeviceReply);
			}
			if (null != propertyInDeviceReplyList && propertyInDeviceReplyList.size() > 0) {
				response.addAllPropertyInDeviceReply(propertyInDeviceReplyList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(SecureTopologyToGrpc.class, e.getMessage(), e);
		}

		return response.build();
	}
}

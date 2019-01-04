package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceAlarmEquipmentTopFiveService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceComplianceNameAndNumService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceComplianceStatisticsService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceProtectionNumberService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.AlarmEquipmentTopFiveToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.ComplianceStatisticsToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.ProtectionToGrpc;
import com.CrossCountry.Survey.intervalutils.SecurityMonitoringIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.AlarmEquipmentTopFiveReply;
import com.datascreen.ComplianceReply;
import com.datascreen.ComplianceStatisticsReply;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.DataScreenSurveillanceServiceGrpc;
import com.datascreen.DataScreenVerificationServiceGrpc;
import com.datascreen.ProtectionNumberReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class AlarmEquipmentTopFiveService extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private SecurityMonitoringIntervalUtils securityMonitoringIntervalUtils;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> bidAlarmEquipmentTopFive(StreamObserver<AlarmEquipmentTopFiveReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAlarmEquipmentTopFiveService.class,
				"getAlarmEquipmentTopFive");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(AlarmEquipmentTopFiveToGrpc.class,
				"alarmEquipmentTopFiveTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,AlarmEquipmentTopFiveReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,AlarmEquipmentTopFiveReply>(kafkaAbTools, inArgs, outArgs, securityMonitoringIntervalUtils.getALARMMONITOR_DRGJ()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

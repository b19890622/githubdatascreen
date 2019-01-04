package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceAlarmDistributionService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceComplianceNameAndNumService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceComplianceStatisticsService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceProtectionNumberService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.AlarmDistributionToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.ComplianceStatisticsToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.ProtectionToGrpc;
import com.CrossCountry.Survey.intervalutils.SecurityMonitoringIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.AlarmDistributionReply;
import com.datascreen.ComplianceReply;
import com.datascreen.ComplianceStatisticsReply;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.DataScreenSurveillanceServiceGrpc;
import com.datascreen.DataScreenVerificationServiceGrpc;
import com.datascreen.ProtectionNumberReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class AlarmDistributionService extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private SecurityMonitoringIntervalUtils securityMonitoringIntervalUtils;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> bidAlarmDistribution(StreamObserver<AlarmDistributionReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAlarmDistributionService.class,
				"getAlarmDistribution");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(AlarmDistributionToGrpc.class,
				"alarmDistributionReplyTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,AlarmDistributionReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,AlarmDistributionReply>(kafkaAbTools, inArgs, outArgs, securityMonitoringIntervalUtils.getALARMMONITOR_GJFB()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

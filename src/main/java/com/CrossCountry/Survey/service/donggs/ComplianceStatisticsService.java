package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceComplianceNameAndNumService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceComplianceStatisticsService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceProtectionNumberService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.ComplianceStatisticsToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.ProtectionToGrpc;
import com.CrossCountry.Survey.intervalutils.SafetyVerificationIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.ComplianceReply;
import com.datascreen.ComplianceStatisticsReply;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.DataScreenVerificationServiceGrpc;
import com.datascreen.ProtectionNumberReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class ComplianceStatisticsService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private SafetyVerificationIntervalUtils safetyVerificationIntervalUtils ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> bidComplianceStatistics(StreamObserver<ComplianceStatisticsReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceComplianceStatisticsService.class,
				"getComplianceStatistics");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ComplianceStatisticsToGrpc.class,
				"complianceStatisticsTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,ComplianceStatisticsReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,ComplianceStatisticsReply>(kafkaAbTools, inArgs, outArgs, safetyVerificationIntervalUtils.getSECURITYVERIFICATION_BHGXQ()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

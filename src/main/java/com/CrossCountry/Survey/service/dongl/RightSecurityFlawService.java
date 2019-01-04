package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.RightSecurityFlawCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.RightSecurityFlawCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.SafetyVerificationIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenVerificationServiceGrpc;
import com.datascreen.RightSecurityFlawReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;





@Component
public class RightSecurityFlawService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase{

	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	SafetyVerificationIntervalUtils SafetyVerificationInterval;
	@Override
	public StreamObserver<StateGridRequest> bidrectionalRightSecurityFlaw(
			StreamObserver<RightSecurityFlawReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(RightSecurityFlawCommonService.class,
				"getRightSecurityFlaw");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(RightSecurityFlawCommonServiceToGrpc.class,
				"getRightSecurityFlawReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs, SafetyVerificationInterval.getAREAFLAWRIGHT_SQLD()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}


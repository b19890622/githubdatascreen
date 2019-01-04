package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.SecurityAnalasisCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.SecurityAnalasisCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.SecurityAnalasisReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class SecurityAnalasisService
		extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase {

	@Autowired
	private KafkaAbTools kafkaAbTools;
	
	@Autowired
	BorderProtectIntervalUtils borderProtectInterval;
	
	@Override
	public StreamObserver<StateGridRequest> bidrectionalSecurityAnalasis(
			StreamObserver<SecurityAnalasisReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(SecurityAnalasisCommonService.class,
				"getSecurityAnalasis");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SecurityAnalasisCommonServiceToGrpc.class,
				"getSecurityAnalasisReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
				borderProtectInterval.getSECURITYEVENTANALYSIS_AQSJFX()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

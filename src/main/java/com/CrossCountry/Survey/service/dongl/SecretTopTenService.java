package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.SecretTopTenCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.SecretTopTenCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.SecretTopTenReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class SecretTopTenService
		extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase {

	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private BorderProtectIntervalUtils borderProtectIntervalUtils ;
	@Override
	public StreamObserver<StateGridRequest> bidrectionalSecretTopTen(
			StreamObserver<SecretTopTenReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(SecretTopTenCommonService.class, "getSecretTopTenEntity");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SecretTopTenCommonServiceToGrpc.class,
				"getSecretTopTenReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
				borderProtectIntervalUtils.getBORDERPROTECT_MTQK()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

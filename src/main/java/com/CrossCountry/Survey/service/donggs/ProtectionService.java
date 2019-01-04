package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceProtectionNumberService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.ProtectionToGrpc;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.ProtectionNumberReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class ProtectionService extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private BorderProtectIntervalUtils borderProtectIntervalUtils;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> bidGetProtectionNumber(StreamObserver<ProtectionNumberReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceProtectionNumberService.class,
				"getProtection");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ProtectionToGrpc.class,
				"protectionTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,ProtectionNumberReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,ProtectionNumberReply>(kafkaAbTools, inArgs, outArgs, borderProtectIntervalUtils.getBORDERPROTECT_AQFHTX()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

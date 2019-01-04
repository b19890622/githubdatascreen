package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.CheckCountCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.CheckCountCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.HostProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.CheckCountReply;
import com.datascreen.DataScreenHostProtectionServiceGrpc;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;





@Component
public class CheckCountService extends DataScreenHostProtectionServiceGrpc.DataScreenHostProtectionServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	HostProtectIntervalUtils hostProtectInterval;
	@Override
	public StreamObserver<StateGridRequest> bidCheckCount(
			StreamObserver<CheckCountReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CheckCountCommonService.class,
				"getCheckCount");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CheckCountCommonServiceToGrpc.class,
				"getCheckCountReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs, hostProtectInterval.getCHECKCOUNT_AQHCTJ()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}


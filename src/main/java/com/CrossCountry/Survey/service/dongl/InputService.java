package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.InputCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.InputCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.HostProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenHostProtectionServiceGrpc;
import com.datascreen.InputReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;





@Component
public class InputService extends DataScreenHostProtectionServiceGrpc.DataScreenHostProtectionServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;

	@Autowired
	HostProtectIntervalUtils hostProtectInterval;
	
	@Override
	public StreamObserver<StateGridRequest> bidInputSurvey(
			StreamObserver<InputReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(InputCommonService.class,
				"getInput");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(InputCommonServiceToGrpc.class,
				"getInputReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs, hostProtectInterval.getINPUTSURVEY_JRGK()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}


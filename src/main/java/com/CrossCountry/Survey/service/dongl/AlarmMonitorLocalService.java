package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.AlarmMonitorLocalCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.AlarmMonitorCommonServiceToGrpc;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenServiceGrpc;
import com.datascreen.RunStateReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class AlarmMonitorLocalService extends DataScreenServiceGrpc.DataScreenServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;

	@Override
	public StreamObserver<StateGridRequest> bidirectionalMainStation(
			StreamObserver<RunStateReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(AlarmMonitorLocalCommonService.class,
				"getAlarmMonitorLocal");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(AlarmMonitorCommonServiceToGrpc.class,
				"getRunStateReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}



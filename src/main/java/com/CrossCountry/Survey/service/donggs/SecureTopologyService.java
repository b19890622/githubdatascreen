package com.CrossCountry.Survey.service.donggs;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceMenaceMonitorService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceSecureTopologyService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.MenaceMonitorToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.SecureTopologyToGrpc;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.MenaceMonitorReply;
import com.datascreen.SecureTopologyReply;
import com.datascreen.StateGridRequest;
import com.datascreen.DataScreenServiceGrpc.DataScreenServiceImplBase;

import io.grpc.stub.StreamObserver;

@Component
public class SecureTopologyService extends DataScreenServiceImplBase {

	@Autowired
	private KafkaAbTools kafkaAbTools;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> getSecureTopology(StreamObserver<SecureTopologyReply> responseObserver) {

		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceSecureTopologyService.class,
				"getSecureTopology");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SecureTopologyToGrpc.class,
				"getSecureTopologyReplyToGrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest, SecureTopologyReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest, SecureTopologyReply>(
				kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {

		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);

	}
}
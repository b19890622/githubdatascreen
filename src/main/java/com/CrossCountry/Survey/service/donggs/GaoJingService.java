package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceGuanKongService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.GetNumberToGrpc;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenServiceGrpc.DataScreenServiceImplBase;
import com.datascreen.NumberReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class GaoJingService extends  DataScreenServiceImplBase {

	@Autowired
	private KafkaAbTools kafkaAbTools;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> getNumber(StreamObserver<NumberReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceGuanKongService.class,
				"getNumber");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(GetNumberToGrpc.class,
				"getNumberTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,NumberReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,NumberReply>(kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceGuanKongService;
import com.CrossCountry.Survey.commonservice.donggs.CommonServiceLagerService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.GetMuToGrpc;
import com.CrossCountry.Survey.grpcmodel.donggs.GetNumberToGrpc;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.LargenArrayProtobuf;
import com.datascreen.NumberReply;
import com.datascreen.StateGridRequest;
import com.datascreen.DataScreenServiceGrpc.DataScreenServiceImplBase;

import io.grpc.stub.StreamObserver;
@Component
public class RemoteprovinceService extends DataScreenServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> getLargenTopology(StreamObserver<LargenArrayProtobuf> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceLagerService.class,
				"getMu");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(GetMuToGrpc.class,
				"getMuTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,LargenArrayProtobuf> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,LargenArrayProtobuf>(kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

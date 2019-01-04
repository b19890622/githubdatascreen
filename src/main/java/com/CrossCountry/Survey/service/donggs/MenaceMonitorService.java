package com.CrossCountry.Survey.service.donggs;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceMenaceMonitorService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.MenaceMonitorToGrpc;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.AllDateReply;
import com.datascreen.DataScreenServiceGrpc.DataScreenServiceImplBase;
import com.datascreen.MenaceMonitorReply;
import com.datascreen.PropertyDataAndOftenReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class MenaceMonitorService extends DataScreenServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> getMenaceMonitorProto(StreamObserver<AllDateReply> responseObserver){
		String ran = UUID.randomUUID().toString();
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceMenaceMonitorService.class,
				"getAllData");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(MenaceMonitorToGrpc.class,
				"getMenaceMonitorReplyToGrpc");
		// 轮询
		PollingOneToOneGrpc <StateGridRequest , AllDateReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest , AllDateReply>(kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {

			@Override
			public Object getRequestPojo(StateGridRequest v) {
				
				return ran;
			}
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

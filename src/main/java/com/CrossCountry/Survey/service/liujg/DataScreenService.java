package com.CrossCountry.Survey.service.liujg;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.liujg.CommonServiceDataScreenService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.liujg.CommonConvertToGrpcTools;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.datascreen.DataScreenServiceGrpc;
import com.datascreen.HelloReply;
import com.datascreen.HelloRequest;
import com.datascreen.OnDutyPersion;
import com.datascreen.RiskWarningProto;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class DataScreenService extends DataScreenServiceGrpc.DataScreenServiceImplBase{

	@Autowired
	CommonServiceDataScreenService commonServiceDataScreenService;
	
	@Autowired
	KafkaAbTools kafkatools;
	
	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
//		UserInfoVO vo = commonServiceDataScreenService.getUsername();
		HelloReply rep = HelloReply.newBuilder().setMessage("ceshi").build();
		responseObserver.onNext(rep);
		responseObserver.onCompleted();
//		System.out.print(vo.getPhone());
	}

	@Override
	public StreamObserver<StateGridRequest> getOnDutyPerson(StreamObserver<OnDutyPersion> responseObserver) {
		String ran = UUID.randomUUID().toString().replace("-", "");
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceDataScreenService.class, "getOnDutyPersionMsg");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToDutyPersion");
		PollingOneToOneGrpc<StateGridRequest, OnDutyPersion> grpcTools = new PollingOneToOneGrpc<StateGridRequest, OnDutyPersion>(kafkatools, inArgs, outArgs, 60 * 2 * 1000){
			@Override
			public Object getRequestPojo(StateGridRequest v) {
				CommonArgs commonArgs = new CommonArgs();
            	commonArgs.setKey(ran);
            	commonArgs.setName(v.getName());
                return commonArgs;
			}
		};
		return grpcTools.getStreamObserver(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getRiskWarning(StreamObserver<RiskWarningProto> responseObserver) {
		// TODO Auto-generated method stub
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceDataScreenService.class, "getRiskWarnMsg");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToRisk");
		PollingOneToOneGrpc<StateGridRequest, RiskWarningProto> grpcTools = new PollingOneToOneGrpc<StateGridRequest, RiskWarningProto>(kafkatools, inArgs, outArgs, 1000*60*5){
		};
		return grpcTools.getStreamObserver(responseObserver);
	}

	
	
}

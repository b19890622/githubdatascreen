package com.CrossCountry.Survey.service.liujg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.liujg.CommonVerificationDataService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.liujg.CommonConvertToGrpcTools;
import com.CrossCountry.Survey.intervalutils.SafetyVerificationIntervalUtils;
import com.datascreen.DataScreenVerificationServiceGrpc;
import com.datascreen.StateGridRequest;
import com.datascreen.SurveillanceRegionMsg;
import com.datascreen.ZxSurveillanceMsg;

import io.grpc.stub.StreamObserver;

@Component
public class DataScreenVerificationService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase{

	@Autowired
	KafkaAbTools kafkatools;
	
	@Autowired
	SafetyVerificationIntervalUtils safetyVerificationInterval;
	
	@Override
	public StreamObserver<StateGridRequest> zxSurveillanceStat(StreamObserver<ZxSurveillanceMsg> responseObserver) {
		// TODO Auto-generated method stub
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonVerificationDataService.class, "getZxSurveillanceMsg");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToZxSurveillance");
		PollingOneToOneGrpc<StateGridRequest, ZxSurveillanceMsg> grpcTools = new PollingOneToOneGrpc<StateGridRequest, ZxSurveillanceMsg>(kafkatools, inArgs, outArgs, safetyVerificationInterval.getSAFETYVERIFICATION_ZXHGQK()){
		};
		return grpcTools.getStreamObserver(responseObserver);	
	}

	@Override
	public StreamObserver<StateGridRequest> surveillanceRegionStat(
			StreamObserver<SurveillanceRegionMsg> responseObserver) {
		// TODO Auto-generated method stub
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonVerificationDataService.class, "getSurveillanceRegionMsg");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToSurveillanceRegion");
		PollingOneToOneGrpc<StateGridRequest, SurveillanceRegionMsg> grpcTools = new PollingOneToOneGrpc<StateGridRequest, SurveillanceRegionMsg>(kafkatools, inArgs, outArgs, safetyVerificationInterval.getSAFETYVERIFICATION_HGQKTJ()){
		};
		return grpcTools.getStreamObserver(responseObserver);	
	}
	
	
	
}

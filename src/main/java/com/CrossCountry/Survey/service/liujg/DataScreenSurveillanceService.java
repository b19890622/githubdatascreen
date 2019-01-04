package com.CrossCountry.Survey.service.liujg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.liujg.CommonSurveillanceDataService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.liujg.CommonConvertToGrpcTools;
import com.CrossCountry.Survey.intervalutils.SecurityMonitoringIntervalUtils;
import com.datascreen.AllNetWorkMsg;
import com.datascreen.DataScreenSurveillanceServiceGrpc;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class DataScreenSurveillanceService extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase{

	@Autowired
	CommonSurveillanceDataService commonSurveillanceDataService;
	
	@Autowired
	KafkaAbTools kafkatools;
	
	@Autowired
	SecurityMonitoringIntervalUtils securityMonitoringInterval;
	
	//全网告警情况
	@Override
	public StreamObserver<StateGridRequest> allNetWrokCompliance(StreamObserver<AllNetWorkMsg> responseObserver) {
		// TODO Auto-generated method stub
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonSurveillanceDataService.class, "getAllNetWorkMsg");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToSurveillance");
		PollingOneToOneGrpc<StateGridRequest, AllNetWorkMsg> grpcTools = new PollingOneToOneGrpc<StateGridRequest, AllNetWorkMsg>(kafkatools, inArgs, outArgs, securityMonitoringInterval.getALARMMONITOR_GJTJ()){
		};
		return grpcTools.getStreamObserver(responseObserver);	
	}
	
	
}

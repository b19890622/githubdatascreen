package com.CrossCountry.Survey.service.liujg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.liujg.CommonBorderProService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.liujg.CommonConvertToGrpcTools;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.StateGridRequest;
import com.datascreen.TendencyOnWeekMsg;
import com.datascreen.ZxDeviceWarnMsg;

import io.grpc.stub.StreamObserver;

@Component
public class DataScreenBorderProService extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase{
	
	@Autowired
	KafkaAbTools kafkatools;
	
	@Autowired
	BorderProtectIntervalUtils borderProtectInterval;
	
	@Override
	public StreamObserver<StateGridRequest> getZxDeviceDisplay(StreamObserver<ZxDeviceWarnMsg> responseObserver) {
		// TODO Auto-generated method stub
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonBorderProService.class, "getBroderProVOMsg");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToZxDevice");
		PollingOneToOneGrpc<StateGridRequest, ZxDeviceWarnMsg> grpcTools = new PollingOneToOneGrpc<StateGridRequest, ZxDeviceWarnMsg>(kafkatools, inArgs, outArgs, borderProtectInterval.getBORDERPROTECT_ZXGLXQ()){
		};
		return grpcTools.getStreamObserver(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getTendencyOnWeekDisplay(
			StreamObserver<TendencyOnWeekMsg> responseObserver) {
		// TODO Auto-generated method stub
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonBorderProService.class, "getTendencyOnWeekVOList");
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonConvertToGrpcTools.class, "getConvertToTendencyWeek");
		PollingOneToOneGrpc<StateGridRequest, TendencyOnWeekMsg> grpcTools = new PollingOneToOneGrpc<StateGridRequest, TendencyOnWeekMsg>(kafkatools, inArgs, outArgs, borderProtectInterval.getBORDERPROTECT_DXSJFX()){
		};
		return grpcTools.getStreamObserver(responseObserver);
	}

}

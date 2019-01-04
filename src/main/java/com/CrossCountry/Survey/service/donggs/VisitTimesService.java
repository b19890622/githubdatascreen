package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.VisitTimesCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.VisitTimesCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.HostProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenHostProtectionServiceGrpc;
import com.datascreen.StateGridRequest;
import com.datascreen.VisitTimesReply;

import io.grpc.stub.StreamObserver;
@Component
public class VisitTimesService extends DataScreenHostProtectionServiceGrpc.DataScreenHostProtectionServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private HostProtectIntervalUtils hostProtectIntervalUtils;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> bidVisitTimes(StreamObserver<VisitTimesReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(VisitTimesCommonService.class,
				"getVisitTimes");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(VisitTimesCommonServiceToGrpc.class,
				"getVisitTimesPo");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,VisitTimesReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,VisitTimesReply>(kafkaAbTools, inArgs, outArgs, hostProtectIntervalUtils.getHOSTPROTECT_FWQFW()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

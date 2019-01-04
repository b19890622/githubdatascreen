package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.SubStationAlarmDistributionCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.SubStationAlarmDistributionCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.SecurityMonitoringIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenSurveillanceServiceGrpc;
import com.datascreen.StateGridRequest;
import com.datascreen.SubStationAlarmDistributionReply;

import io.grpc.stub.StreamObserver;





@Component
public class SubStationAlarmDistributionService  extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	SecurityMonitoringIntervalUtils securityMonitoringInterval;
	@Override
	public StreamObserver<StateGridRequest> bidrectionalSubStationAlarmDistribution(
			StreamObserver<SubStationAlarmDistributionReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(SubStationAlarmDistributionCommonService.class,
				"getSubStationAlarmDistribution");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SubStationAlarmDistributionCommonServiceToGrpc.class,
				"getSubStationAlarmDistributionReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs, securityMonitoringInterval.getSUBSTATIONALARMDISTRIBUTION_CZGJQK()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}


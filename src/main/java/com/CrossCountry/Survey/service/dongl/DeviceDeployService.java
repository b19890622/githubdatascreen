package com.CrossCountry.Survey.service.dongl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.DeviceDeployCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.DeviceDeployCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.NetWorkMonitorIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenNetWorkCheckServiceGrpc;
import com.datascreen.DeviceDeployReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class DeviceDeployService extends DataScreenNetWorkCheckServiceGrpc.DataScreenNetWorkCheckServiceImplBase{

	@Autowired
	private KafkaAbTools kafkaAbTools;

	@Autowired
	NetWorkMonitorIntervalUtils netWorkMonitorInterval;
	
	@Override
	public StreamObserver<StateGridRequest> bidrectionalDeviceDeploy(
			StreamObserver<DeviceDeployReply> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(DeviceDeployCommonService.class,
				"getDeviceDeploy");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(DeviceDeployCommonServiceToGrpc.class,
				"getDeviceDeployReply");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs, netWorkMonitorInterval.getSUBSTATIONDEVICEDEPLOY_CZZZBS()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}


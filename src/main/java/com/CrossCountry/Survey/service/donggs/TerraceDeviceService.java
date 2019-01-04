package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceTerraceDeviceNumberService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.TerraceDeviceNumberToGrpc;
import com.CrossCountry.Survey.intervalutils.NetWorkMonitorIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenNetWorkCheckServiceGrpc;
import com.datascreen.StateGridRequest;
import com.datascreen.TerraceDeviceNumberReply;

import io.grpc.stub.StreamObserver;
@Component
public class TerraceDeviceService extends DataScreenNetWorkCheckServiceGrpc.DataScreenNetWorkCheckServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private NetWorkMonitorIntervalUtils netWorkMonitorIntervalUtils;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> getTerraceDeviceNumber(StreamObserver<TerraceDeviceNumberReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceTerraceDeviceNumberService.class,
				"getTerraceDevice");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(TerraceDeviceNumberToGrpc.class,
				"getTerraceDeviceNumberTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,TerraceDeviceNumberReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,TerraceDeviceNumberReply>(kafkaAbTools, inArgs, outArgs, netWorkMonitorIntervalUtils.getNETWORKMONITOR_PTBSQK()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

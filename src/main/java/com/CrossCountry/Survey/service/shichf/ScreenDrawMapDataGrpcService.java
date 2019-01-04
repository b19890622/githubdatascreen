package com.CrossCountry.Survey.service.shichf;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.shichf.ScreenDayRiskTOPTenService;
import com.CrossCountry.Survey.commonservice.shichf.ScreenDrawMapDataService;
import com.CrossCountry.Survey.commonservice.shichf.ScreenSecretPassOnlineRateDataService;
import com.CrossCountry.Survey.commonservice.shichf.ScreenSecuritySystemDeviceDataService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.shichf.ScreenDrawMapDataToGrpc;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenServiceGrpc.DataScreenServiceImplBase;
import com.datascreen.DrawingMapsForParameters;
import com.datascreen.ScreenDayRiskTOPTenForParameters;
import com.datascreen.ScreenSecretPassOnlineRateForParameters;
import com.datascreen.SecuritySystemStateForParameters;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class ScreenDrawMapDataGrpcService extends DataScreenServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;

	@Override
	public StreamObserver<StateGridRequest> bidirectionalDrawingMapsFormat(
			StreamObserver<DrawingMapsForParameters> responseObserver) {
		String ran = UUID.randomUUID().toString().replace("-", "");
		String warnran = UUID.randomUUID().toString().replace("-", "");
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(ScreenDrawMapDataService.class,
				"getDrawingMapsForParametersVO");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ScreenDrawMapDataToGrpc.class,
				"getDrawingMapsForParameters");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest, DrawingMapsForParameters> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest, DrawingMapsForParameters>(
				kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
			@Override
			public Object getRequestPojo(StateGridRequest v) {
				CommonArgs commonArgs = new CommonArgs();
				commonArgs.setKey(ran+","+warnran);
				commonArgs.setName(v.getName());
				return commonArgs;
			}
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> screenSecuritySystemState(
			StreamObserver<SecuritySystemStateForParameters> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(ScreenSecuritySystemDeviceDataService.class,
				"getSecuritySystemStateForParametersVO");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ScreenDrawMapDataToGrpc.class,
				"getSecuritySystemStateForParameters");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
				StaticConfig.intervalDeviceCountTimes) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> screenSecretPassOnlineRate(
			StreamObserver<ScreenSecretPassOnlineRateForParameters> responseObserver) {
		String ranSecret = UUID.randomUUID().toString().replace("-", "");
		String ranOnline = UUID.randomUUID().toString().replace("-", "");
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(ScreenSecretPassOnlineRateDataService.class,
				"getScreenSecretPassOnlineRateForParametersVO");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ScreenDrawMapDataToGrpc.class,
				"getScreenSecretPassOnlineRateForParameters");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest, ScreenSecretPassOnlineRateForParameters> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest, ScreenSecretPassOnlineRateForParameters>(
				kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
			@Override
			public Object getRequestPojo(StateGridRequest v) {
				CommonArgs commonArgs = new CommonArgs();
				commonArgs.setKey(ranSecret + "," + ranOnline);
				commonArgs.setName(v.getName());
				return commonArgs;
			}
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> screenDayRiskTOPTen(
			StreamObserver<ScreenDayRiskTOPTenForParameters> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(ScreenDayRiskTOPTenService.class,
				"getScreenDayRiskTOPTenForParametersVO");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ScreenDrawMapDataToGrpc.class,
				"getScreenDayRiskTOPTenForParameters");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
				StaticConfig.intervalTimes) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> screenDayRiskUnitTOPTen(
			StreamObserver<ScreenDayRiskTOPTenForParameters> responseObserver) {
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(ScreenDayRiskTOPTenService.class,
				"getScreenDayRiskUnitTOPTenForParametersVO");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ScreenDrawMapDataToGrpc.class,
				"getScreenDayRiskUnitTOPTenForParameters");
		// 轮询
		PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
				StaticConfig.intervalTimes) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

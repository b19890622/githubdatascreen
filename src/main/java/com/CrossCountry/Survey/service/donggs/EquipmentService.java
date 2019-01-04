package com.CrossCountry.Survey.service.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceEquipmentNumberService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.donggs.EquipmentToGrpc;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.EquipmentNumberReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
@Component
public class EquipmentService extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase{
	@Autowired
	private KafkaAbTools kafkaAbTools;
	@Autowired
	private BorderProtectIntervalUtils borderProtectIntervalUtils ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StreamObserver<StateGridRequest> bidGetEquipmentNumber(StreamObserver<EquipmentNumberReply> responseObserver){
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceEquipmentNumberService.class,
				"getEquipment");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(EquipmentToGrpc.class,
				"getEquipmentTogrpc");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest,EquipmentNumberReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,EquipmentNumberReply>(kafkaAbTools, inArgs, outArgs, borderProtectIntervalUtils.getBORDERPROTECT_QWDLZY()) {
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

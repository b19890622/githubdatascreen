package com.CrossCountry.Survey.service.dongl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.dongl.SafetyComplianceCommonService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.dongl.SafetyComplianceCommonServiceToGrpc;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenServiceGrpc;
import com.datascreen.SafetyComplianceReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class SafetyComplianceService extends DataScreenServiceGrpc.DataScreenServiceImplBase {
	@Autowired
	private KafkaAbTools kafkaAbTools;

	@Override
	public StreamObserver<StateGridRequest> bidirectionalSafetyCompliance(
			StreamObserver<SafetyComplianceReply> responseObserver) {
		String ran = UUID.randomUUID().toString().replace("-", "");
		// 处理业务逻辑数据
		CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(SafetyComplianceCommonService.class,
				"getSafetyCompliance");
		// 将业务逻辑数据解析成grpc流
		CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SafetyComplianceCommonServiceToGrpc.class,
				"getSafetyComplianceReply");
		// 轮询
		PollingOneToOneGrpc<StateGridRequest, SafetyComplianceReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest, SafetyComplianceReply>(
				kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
			@Override
			public Object getRequestPojo(StateGridRequest v) {
				CommonArgs commonArgs = new CommonArgs();
				commonArgs.setKey(ran);
				commonArgs.setName(v.getName());
				return commonArgs;
			}
		};
		return pollingOneToOneGrpc.getStreamObserver(responseObserver);
	}
}

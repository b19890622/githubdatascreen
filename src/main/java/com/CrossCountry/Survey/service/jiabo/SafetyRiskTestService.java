package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.SafetyRiskTestCommonService;
import com.CrossCountry.Survey.grpcmodel.jiabo.SafetyRiskTestCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.SecurityMonitoringIntervalUtils;
import com.datascreen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.stub.StreamObserver;

@Component
public class SafetyRiskTestService extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    SecurityMonitoringIntervalUtils securityMonitoringInterval;

    @Override
    public StreamObserver<StateGridRequest> bidrectionalSafetyRiskTest(
            StreamObserver<SafetyRiskManageAndTestReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(SafetyRiskTestCommonService.class,
                "getSafetyRiskTest");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SafetyRiskTestCommonServiceToGrpc.class,
                "getSafetyRiskTest");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                securityMonitoringInterval.getALARMMONITOR_AQFXJCT()) {
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

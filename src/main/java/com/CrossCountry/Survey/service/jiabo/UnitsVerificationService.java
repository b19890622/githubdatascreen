package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.UnitsVerificationCommonService;
import com.CrossCountry.Survey.grpcmodel.jiabo.UnitsVerificationCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.SafetyVerificationIntervalUtils;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.datascreen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.stub.StreamObserver;

import java.util.UUID;

@Component
public class UnitsVerificationService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    SafetyVerificationIntervalUtils safetyVerificationInterval;

    @Override
    public StreamObserver<StateGridRequest> bidrectionalUnitsVerification(
            StreamObserver<UnitsVerificationReply> responseObserver) {
        String randomKey = UUID.randomUUID().toString();
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(UnitsVerificationCommonService.class,
                "getUnitsVerification");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(UnitsVerificationCommonServiceToGrpc.class,
                "getUnitsVerification");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                safetyVerificationInterval.getSECURITYVERIFICATION_DWHCT()) {
            public Object getRequestPojo(StateGridRequest v) {
                CommonArgs commonArgs = new CommonArgs();
                commonArgs.setKey(randomKey);
                commonArgs.setName(v.getName());
                return commonArgs;
            }
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

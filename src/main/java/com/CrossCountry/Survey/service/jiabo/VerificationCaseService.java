package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.VerificationCaseCommonService;
import com.CrossCountry.Survey.grpcmodel.jiabo.VerificationCaseCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.datascreen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.stub.StreamObserver;

@Component
public class VerificationCaseService extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    BorderProtectIntervalUtils borderProtectInterval;

    @Override
    public StreamObserver<StateGridRequest> bidirectionalVerificationCase(
            StreamObserver<VerificationCaseReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(VerificationCaseCommonService.class,
                "getVerificationCase");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(VerificationCaseCommonServiceToGrpc.class,
                "getVerificationCase");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                borderProtectInterval.getBORDERPROTECT_YZHCQK()) {
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

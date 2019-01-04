package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.NetSecurityVerificaCommonService;
import com.CrossCountry.Survey.grpcmodel.jiabo.NetSecurityVerificaCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.SafetyVerificationIntervalUtils;
import com.datascreen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.stub.StreamObserver;

@Component
public class NetSecurityVerificaService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    SafetyVerificationIntervalUtils safetyVerificationInterval;

    @Override
    public StreamObserver<StateGridRequest> bidrectionalNetSecurityVerifica(
            StreamObserver<NetSecurityVerificaReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(NetSecurityVerificaCommonService.class,
                "getNetSecurityVerifica");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(NetSecurityVerificaCommonServiceToGrpc.class,
                "getNetSecurityVerifica");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                safetyVerificationInterval.getSECURITYVERIFICATION_DTW()) {
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

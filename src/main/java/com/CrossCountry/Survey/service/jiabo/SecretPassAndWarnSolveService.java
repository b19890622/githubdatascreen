package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.SecretPassAndWarnSolveCommonService;
import com.CrossCountry.Survey.grpcmodel.jiabo.SecretPassAndWarnSolveCommonServiceToGrpc;
import com.CrossCountry.Survey.intervalutils.NetWorkMonitorIntervalUtils;
import com.datascreen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.stub.StreamObserver;

@Component
public class SecretPassAndWarnSolveService extends DataScreenNetWorkCheckServiceGrpc.DataScreenNetWorkCheckServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    NetWorkMonitorIntervalUtils netWorkMonitorInterval;

    @Override
    public StreamObserver<StateGridRequest> bidirectionalSecretPassAndWarnSolve(
            StreamObserver<SecretPassAndWarnSolveReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(SecretPassAndWarnSolveCommonService.class,
                "getSecretPassAndWarnSolve");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(SecretPassAndWarnSolveCommonServiceToGrpc.class,
                "getSecretPassAndWarnSolve");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                netWorkMonitorInterval.getNETWORKMONITOR_AQYXZS()) {
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

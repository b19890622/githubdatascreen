package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.TunnelAndStrategyCommonService;
import com.CrossCountry.Survey.grpcmodel.jiabo.TunnelAndStrategyCommonServiceToGrpc;
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
public class TunnelAndStrategyService extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    BorderProtectIntervalUtils borderProtectInterval;

    @Override
    public StreamObserver<StateGridRequest> bidirectionalTunnelAndStrategy(
            StreamObserver<TunnelAndStrategyReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(TunnelAndStrategyCommonService.class,
                "getTunnelAndStrategy");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(TunnelAndStrategyCommonServiceToGrpc.class,
                "getTunnelAndStrategy");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                borderProtectInterval.getBORDERPROTECT_WSGSZXQK()) {
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

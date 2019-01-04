package com.CrossCountry.Survey.service.wangkun;

import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceAccessAndHighRiskService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceHostProtectToGrpc;
import com.CrossCountry.Survey.intervalutils.HostProtectIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther wangkun
 * @date 2018/12/30 12:00
 */
@Component
public class AccessAndHighRiskService extends DataScreenHostProtectionServiceGrpc.DataScreenHostProtectionServiceImplBase{
    @Autowired
    KafkaAbTools kafkaAbTools;
    @Autowired
    HostProtectIntervalUtils hostProtectIntervalUtils;

    @Override
    public StreamObserver<StateGridRequest> bidAccessMonitoring(StreamObserver<AccessMonitoringReplay> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAccessAndHighRiskService.class,
                "getAccessMonitoringPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceHostProtectToGrpc.class,
                "getAccessMonitoringResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,AccessMonitoringReplay> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,AccessMonitoringReplay>(kafkaAbTools, inArgs, outArgs, hostProtectIntervalUtils.getPERIPHERALACCESSINFO_WSJRJS()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidHighRiskSurveillance(StreamObserver<HighRiskSurveillanceReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAccessAndHighRiskService.class,
                "getHighRiskSurveillancePojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceHostProtectToGrpc.class,
                "getHighRiskSurveillanceResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,HighRiskSurveillanceReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,HighRiskSurveillanceReply>(kafkaAbTools, inArgs, outArgs, hostProtectIntervalUtils.getDANGER_SERVICE_MONITOR_GWFWJS()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidSystemAndAgentCount(StreamObserver<SystemAndAgentReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAccessAndHighRiskService.class,
                "getSystemAndAgentCountPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceHostProtectToGrpc.class,
                "getSystemAndAgentCountResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,SystemAndAgentReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,SystemAndAgentReply>(kafkaAbTools, inArgs, outArgs, hostProtectIntervalUtils.getSYSAGENT_VERSIONNUM_ZJXTDLTJ()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

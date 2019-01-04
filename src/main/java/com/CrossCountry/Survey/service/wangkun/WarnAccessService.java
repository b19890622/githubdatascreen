package com.CrossCountry.Survey.service.wangkun;

import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceAboutWarnService;
import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceBugSumService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceBroderProtectToGrpc;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceWarnRefelctToGrpc;
import com.CrossCountry.Survey.intervalutils.SecurityMonitoringIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @auther wangkun
 * @date 2018/12/29 12:15
 */
@Component
public class WarnAccessService extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase {
    @Autowired
    KafkaAbTools kafkaAbTools;
    @Autowired
    SecurityMonitoringIntervalUtils intervalUtils;
    @Override
    public StreamObserver<StateGridRequest> bidWarnFigure(StreamObserver<WarnFigureReply> responseObserver) {

            // 处理业务逻辑数据
            CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAboutWarnService.class,
                    "getWarnFigurePojo");
            // 将业务逻辑数据解析成grpc流
            CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceWarnRefelctToGrpc.class,
                    "getWarnFigureResponse");
            // 轮询
            PollingOneToOneGrpc<StateGridRequest,WarnFigureReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,WarnFigureReply>(kafkaAbTools, inArgs, outArgs, intervalUtils.getRISKMON_VIEW_GJQX()) {

            };
            return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidSecurityRisk(StreamObserver<SecurityRiskReply> responseObserver) {
        String randomKey = UUID.randomUUID().toString();
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAboutWarnService.class,
                "getSecurityRiskPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceWarnRefelctToGrpc.class,
                "getSecurityRiskResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,SecurityRiskReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,SecurityRiskReply>(kafkaAbTools, inArgs, outArgs, intervalUtils.getALARMMONITOR_AQFXJCT()) {

            @Override
            public Object getRequestPojo(StateGridRequest v) {
                return randomKey;
            }
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidSecurityRiskDeal(StreamObserver<SecurityRiskReply> responseObserver) {
        String randomKey = UUID.randomUUID().toString();
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceAboutWarnService.class,
                "getSecurityRiskDealPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceWarnRefelctToGrpc.class,
                "getSecurityRiskDealResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,SecurityRiskReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,SecurityRiskReply>(kafkaAbTools, inArgs, outArgs, intervalUtils.getALARMMONITOR_AQFXJCT()) {

            @Override
            public Object getRequestPojo(StateGridRequest v) {
                return randomKey;
            }
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

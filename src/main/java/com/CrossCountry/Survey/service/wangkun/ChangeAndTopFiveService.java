package com.CrossCountry.Survey.service.wangkun;

import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceBorderProtectService;
import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceDeviceWarnService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceBroderProtectToGrpc;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceDeviceWarnNumToGrpc;
import com.CrossCountry.Survey.intervalutils.BorderProtectIntervalUtils;
import com.CrossCountry.Survey.service.CommonBorderProtectionService;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther wangkun
 * @date 2018/12/28 13:41
 */
@Component
public class ChangeAndTopFiveService extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase {
    @Autowired
    KafkaAbTools kafkaAbTools;
    @Autowired
    BorderProtectIntervalUtils borderProtectIntervalUtils;

    @Override
    public StreamObserver<StateGridRequest> bidGetChangeNum(StreamObserver<GetChangeNumReplay> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceBorderProtectService.class,
                "getChangeNumPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceBroderProtectToGrpc.class,
                "getChangeNumResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,GetChangeNumReplay> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,GetChangeNumReplay>(kafkaAbTools, inArgs, outArgs, borderProtectIntervalUtils.getTOTALCHANGETIMES_ZBGCS()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidGetTopFiveWarn(StreamObserver<GetTopFiveWarnReplay> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceBorderProtectService.class,
                "getTopFiveWarnPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceBroderProtectToGrpc.class,
                "getTopFiveWarnResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,GetTopFiveWarnReplay> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,GetTopFiveWarnReplay>(kafkaAbTools, inArgs, outArgs, borderProtectIntervalUtils.getSAFETYEVENTWEEK_GJSJTW()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

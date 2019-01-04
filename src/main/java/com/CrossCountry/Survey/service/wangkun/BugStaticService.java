package com.CrossCountry.Survey.service.wangkun;

import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceBorderProtectService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceBroderProtectToGrpc;
import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceBugSumService;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceBugSumToGrpc;
import com.CrossCountry.Survey.intervalutils.SafetyVerificationIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther wangkun
 * @date 2018/12/29 11:36
 */
@Component
public class BugStaticService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    SafetyVerificationIntervalUtils intervalUtils;
 //漏洞统计环形
    @Override
    public StreamObserver<StateGridRequest> bidVulnerabilityCircle(StreamObserver<VulnerabilityCircleReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceBugSumService.class,
                "getVulnerabilityCirclePojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceBugSumToGrpc.class,
                "getVulnerabilityCircleResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,VulnerabilityCircleReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,VulnerabilityCircleReply>(kafkaAbTools, inArgs, outArgs, intervalUtils.getHOLESCANTOTALCOUNT_LDTJHX()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
//漏洞统计列表
    @Override
    public StreamObserver<StateGridRequest> bidVulnerabilityList(StreamObserver<VulnerabilityListReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceBugSumService.class,
                "getVulnerabilityListPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceBugSumToGrpc.class,
                "getVulnerabilityListResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,VulnerabilityListReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,VulnerabilityListReply>(kafkaAbTools, inArgs, outArgs, intervalUtils.getHOLESCANTOTALNAME_LDTJLB()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

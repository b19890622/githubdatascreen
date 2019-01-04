package com.CrossCountry.Survey.service.wangkun;

import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceSecurityEventService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceSecurityEventToGrpc;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.DataScreenServiceGrpc;
import com.datascreen.SecurityEventReply;
import com.datascreen.StateGridRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityEventService  extends DataScreenServiceGrpc.DataScreenServiceImplBase{
    @Autowired
    private KafkaAbTools kafkaAbTools;

    @Override
    public StreamObserver<StateGridRequest> bidrectionalSecurityEvent(StreamObserver<SecurityEventReply> responseObserver) {
        String randomKey = UUID.randomUUID().toString();
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceSecurityEventService.class,
                "getSecurityEvent");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceSecurityEventToGrpc.class,
                "getResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,SecurityEventReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,SecurityEventReply>(kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
            @Override
            public Object getRequestPojo(StateGridRequest v) {
                return randomKey;
            }
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidrectionalSubSecurityEvent(StreamObserver<SecurityEventReply> responseObserver) {
        String randomKey = UUID.randomUUID().toString();
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceSecurityEventService.class,
                "getSubSecurityEvent");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceSecurityEventToGrpc.class,
                "getSubResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,SecurityEventReply> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,SecurityEventReply>(kafkaAbTools, inArgs, outArgs, StaticConfig.intervalTimes) {
            @Override
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

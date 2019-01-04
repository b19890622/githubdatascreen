package com.CrossCountry.Survey.service.jiabo;

import com.CrossCountry.Survey.commonservice.jiabo.*;
import com.CrossCountry.Survey.grpcmodel.jiabo.*;
import com.CrossCountry.Survey.intervalutils.HostProtectIntervalUtils;
import com.datascreen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.StaticConfig;

import io.grpc.stub.StreamObserver;

@Component
public class ImportantBusinessHostStatusService extends DataScreenHostProtectionServiceGrpc.DataScreenHostProtectionServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    HostProtectIntervalUtils hostProtectInterval;

    @Override
    public StreamObserver<StateGridRequest> bidrectionalImportantBusinessHostStatus(
            StreamObserver<ImportantBusinessHostStatusReply> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(ImportantBusinessHostStatusCommonService.class,
                "getImportantBusinessHostStatus");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(ImportantBusinessHostStatusCommonServiceToGrpc.class,
                "getImportantBusinessHostStatus");
        // 轮询
        PollingOneToOneGrpc pollingOneToOneGrpc = new PollingOneToOneGrpc(kafkaAbTools, inArgs, outArgs,
                hostProtectInterval.getHOSTPROTECT_ZYYWZJZT()) {
        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

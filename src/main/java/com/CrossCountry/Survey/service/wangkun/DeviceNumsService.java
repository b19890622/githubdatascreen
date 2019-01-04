package com.CrossCountry.Survey.service.wangkun;

import com.CrossCountry.Survey.commonservice.wangkun.CommonServiceDeviceWarnService;
import com.CrossCountry.Survey.conversion.onetoone.PollingOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.grpcmodel.wangkun.CommonServiceDeviceWarnNumToGrpc;
import com.CrossCountry.Survey.intervalutils.NetWorkMonitorIntervalUtils;
import com.CrossCountry.Survey.utils.StaticConfig;
import com.datascreen.AllDeviceNumsReplay;
import com.datascreen.DataScreenNetWorkCheckServiceGrpc;
import com.datascreen.StateGridRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther wangkun
 * @date 2018/12/28 11:12
 */
@Component
public class DeviceNumsService extends DataScreenNetWorkCheckServiceGrpc.DataScreenNetWorkCheckServiceImplBase {
    @Autowired
    private KafkaAbTools kafkaAbTools;
    @Autowired
    NetWorkMonitorIntervalUtils netWorkMonitorIntervalUtils;
    @Override
    public StreamObserver<StateGridRequest> bidGetAllDeviceNums(StreamObserver<AllDeviceNumsReplay> responseObserver) {
        // 处理业务逻辑数据
        CommonInOutBoundArgs inArgs = new CommonInOutBoundArgs(CommonServiceDeviceWarnService.class,
                "getAllDeviceNumsPojo");
        // 将业务逻辑数据解析成grpc流
        CommonInOutBoundArgs outArgs = new CommonInOutBoundArgs(CommonServiceDeviceWarnNumToGrpc.class,
                "getAllDeviceNumsResponse");
        // 轮询
        PollingOneToOneGrpc<StateGridRequest,AllDeviceNumsReplay> pollingOneToOneGrpc = new PollingOneToOneGrpc<StateGridRequest,AllDeviceNumsReplay>(kafkaAbTools, inArgs, outArgs, netWorkMonitorIntervalUtils.getSUPSECURITYDEVNUM_GLGJSB()) {

        };
        return pollingOneToOneGrpc.getStreamObserver(responseObserver);
    }
}

package com.CrossCountry.Survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.CrossCountry.Survey.service.donggs.AccessControlsService;
import com.CrossCountry.Survey.service.donggs.VisitTimesService;
import com.datascreen.AccessControlsReply;
import com.CrossCountry.Survey.service.jiabo.ImportantBusinessHostStatusService;
import com.datascreen.DataScreenHostProtectionServiceGrpc;
import com.datascreen.SecretTopTenReply;
import com.datascreen.StateGridRequest;
import com.datascreen.VisitTimesReply;

import io.grpc.stub.StreamObserver;

import org.springframework.beans.factory.annotation.Autowired;
import com.CrossCountry.Survey.service.wangkun.AccessAndHighRiskService;
import com.datascreen.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import com.datascreen.ImportantBusinessHostStatusReply;
import com.datascreen.StateGridRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.service.dongl.CheckCountService;
import com.CrossCountry.Survey.service.dongl.InputService;
import com.datascreen.CheckCountReply;
import com.datascreen.DataScreenHostProtectionServiceGrpc;
import com.datascreen.InputReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

/**
 * @auther wangkun
 * @date 2018/12/30 9:45
 */
@Component
public class CommonHostProtectionService extends DataScreenHostProtectionServiceGrpc.DataScreenHostProtectionServiceImplBase {
@Autowired
AccessAndHighRiskService accessAndHighRiskService;
	  @Autowired //dgs
      VisitTimesService visitTimesService ;
	  @Autowired
	  AccessControlsService accessControlsService;
    @Autowired
    private ImportantBusinessHostStatusService importantBusinessHostStatusService;
	@Autowired
	InputService inputService;

	@Autowired
	CheckCountService checkCountService;

	// 接入概况 add dongl
	@Override
	public StreamObserver<StateGridRequest> bidInputSurvey(StreamObserver<InputReply> responseObserver) {
		// TODO Auto-generated method stub
		return inputService.bidInputSurvey(responseObserver);
	}

	// 安全核查统计 add dongl
	@Override
	public StreamObserver<StateGridRequest> bidCheckCount(StreamObserver<CheckCountReply> responseObserver) {
		// TODO Auto-generated method stub
		return checkCountService.bidCheckCount(responseObserver);
	}
    @Override
    public StreamObserver<StateGridRequest> bidAccessMonitoring(StreamObserver<AccessMonitoringReplay> responseObserver) {
        return accessAndHighRiskService.bidAccessMonitoring(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidHighRiskSurveillance(StreamObserver<HighRiskSurveillanceReply> responseObserver) {
        return accessAndHighRiskService.bidHighRiskSurveillance(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidSystemAndAgentCount(StreamObserver<SystemAndAgentReply> responseObserver) {
        return accessAndHighRiskService.bidSystemAndAgentCount(responseObserver);
    }
	  @Override
		public StreamObserver<StateGridRequest> bidVisitTimes(
				StreamObserver<VisitTimesReply> responseObserver) {
			// TODO Auto-generated method stub
			return visitTimesService.bidVisitTimes(responseObserver);
		}

	  @Override
		public StreamObserver<StateGridRequest> bidAccessControls(
				StreamObserver<AccessControlsReply> responseObserver) {
			// TODO Auto-generated method stub
			return accessControlsService.bidAccessControls(responseObserver);
		}
    //全网安全核查情况-地图中数据 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidrectionalImportantBusinessHostStatus(StreamObserver<ImportantBusinessHostStatusReply> responseObserver) {
        // TODO Auto-generated method stub
        return importantBusinessHostStatusService.bidrectionalImportantBusinessHostStatus(responseObserver);
    }
}

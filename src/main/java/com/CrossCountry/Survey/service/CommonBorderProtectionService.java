package com.CrossCountry.Survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.service.donggs.EquipmentService;
import com.CrossCountry.Survey.service.donggs.ProtectionService;
import com.CrossCountry.Survey.service.dongl.SecretTopTenService;
import com.CrossCountry.Survey.service.dongl.SecurityAnalasisService;
import com.CrossCountry.Survey.service.jiabo.TunnelAndStrategyService;
import com.CrossCountry.Survey.service.jiabo.VerificationCaseService;
import com.CrossCountry.Survey.service.liujg.DataScreenBorderProService;
import com.CrossCountry.Survey.service.wangkun.ChangeAndTopFiveService;
import com.datascreen.DataScreenBorderProtectionServiceGrpc;
import com.datascreen.EquipmentNumberReply;
import com.datascreen.GetChangeNumReplay;
import com.datascreen.GetTopFiveWarnReplay;
import com.datascreen.ProtectionNumberReply;
import com.datascreen.SecretTopTenReply;
import com.datascreen.SecurityAnalasisReply;
import com.datascreen.StateGridRequest;
import com.datascreen.TendencyOnWeekMsg;
import com.datascreen.TunnelAndStrategyReply;
import com.datascreen.VerificationCaseReply;
import com.datascreen.ZxDeviceWarnMsg;

import io.grpc.stub.StreamObserver;

@Component
public class CommonBorderProtectionService
		extends DataScreenBorderProtectionServiceGrpc.DataScreenBorderProtectionServiceImplBase {
	@Autowired
	SecretTopTenService secretTopTenService;

	@Autowired
	SecurityAnalasisService securityAnalasisService;
	
	@Autowired
    ChangeAndTopFiveService changeAndTopFiveService;

	@Autowired
	DataScreenBorderProService dataScreenBorderProService;
	
	@Autowired
    private VerificationCaseService verificationCaseService;

    @Autowired
    private TunnelAndStrategyService tunnelAndStrategyService;
    
    @Autowired
	ProtectionService protectionService;
	
	@Autowired
	EquipmentService equipmentService;

	@Override
	public StreamObserver<StateGridRequest> bidrectionalSecretTopTen(
			StreamObserver<SecretTopTenReply> responseObserver) {
		// TODO Auto-generated method stub
		return secretTopTenService.bidrectionalSecretTopTen(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> bidrectionalSecurityAnalasis(
			StreamObserver<SecurityAnalasisReply> responseObserver) {
		// TODO Auto-generated method stub
		return securityAnalasisService.bidrectionalSecurityAnalasis(responseObserver);
	}

    @Override
    public StreamObserver<StateGridRequest> bidGetTopFiveWarn(StreamObserver<GetTopFiveWarnReplay> responseObserver) {
        return changeAndTopFiveService.bidGetTopFiveWarn(responseObserver);
    }
    
    //近一周核查情况 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidirectionalVerificationCase(StreamObserver<VerificationCaseReply> responseObserver) {
        // TODO Auto-generated method stub
        return verificationCaseService.bidirectionalVerificationCase(responseObserver);
    }
    //各网省公司隧道、策略情况 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidirectionalTunnelAndStrategy(StreamObserver<TunnelAndStrategyReply> responseObserver) {
        // TODO Auto-generated method stub
        return tunnelAndStrategyService.bidirectionalTunnelAndStrategy(responseObserver);
    }
    
    @Override
	public StreamObserver<StateGridRequest> bidGetProtectionNumber(StreamObserver<ProtectionNumberReply> responseObserver) {
		// TODO Auto-generated method stub
		return protectionService.bidGetProtectionNumber(responseObserver);
	}
	@Override
	public StreamObserver<StateGridRequest> bidGetEquipmentNumber(StreamObserver<EquipmentNumberReply> responseObserver) {
		// TODO Auto-generated method stub
		return equipmentService.bidGetEquipmentNumber(responseObserver);
	}
	
	@Override
	public StreamObserver<StateGridRequest> getZxDeviceDisplay(StreamObserver<ZxDeviceWarnMsg> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenBorderProService.getZxDeviceDisplay(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getTendencyOnWeekDisplay(
			StreamObserver<TendencyOnWeekMsg> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenBorderProService.getTendencyOnWeekDisplay(responseObserver);
	}

    @Override
    public StreamObserver<StateGridRequest> bidGetChangeNum(StreamObserver<GetChangeNumReplay> responseObserver) {
        return changeAndTopFiveService.bidGetChangeNum(responseObserver);
    }
}

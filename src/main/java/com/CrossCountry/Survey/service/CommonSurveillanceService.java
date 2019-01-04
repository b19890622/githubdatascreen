package com.CrossCountry.Survey.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.service.donggs.AlarmDistributionService;
import com.CrossCountry.Survey.service.donggs.AlarmEquipmentTopFiveService;
import com.CrossCountry.Survey.service.jiabo.SafetyRiskManageService;
import com.CrossCountry.Survey.service.jiabo.SafetyRiskTestService;
import com.CrossCountry.Survey.service.liujg.DataScreenSurveillanceService;
import com.CrossCountry.Survey.service.wangkun.WarnAccessService;
import com.datascreen.AlarmDistributionReply;
import com.datascreen.AlarmEquipmentTopFiveReply;
import com.datascreen.AllNetWorkMsg;
import com.CrossCountry.Survey.service.dongl.MainStationAlarmDistributionService;
import com.CrossCountry.Survey.service.dongl.SubStationAlarmDistributionService;
import com.datascreen.DataScreenSurveillanceServiceGrpc;
import com.datascreen.SafetyRiskManageAndTestReply;
import com.datascreen.SecurityRiskReply;
import com.datascreen.StateGridRequest;
import com.datascreen.WarnFigureReply;

import io.grpc.stub.StreamObserver;
import com.datascreen.MainStationAlarmDistributionReply;
import com.datascreen.StateGridRequest;
import com.datascreen.SubStationAlarmDistributionReply;

import io.grpc.stub.StreamObserver;
//核查
@Component
public class CommonSurveillanceService extends DataScreenSurveillanceServiceGrpc.DataScreenSurveillanceServiceImplBase{
    @Autowired
    WarnAccessService warnAccessService;

	@Autowired
	MainStationAlarmDistributionService mainStationAlarmDistributionService;

	@Autowired
	SubStationAlarmDistributionService subStationAlarmDistributionService;
	@Autowired
	DataScreenSurveillanceService dataScreenSurveillanceService;

	@Autowired
	AlarmDistributionService alarmDistributionService ;

	@Autowired
	AlarmEquipmentTopFiveService alarmEquipmentTopFiveService ;

	@Autowired
	private SafetyRiskTestService safetyRiskTestService;

    @Autowired
    private SafetyRiskManageService safetyRiskManageService;


	//主站告警分布 add dongl
	@Override
	public StreamObserver<StateGridRequest> bidrectionalMainStationAlarmDistribution(
			StreamObserver<MainStationAlarmDistributionReply> responseObserver) {
		// TODO Auto-generated method stub
		return mainStationAlarmDistributionService.bidrectionalMainStationAlarmDistribution(responseObserver);
	}

	//厂站告警分布 add dongl
	@Override
	public StreamObserver<StateGridRequest> bidrectionalSubStationAlarmDistribution(
			StreamObserver<SubStationAlarmDistributionReply> responseObserver) {
		// TODO Auto-generated method stub
		return subStationAlarmDistributionService.bidrectionalSubStationAlarmDistribution(responseObserver);
	}
	@Override
	public StreamObserver<StateGridRequest> bidAlarmDistribution(
			StreamObserver<AlarmDistributionReply> responseObserver) {
		// TODO Auto-generated method stub
		return alarmDistributionService.bidAlarmDistribution(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> bidAlarmEquipmentTopFive(
			StreamObserver<AlarmEquipmentTopFiveReply> responseObserver) {
		// TODO Auto-generated method stub
		return alarmEquipmentTopFiveService.bidAlarmEquipmentTopFive(responseObserver);
	}

	//全网告警情况
	@Override
	public StreamObserver<StateGridRequest> allNetWrokCompliance(StreamObserver<AllNetWorkMsg> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenSurveillanceService.allNetWrokCompliance(responseObserver);
	}
	//告警曲线 wangkun
    @Override
    public StreamObserver<StateGridRequest> bidWarnFigure(StreamObserver<WarnFigureReply> responseObserver) {
        return warnAccessService.bidWarnFigure(responseObserver);
    }
	//安全风险监测，安全风险处置 wangkun
    @Override
    public StreamObserver<StateGridRequest> bidSecurityRisk(StreamObserver<SecurityRiskReply> responseObserver) {
        return warnAccessService.bidSecurityRisk(responseObserver);
    }

    //当日安全风险处置top10 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidrectionalSafetyRiskManage(StreamObserver<SafetyRiskManageAndTestReply> responseObserver) {
        // TODO Auto-generated method stub
        return safetyRiskManageService.bidrectionalSafetyRiskManage(responseObserver);
    }
    //当日安全风险监测top10 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidrectionalSafetyRiskTest(StreamObserver<SafetyRiskManageAndTestReply> responseObserver) {
        // TODO Auto-generated method stub
        return safetyRiskTestService.bidrectionalSafetyRiskTest(responseObserver);
    }

	@Override
	public StreamObserver<StateGridRequest> bidSecurityRiskDeal(StreamObserver<SecurityRiskReply> responseObserver) {
		return warnAccessService.bidSecurityRiskDeal(responseObserver);
	}
}

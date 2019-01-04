package com.CrossCountry.Survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.service.donggs.GaoJingService;
import com.CrossCountry.Survey.service.donggs.MenaceMonitorService;
import com.CrossCountry.Survey.service.donggs.RemoteprovinceService;
import com.CrossCountry.Survey.service.donggs.SecureTopologyService;
import com.CrossCountry.Survey.service.dongl.AlarmMonitorAllService;
import com.CrossCountry.Survey.service.dongl.AlarmMonitorLocalService;
import com.CrossCountry.Survey.service.dongl.OnlineRateAndSecretPassService;
import com.CrossCountry.Survey.service.dongl.SafetyComplianceService;
import com.CrossCountry.Survey.service.liujg.DataScreenService;
import com.CrossCountry.Survey.service.shichf.ScreenDrawMapDataGrpcService;
import com.CrossCountry.Survey.service.wangkun.SecurityEventService;
import com.datascreen.AllDateReply;
import com.datascreen.DataScreenServiceGrpc;
import com.datascreen.DrawingMapsForParameters;
import com.datascreen.HelloReply;
import com.datascreen.HelloRequest;
import com.datascreen.LargenArrayProtobuf;
import com.datascreen.NumberReply;
import com.datascreen.OnDutyPersion;
import com.datascreen.OnlineRateAndSecretPassReply;
import com.datascreen.RiskWarningProto;
import com.datascreen.RunAllStateReply;
import com.datascreen.RunStateReply;
import com.datascreen.SafetyComplianceReply;
import com.datascreen.ScreenDayRiskTOPTenForParameters;
import com.datascreen.ScreenSecretPassOnlineRateForParameters;
import com.datascreen.SecureTopologyReply;
import com.datascreen.SecurityEventReply;
import com.datascreen.SecuritySystemStateForParameters;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;

@Component
public class CommonService extends DataScreenServiceGrpc.DataScreenServiceImplBase {
	@Autowired
	private DataScreenService dataScreenService;

	@Autowired
	private AlarmMonitorLocalService mainStationService;

	@Autowired
	private AlarmMonitorAllService allStationService;

	@Autowired
	private SafetyComplianceService safetyComplianceService;

	@Autowired
	private OnlineRateAndSecretPassService onlineRateAnadSecretPassService;

	@Autowired
	private ScreenDrawMapDataGrpcService screenDrawMapDataGrpcService;

	@Autowired
	private SecurityEventService securityEventService;

	@Autowired
	private GaoJingService gaoJingService;

	@Autowired
	private MenaceMonitorService menaceMonitorService;
	@Autowired
	private SecureTopologyService secureTopologyService;
	@Autowired
	private RemoteprovinceService remoteprovinceService;

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		// TODO Auto-generated method stub
		dataScreenService.sayHello(request, responseObserver);
	}

	// add by dongl 国调告警监视本级
	@Override
	public StreamObserver<StateGridRequest> bidirectionalMainStation(StreamObserver<RunStateReply> responseObserver) {
		// TODO Auto-generated method stub
		return mainStationService.bidirectionalMainStation(responseObserver);
	}

	// add by dongl 全网告警监视
	@Override
	public StreamObserver<StateGridRequest> bidirectionalAllStation(StreamObserver<RunAllStateReply> responseObserver) {
		// TODO Auto-generated method stub
		return allStationService.bidirectionalAllStation(responseObserver);
	}

	// add by dongl 安全合规率
	@Override
	public StreamObserver<StateGridRequest> bidirectionalSafetyCompliance(
			StreamObserver<SafetyComplianceReply> responseObserver) {
		// TODO Auto-generated method stub
		return safetyComplianceService.bidirectionalSafetyCompliance(responseObserver);
	}

	// add by dongl 在线率、密通率
	@Override
	public StreamObserver<StateGridRequest> bidirectionalOnlineRateAndSecretPassRate(
			StreamObserver<OnlineRateAndSecretPassReply> responseObserver) {
		// TODO Auto-generated method stub
		return onlineRateAnadSecretPassService.bidirectionalOnlineRateAndSecretPassRate(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getOnDutyPerson(StreamObserver<OnDutyPersion> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenService.getOnDutyPerson(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getRiskWarning(StreamObserver<RiskWarningProto> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenService.getRiskWarning(responseObserver);
	}

	// 安防系统及设备在线状态 add shichf
	@Override
	public StreamObserver<StateGridRequest> screenSecuritySystemState(
			StreamObserver<SecuritySystemStateForParameters> responseObserver) {
		return screenDrawMapDataGrpcService.screenSecuritySystemState(responseObserver);
	}

	// 网络安全运行水平在线率密通率 add shichf
	public StreamObserver<StateGridRequest> screenSecretPassOnlineRate(
			StreamObserver<ScreenSecretPassOnlineRateForParameters> responseObserver) {
		return screenDrawMapDataGrpcService.screenSecretPassOnlineRate(responseObserver);
	}

	// 当日安全风险top10 add shichf
	public StreamObserver<StateGridRequest> screenDayRiskTOPTen(
			StreamObserver<ScreenDayRiskTOPTenForParameters> responseObserver) {
		return screenDrawMapDataGrpcService.screenDayRiskTOPTen(responseObserver);
	}

	// 发生威胁单位top10 add shichf
	public StreamObserver<StateGridRequest> screenDayRiskUnitTOPTen(
			StreamObserver<ScreenDayRiskTOPTenForParameters> responseObserver) {
		return screenDrawMapDataGrpcService.screenDayRiskUnitTOPTen(responseObserver);
	}

	// 最新安全事件本级 wangkun
	@Override
	public StreamObserver<StateGridRequest> bidrectionalSecurityEvent(
			StreamObserver<SecurityEventReply> responseObserver) {
		return securityEventService.bidrectionalSecurityEvent(responseObserver);
	}

	// 最新安全告警下级 wangkun
	@Override
	public StreamObserver<StateGridRequest> bidrectionalSubSecurityEvent(
			StreamObserver<SecurityEventReply> responseObserver) {
		return securityEventService.bidrectionalSubSecurityEvent(responseObserver);
	}

	// 大屏地图 add shichf
	public StreamObserver<StateGridRequest> bidirectionalDrawingMapsFormat(
			StreamObserver<DrawingMapsForParameters> responseObserver) {
		return screenDrawMapDataGrpcService.bidirectionalDrawingMapsFormat(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getNumber(StreamObserver<NumberReply> responseObserver) {

		return gaoJingService.getNumber(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getMenaceMonitorProto(StreamObserver<AllDateReply> responseObserver) {

		return menaceMonitorService.getMenaceMonitorProto(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getSecureTopology(StreamObserver<SecureTopologyReply> responseObserver) {
		return secureTopologyService.getSecureTopology(responseObserver);
	}

	@Override
	public StreamObserver<StateGridRequest> getLargenTopology(StreamObserver<LargenArrayProtobuf> responseObserver) {

		return remoteprovinceService.getLargenTopology(responseObserver);
	}

}

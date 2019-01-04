package com.CrossCountry.Survey.service;

import com.CrossCountry.Survey.service.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.CrossCountry.Survey.service.donggs.ComplianceNameAndNumService;
import com.CrossCountry.Survey.service.donggs.ComplianceStatisticsService;
import com.CrossCountry.Survey.service.dongl.LeftSecurityFlawService;
import com.CrossCountry.Survey.service.dongl.RightSecurityFlawService;
import com.CrossCountry.Survey.service.liujg.DataScreenVerificationService;
import com.CrossCountry.Survey.service.wangkun.BugStaticService;
import com.datascreen.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.service.dongl.LeftSecurityFlawService;
import com.CrossCountry.Survey.service.dongl.RightSecurityFlawService;
import com.CrossCountry.Survey.service.donggs.ComplianceNameAndNumService;
import com.CrossCountry.Survey.service.donggs.ComplianceStatisticsService;
import com.CrossCountry.Survey.service.liujg.DataScreenVerificationService;
import com.CrossCountry.Survey.service.wangkun.BugStaticService;
import com.datascreen.ComplianceReply;
import com.datascreen.ComplianceStatisticsReply;
import com.datascreen.DataScreenVerificationServiceGrpc;
import com.datascreen.LeftSecurityFlawReply;
import com.datascreen.RightSecurityFlawReply;
import com.datascreen.StateGridRequest;

import io.grpc.stub.StreamObserver;
import com.datascreen.StateGridRequest;
import com.datascreen.SurveillanceRegionMsg;
import com.datascreen.VulnerabilityCircleReply;
import com.datascreen.VulnerabilityListReply;
import com.datascreen.ZxSurveillanceMsg;

import io.grpc.stub.StreamObserver;

@Component
public class CommonVerificationService extends DataScreenVerificationServiceGrpc.DataScreenVerificationServiceImplBase{
    @Autowired
    BugStaticService bugStaticService;

	@Autowired
	DataScreenVerificationService dataScreenVerificationService;

	@Autowired
	ComplianceNameAndNumService complianceNameAndNumService;
	@Autowired
	ComplianceStatisticsService complianceStatisticsService;

	@Autowired
    private NetSecurityVerificaService netSecurityVerificaService;

    @Autowired
    private UnitsVerificationService unitsVerificationService;
	@Autowired
	private LeftSecurityFlawService leftSecurityFlawService;

	@Autowired
	private RightSecurityFlawService rightSecurityFlawService;

	@Autowired
	private NetSecurityVerificaMapService netSecurityVerificaMapService;

	@Override
	public StreamObserver<StateGridRequest> bidComplianceNameAndNum(
			StreamObserver<ComplianceReply> responseObserver) {
		// TODO Auto-generated method stub
		return complianceNameAndNumService.bidComplianceNameAndNum(responseObserver);
	}
	@Override
	public StreamObserver<StateGridRequest> bidComplianceStatistics(
			StreamObserver<ComplianceStatisticsReply> responseObserver) {
		// TODO Auto-generated method stub
		return complianceStatisticsService.bidComplianceStatistics(responseObserver);
	}

	//纵向合规情况统计
	@Override
	public StreamObserver<StateGridRequest> zxSurveillanceStat(StreamObserver<ZxSurveillanceMsg> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenVerificationService.zxSurveillanceStat(responseObserver);
	}

	//合规情况统计
	@Override
	public StreamObserver<StateGridRequest> surveillanceRegionStat(
			StreamObserver<SurveillanceRegionMsg> responseObserver) {
		// TODO Auto-generated method stub
		return dataScreenVerificationService.surveillanceRegionStat(responseObserver);
	}

    @Override
    public StreamObserver<StateGridRequest> bidVulnerabilityCircle(StreamObserver<VulnerabilityCircleReply> responseObserver) {
        return bugStaticService.bidVulnerabilityCircle(responseObserver);
    }

    @Override
    public StreamObserver<StateGridRequest> bidVulnerabilityList(StreamObserver<VulnerabilityListReply> responseObserver) {
        return bugStaticService.bidVulnerabilityList(responseObserver);
    }

    //全网安全核查情况-地图 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidrectionalNetSecurityVerifica(StreamObserver<NetSecurityVerificaReply> responseObserver) {
        // TODO Auto-generated method stub
        return netSecurityVerificaService.bidrectionalNetSecurityVerifica(responseObserver);
    }
    //全网安全核查情况-top10 add by jiabo
    @Override
    public StreamObserver<StateGridRequest> bidrectionalUnitsVerification(StreamObserver<UnitsVerificationReply> responseObserver) {
        // TODO Auto-generated method stub
        return unitsVerificationService.bidrectionalUnitsVerification(responseObserver);
    }


		// add by dongl I、II、III型漏洞分布 左图
		@Override
		public StreamObserver<StateGridRequest> bidrectionalLeftSecurityFlaw(StreamObserver<LeftSecurityFlawReply> responseObserver) {
			// TODO Auto-generated method stub
			return leftSecurityFlawService.bidrectionalLeftSecurityFlaw(responseObserver);
		}

		// add by dongl I、II、III型漏洞分布 右图
		@Override
		public StreamObserver<StateGridRequest> bidrectionalRightSecurityFlaw(StreamObserver<RightSecurityFlawReply> responseObserver) {
			// TODO Auto-generated method stub
			return rightSecurityFlawService.bidrectionalRightSecurityFlaw(responseObserver);
		}

	//全网安全核查情况-地图中数据 add by jiabo
	@Override
	public StreamObserver<StateGridRequest> bidrectionalNetSecurityVerificaMap(StreamObserver<NetSecurityVerificaMapReply> responseObserver) {
		// TODO Auto-generated method stub
		return netSecurityVerificaMapService.bidrectionalNetSecurityVerificaMap(responseObserver);
	}
}

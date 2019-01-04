package com.CrossCountry.Survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.service.donggs.TerraceDeviceService;
import com.CrossCountry.Survey.service.dongl.DeviceDeployService;
import com.CrossCountry.Survey.service.jiabo.SecretPassAndWarnSolveService;
import com.CrossCountry.Survey.service.wangkun.DeviceNumsService;
import com.datascreen.AllDeviceNumsReplay;
import com.datascreen.DataScreenNetWorkCheckServiceGrpc;
import com.datascreen.DeviceDeployReply;
import com.datascreen.SecretPassAndWarnSolveReply;
import com.datascreen.StateGridRequest;
import com.datascreen.TerraceDeviceNumberReply;

import io.grpc.stub.StreamObserver;

@Component
public class CommonNetWorkService extends DataScreenNetWorkCheckServiceGrpc.DataScreenNetWorkCheckServiceImplBase{

	@Autowired
	DeviceDeployService deviceDeployService;
	
	
	@Autowired
	DeviceNumsService deviceNumsService;

	@Autowired
	private SecretPassAndWarnSolveService secretPassAndWarnSolveService;
	
	@Autowired
	TerraceDeviceService terraceDeviceService;
	
	@Override
	public StreamObserver<StateGridRequest> bidrectionalDeviceDeploy(
			StreamObserver<DeviceDeployReply> responseObserver) {
		// TODO Auto-generated method stub
		return deviceDeployService.bidrectionalDeviceDeploy(responseObserver);
	}
	//各类告警设备数量
	@Override
	public StreamObserver<StateGridRequest> bidGetAllDeviceNums(StreamObserver<AllDeviceNumsReplay> responseObserver) {
		return deviceNumsService.bidGetAllDeviceNums(responseObserver);
	}
	
	//密通率及告警解决率 add jiabo
	@Override
	public StreamObserver<StateGridRequest> bidirectionalSecretPassAndWarnSolve(StreamObserver<SecretPassAndWarnSolveReply> responseObserver) {
		// TODO Auto-generated method stub
		return secretPassAndWarnSolveService.bidirectionalSecretPassAndWarnSolve(responseObserver);
	}
	
	@Override
	public StreamObserver<StateGridRequest> getTerraceDeviceNumber(StreamObserver<TerraceDeviceNumberReply> responseObserver) {
		// TODO Auto-generated method stub
		return terraceDeviceService.getTerraceDeviceNumber(responseObserver);
	}

}

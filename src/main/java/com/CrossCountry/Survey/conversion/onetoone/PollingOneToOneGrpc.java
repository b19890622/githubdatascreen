package com.CrossCountry.Survey.conversion.onetoone;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import com.CrossCountry.Survey.HeaderServerInterceptor;
import com.CrossCountry.Survey.conversion.thread.DefaultKafkaOneToOneGrpc;
import com.CrossCountry.Survey.conversion.thread.KafkaAbTools;
import com.CrossCountry.Survey.encapsulation.common.CommonHeapStore;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.Log4jUtil;

import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public abstract class PollingOneToOneGrpc<T, V> extends BaseOneToOneGrpc<T, V> {

	// 工具类
	private KafkaAbTools kafkatools;
	// 入参
	private Object inArgs;
	// 出参
	private Object outArgs;

	private long timeInterval;

	public PollingOneToOneGrpc(KafkaAbTools kafkatools, Object inArgs, Object outArgs,
			long timeInterval) {
		super(inArgs, outArgs);
		this.kafkatools = kafkatools;
		this.inArgs = inArgs;
		this.outArgs = outArgs;
		this.timeInterval = timeInterval;
	}

	@Override
	public StreamObserver<T> getStreamObserver(StreamObserver<V> responseObserver, boolean sysSign) {
		// TODO Auto-generated method stub
		final String remoteip = HeaderServerInterceptor.userThreadLocal.get().substring(1);// 远程设备IP+端口，例：192.100.10.38:8080
		final ServerCallStreamObserver<V> serverCallStreamObserver = (ServerCallStreamObserver<V>) responseObserver;
		serverCallStreamObserver.disableAutoInboundFlowControl();
		final AtomicBoolean wasReady = new AtomicBoolean(false);
		serverCallStreamObserver.setOnReadyHandler(new Runnable() {
			public void run() {
				if (serverCallStreamObserver.isReady() && wasReady.compareAndSet(false, true)) {
					serverCallStreamObserver.request(1);
				}
			}
		});
		return new StreamObserver<T>() {

			private ScheduledFuture future;

			private String name = this.toString();

			private volatile Object condition = null;
			
			private boolean flag = (inArgs instanceof List)?Boolean.TRUE:Boolean.FALSE;

			DefaultKafkaOneToOneGrpc kafkaOneToOneGrpc = new DefaultKafkaOneToOneGrpc(remoteip, new HashMap(), kafkatools);

			@Override
			public void onNext(T request) {
				try {
					if (future != null) {
						kafkaOneToOneGrpc.freeThread(future);
					}
					condition = getCopyRequestPojo(request, condition);
					if (getFirstResultGrpcFlag()) { // 适应于快速返回结果
						pushGrpcMessageToPeer(condition);
						if(flag){
							future = kafkatools.dealWithOneToOneLoseMsg((List<CommonInOutBoundArgs>)inArgs, condition, responseObserver, (CommonInOutBoundArgs)outArgs,
									timeInterval, timeInterval);
						}else{
							future = kafkatools.dealWithOneToOneLoseMsg((CommonInOutBoundArgs)inArgs, condition, responseObserver, (CommonInOutBoundArgs)outArgs,
									timeInterval, timeInterval);
						}
						
					} else {
						if(flag){
							future = kafkatools.dealWithOneToOneLoseMsg((List<CommonInOutBoundArgs>)inArgs, condition, responseObserver, (CommonInOutBoundArgs)outArgs,
									timeInterval, timeInterval);
						}else{
							future = kafkatools.dealWithOneToOneLoseMsg((CommonInOutBoundArgs)inArgs, condition, responseObserver, (CommonInOutBoundArgs)outArgs,
									timeInterval, timeInterval);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (serverCallStreamObserver.isReady()) {
					serverCallStreamObserver.request(1);
				} else {
					wasReady.set(false);
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				Log4jUtil.error(PollingOneToOneGrpc.class, name + ": " + t.getMessage(), t);
				if (future != null) {
					kafkaOneToOneGrpc.freeThread(future);
				}
				responseObserver.onCompleted();
			}

			@Override
			public void onCompleted() {
				Log4jUtil.info(PollingOneToOneGrpc.class, name + ": COMPLETED");
				if (future != null) {
					kafkaOneToOneGrpc.freeThread(future);
				}
				responseObserver.onCompleted();
			}

			private void pushGrpcMessageToPeer(Object request) {
				if (sysSign) {
					CommonHeapStore.cachedThreadPool.execute((new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							V v = getFirstResultGrpcObj(request);
							if (v != null) {
								responseObserver.onNext(v);
							}
						}
					}));
				} else {
					V v = getFirstResultGrpcObj(request);
					if (v != null) {
						responseObserver.onNext(v);
					}
				}
			}
		};

	}
	
	@Override
	public StreamObserver<T> getStreamObserver(StreamObserver<V> responseObserver) {
		// TODO Auto-generated method stub
		return getStreamObserver(responseObserver, false);
	}
	
}

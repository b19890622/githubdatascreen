package com.CrossCountry.Survey.conversion.onetoone;

import java.util.concurrent.atomic.AtomicBoolean;

import com.CrossCountry.Survey.HeaderServerInterceptor;
import com.CrossCountry.Survey.encapsulation.common.CommonHeapStore;
import com.CrossCountry.Survey.utils.Log4jUtil;

import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public abstract class CommonOneToOneGrpc<T, V> extends BaseOneToOneGrpc<T, V> {

	public CommonOneToOneGrpc(Object inArgs, Object outArgs) {
		super(inArgs, outArgs);
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
			@Override
			public void onNext(T request) {
				try {
					Object condition = getRequestPojo(request);
					pushGrpcMessageToPeer(condition);
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
				Log4jUtil.error(PollingOneToOneGrpc.class, t.getMessage(), t);
				responseObserver.onCompleted();
			}

			@Override
			public void onCompleted() {
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

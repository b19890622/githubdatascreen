package com.CrossCountry.Survey.conversion.onetoone;

import io.grpc.stub.StreamObserver;

//封装grpc双向流内部实现方法
public interface OneToOneStreamGrpc <T,V> extends OneToOneArgsDeal<T>, OneToOneReturnFirst <V>{
	
	/**
	 * 1对1双向流模板
	 * @param responseObserver
	 * @param sysSign
	 * @return
	 */
	public StreamObserver<T> getStreamObserver(StreamObserver<V> responseObserver, boolean sysSign);
	
	/**
	 * 1对1双向流模板 同步
	 * @param responseObserver
	 * @return
	 */
	public StreamObserver<T> getStreamObserver(StreamObserver<V> responseObserver);
	

}

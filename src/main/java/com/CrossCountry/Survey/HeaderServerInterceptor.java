package com.CrossCountry.Survey;

import java.util.Iterator;

import io.grpc.Attributes.Key;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class HeaderServerInterceptor implements ServerInterceptor{


	public static ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();

	@Override
	public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,

			ServerCallHandler<ReqT, RespT> next) {
		// TODO Auto-generated method stub
		Iterator<Key<?>> i = call.getAttributes().keys().iterator();
		String remoteIP = "";
		
		while(i.hasNext()){
			Key<?> key = i.next();
			if("remote-addr".equals(key.toString())){
				remoteIP = call.getAttributes().get(key).toString();
				userThreadLocal.set(remoteIP);
				remoteIP = remoteIP.replace("/", "");
				String[] ip = remoteIP.split(":");
			}
		}
		String msg = remoteIP + " " + Thread.currentThread().getName() + " called " + call.getMethodDescriptor().getFullMethodName();
		System.out.println(msg+"....................");
		return next.startCall(call, headers);
	}


}

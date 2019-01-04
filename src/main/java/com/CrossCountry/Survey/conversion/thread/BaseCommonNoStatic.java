package com.CrossCountry.Survey.conversion.thread;

import java.lang.reflect.Method;

import com.CrossCountry.Survey.utils.Log4jUtil;

public class BaseCommonNoStatic extends BaseCommonGpc{
	// 返回数据
	private Object responseObserver;
	
	public BaseCommonNoStatic(Object responseObserver, Object inArgs,
			Object outArgs) {
		super(inArgs, outArgs);
		this.responseObserver = responseObserver;
	}
	
	public void pushGrpcObjToPeer(Object commandArgs){
		try{
			Object resultObj = getMainPeerGrpcObj(commandArgs);
			if(resultObj != null){
				Method method = responseObserver.getClass().getMethod("onNext", Object.class);
				method.setAccessible(true);
				method.invoke(responseObserver, resultObj);
				resultObj = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonNoStaticThread.class,e.getMessage(),e);
		}
	}
}

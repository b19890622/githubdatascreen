package com.CrossCountry.Survey.conversion.thread;

import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;

public class CommonMoreNoStaticPollThread extends BaseCommonNoStatic implements Runnable{

	/**
	 * 请求参数
	 */
	private Object commandArgs;
	
	public CommonMoreNoStaticPollThread(Object responseObserver, Object commandArgs, List<CommonInOutBoundArgs> inArgs,
			CommonInOutBoundArgs outArgs) {
		super(responseObserver, inArgs, outArgs);
		// TODO Auto-generated constructor stub
		this.commandArgs = commandArgs;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pushGrpcObjToPeer(commandArgs);
	}

}

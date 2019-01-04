package com.CrossCountry.Survey.conversion.thread;

import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;

public class CommonNoStaticPollingThread extends BaseCommonNoStatic implements Runnable {

	private Object commandArgs;
	/**
	 * 构造方法
	 * 
	 * @param responseObserver
	 *            返回数据
	 * @param commandArgs
	 *            UI封装请求参数数据
	 * @param inArgs
	 *            入参处理逻辑类
	 * @param outArgs
	 *            输出处理逻辑类
	 */
	public CommonNoStaticPollingThread(Object responseObserver, Object commandArgs, CommonInOutBoundArgs inArgs,
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

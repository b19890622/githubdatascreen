package com.CrossCountry.Survey.conversion.thread;

import java.util.Deque;

import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;

public class CommonNoStaticThread extends BaseCommonNoStatic implements Runnable{

	private Deque<Boolean> deque;
	
	private Object commandArgs;
	
	/**
	 * 构造方法
	 * @param responseObserver 返回数据
	 * @param deque 1对1 维持队列
	 * @param commandArgs UI封装请求参数数据
	 * @param inArgs  入参处理逻辑类
	 * @param outArgs 输出处理逻辑类
	 */
	public CommonNoStaticThread(Object responseObserver, Deque<Boolean> deque, Object commandArgs,
			CommonInOutBoundArgs inArgs, CommonInOutBoundArgs outArgs) {
		super(responseObserver, inArgs, outArgs);
		this.commandArgs = commandArgs;
		this.deque = deque;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Boolean msg = deque.poll();
		if (msg != null) {
			pushGrpcObjToPeer(commandArgs);
		}
	}

}

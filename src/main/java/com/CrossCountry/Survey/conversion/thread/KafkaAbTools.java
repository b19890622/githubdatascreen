package com.CrossCountry.Survey.conversion.thread;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ScheduledFuture;

import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;

public interface KafkaAbTools{

	//丢失 一对一 单一service
	/**
	 * @param className   实现pojo 对象名称
	 * @param methodName  实现pojo 对应方法
	 * @param commandArgs 对应方法所对应参数
	 * @param queue       生成新的队列
	 * @param response    返回前台的response
	 * @param methodGrpc  转换方法名
	 * @return
	 * @throws Exception
	 */
	public ScheduledFuture dealWithOneToOneLoseMsg(Class className,String methodName, Object commandArgs,
			Queue<Boolean> queue, Object response, String methodGrpc) throws Exception;
	
	
	//丢失 一对一  多个service
		/**
		 * @param className   实现pojo 对象class名称集合
		 * @param methodName  实现pojo 对应方法集合
		 * @param commandArgs 对应方法所对应参数
		 * @param queue       生成新的队列
		 * @param response    返回前台的response
		 * @param methodGrpc  转换方法名
		 * @return
		 * @throws Exception
		 */
	public ScheduledFuture dealWithMoreServiceOneToOneLoseMsg(List<Class> className,List<String> methodName, 
			Object commandArgs,	Queue<Boolean> queue, Object response, String methodGrpc) throws Exception;
	
	
	
	/**
	 * 释放资源
	 * @param future 加入threadpool 返回的future
	 * @return
	 */
	public boolean freeScheduledThread(ScheduledFuture future);
	

	
	
	/**
	 * 处理 双向流 1对1 处理逻辑
	 * @param inArgs 处理UI传参后的数据处理工程
	 * @param commandArgs UI的入参obj
	 * @param queue 1对1维持的队列信息
	 * @param response 1对1维持的输出对象
	 * @param outArgs 处理 inArgs得到数据进行加工的逻辑类
	 * @return
	 * @throws Exception 
	 */
	public ScheduledFuture dealWithOneToOneLoseMsg(CommonInOutBoundArgs inArgs, Object commandArgs,
			Queue<Boolean> queue, Object response, CommonInOutBoundArgs outArgs) throws Exception;
	
	/**
	 * 处理 双向流 1对1 处理逻辑
	 * @param inArgs 处理UI传参后的数据处理工程 list 多对象
	 * @param commandArgs UI的入参obj
	 * @param queue 1对1维持的队列信息
	 * @param response 1对1维持的输出对象
	 * @param outArgs 处理 inArgs得到数据进行加工的逻辑类
	 * @return
	 * @throws Exception 
	 */
	public ScheduledFuture dealWithOneToOneLoseMsg(List<CommonInOutBoundArgs> inArgs, Object commandArgs,
			Queue<Boolean> queue, Object response, CommonInOutBoundArgs outArgs) throws Exception;
	
	/**
	 * 处理双向流1对1 轮询
	 * @param inArgs 处理UI传参后的数据处理对象
	 * @param commandArgs UI的入参obj
	 * @param response 1对1维持的输出对象
	 * @param outArgs 处理 inArgs得到数据进行加工的逻辑类
	 * @param timeInterval 轮询的时间间隔单位毫秒
	 * @return
	 * @throws Exception
	 */
	public ScheduledFuture dealWithOneToOneLoseMsg(CommonInOutBoundArgs inArgs, Object commandArgs,
			Object response, CommonInOutBoundArgs outArgs, long timeInterval, long lazyintal) throws Exception;
	
	/**
	 * 处理双向流1对1 轮询
	 * @param inArgs 处理UI传参后的数据处理对象
	 * @param commandArgs UI的入参obj list类型
	 * @param response 1对1维持的队列信息
	 * @param outArgs 处理 inArgs得到数据进行加工的逻辑类
	 * @param timeInterval 轮询的时间间隔单位毫秒
	 * @param lazyintal 延时的间隔 单位毫秒
	 * @return
	 * @throws Exception
	 */
	public ScheduledFuture dealWithOneToOneLoseMsg(List<CommonInOutBoundArgs> inArgs, Object commandArgs,
			Object response, CommonInOutBoundArgs outArgs, long timeInterval, long lazyintal) throws Exception;
	
}

package com.CrossCountry.Survey.conversion.thread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.CrossCountry.Survey.utils.SpringContextUtil;

/**
 * 处理多service逻辑处理功能的实现
 * @author Administrator
 *
 */
public class CommonMoreNoStaticThread implements Runnable{

	//返回数据
	private Object responseObserver;
	//1对1 维持队列
	private Deque<Boolean> deque;
    //UI封装请求参数数据
	private Object commandArgs;
    //入参处理逻辑类 含有多个处理类
	private List<CommonInOutBoundArgs> inArgsList;
    //输出处理逻辑类
	private CommonInOutBoundArgs outArgs;
	
	/**
	 * 构造方法
	 * @param responseObserver 返回数据
	 * @param deque 1对1 维持队列
	 * @param commandArgs UI封装请求参数数据
	 * @param inArgs  入参处理逻辑类
	 * @param outArgs 输出处理逻辑类
	 */
	public CommonMoreNoStaticThread(Object responseObserver, Deque<Boolean> deque, Object commandArgs,
			List<CommonInOutBoundArgs> inArgsList, CommonInOutBoundArgs outArgs) {
		super();
		this.responseObserver = responseObserver;
		this.deque = deque;
		this.commandArgs = commandArgs;
		this.inArgsList = inArgsList;
		this.outArgs = outArgs;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Boolean msg = deque.poll();
		if (msg != null) {
			try {
				Object obj = null;
				List<Object> objList = new ArrayList<Object>();
				Method method = null;
				Object resultObj = null;
				Class objClass = null;
				for(int i=0;i<inArgsList.size();i++){
					objClass = inArgsList.get(i).getClassName();
					obj = SpringContextUtil.getBeanByClass(objClass);
					try{
						method = objClass.getMethod(inArgsList.get(i).getMethodName(), Object.class);
						resultObj = method.invoke(obj, commandArgs);
					}catch (Exception e){
						if(e instanceof  NoSuchMethodException){
							method = objClass.getMethod(inArgsList.get(i).getMethodName(), null);
							Log4jUtil.error(CommonMoreNoStaticThread.class,e.getMessage(),e);
							resultObj = method.invoke(obj);
						}
						Log4jUtil.error(CommonMoreNoStaticThread.class,e.getMessage(),e);
					}
					if(resultObj != null){
						objList.add(resultObj);
					}
				}
				if(objList.size() != 0){
					if(outArgs == null){
						throw new Exception("转换方法不能置为空，请确认!");
					}
					Class outClassName = outArgs.getClassName();
					Object outObj = outClassName.newInstance();
					Method outMethod = outClassName.getMethod(outArgs.getMethodName(), List.class);
					resultObj = outMethod.invoke(outObj, objList);
					if(resultObj == null){
						return;
					}
					method = responseObserver.getClass().getMethod("onNext", Object.class);
					method.setAccessible(true);
					method.invoke(responseObserver, resultObj);
					//Log4jUtil.info(CommonOneToOneMorePushCheckThread.class, resultObj.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(CommonNoStaticThread.class,e.getMessage(),e);
			}
		}
	}

}

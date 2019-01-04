package com.CrossCountry.Survey.encapsulation.common;

/**
 * 一对一入参描述 处理接收参数后续处理
 * @author Administrator
 *
 */
public class CommonInOutBoundArgs {

	/**
	 * 处理类名称class
	 */
	private Class className;
	
	/**
	 * 处理类的方法名称
	 */
	private String methodName;
	

	public CommonInOutBoundArgs(Class className, String methodName) {
		super();
		this.className = className;
		this.methodName = methodName;
	}


	public Class getClassName() {
		return className;
	}


	public void setClassName(Class className) {
		this.className = className;
	}


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	
	
	
}

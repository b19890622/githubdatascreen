package com.CrossCountry.Survey.conversion.onetoone;

//处理入参数据
public interface OneToOneArgsDeal<T> {
	
	/**
	 * 处理grpc请求参数
	 * @param v 参数对象
	 * @return 返回K对象
	 */
	public Object getRequestPojo(T v);
	
	/**
	 * 校验grpc转换普通POJO对象
	 * @param v
	 * @return
	 * @throws Exception 
	 */
	public Object getCopyRequestPojo(T v, Object commandsArgs) throws Exception;
	
	
}

package com.CrossCountry.Survey.conversion.onetoone;

public interface OneToOneReturnFirst <V>{

	/**
	 * 是否马上获取数据结构标识
	 * @return
	 */
	public boolean getFirstResultGrpcFlag();
	/**
	 * 及时访问请求数据的grpc结果信息
	 * @param request
	 * @return
	 */
	public V getFirstResultGrpcObj(Object request); 
}

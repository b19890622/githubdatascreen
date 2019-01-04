package com.CrossCountry.Survey.encapsulation.common;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONObject;

public class AtoAttrSetting<T, V> {
	
	private Lock lock = new ReentrantLock();
	
	/**
	 * 获取condition 数据
	 * @param obj  grpc对象
	 * @param classObj grpc对应的class对象
	 * @return 普通pojo对象 
	 */
	public V getConditionFromGrpc(Object obj, Class<T> classObj, Class<V> condition){
		V destObj = null;
		try {
			lock.lock();
			T objT = classObj.cast(obj);
			destObj = (V)TransformToGrpcPo.convertToFromGrpc(condition, objT);
			return destObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 自定义从GRPC对象获取condition对象 
	 * @param condition 源对象
	 * @param objClass  转换工具类class 对象 需要有公有构造函数
	 * @param method    转换方法 参数唯一为 condition对象类型一致
	 * @return   返回工具类对象信息
	 */
	public void getSetConditionFromGrpc(T condition, Class objClass, String method, V conditionDest){
		try {
			lock.lock();
			Object obj = objClass.newInstance();
			Method methodObj = obj.getClass().getMethod(method, condition.getClass(), conditionDest.getClass());
			methodObj.invoke(obj, condition, conditionDest);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	
	
	
	/**
	 * 获得jdk原生copy对象 使用深度或者浅度需要实现copy接口
	 * @param condition condition对象
	 * @return
	 */
	public V copyConditionArgs(V condition){  
		try {
			lock.lock();
			Method method = condition.getClass().getDeclaredMethod("clone");
			method.setAccessible(true);
			return (V)method.invoke(condition, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 工具类copy 方式
	 * @param condition 源方法对象
	 * @param obj   目标方法对象
	 * @param classObj 目标方法加载对象
	 * @return 返回目标对象
	 */
	public V getUtilConditionArgs(V condition, Object obj, Class<V> classObj){
		try {
			lock.lock();
			V dest = classObj.cast(obj);
			BeanUtils.copyProperties(dest, condition);
			return dest;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	
	/**
	 * 自定义获取数据对象
	 * @param condition 源对象
	 * @param objClass  转换工具类class 对象 需要有公有构造函数
	 * @param method    转换方法 参数唯一为 condition对象类型一致
	 * @return   返回工具类对象信息
	 */
	public V getSetConditionArgs(V condition, Class objClass, String method){
		try {
			lock.lock();
			Object obj = objClass.newInstance();
			Method methodObj = obj.getClass().getMethod(method, condition.getClass());
			return (V)methodObj.invoke(obj, condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 获得数据的深度拷贝 无需实现序列化接口 clone接口
	 * @param condition
	 * @return
	 */
	public Object getConditionCopyArgs(V condition){
		try {
			lock.lock();
			return JSONObject.toBean(JSONObject.fromObject(condition), condition.getClass());
		} catch (Exception e) {
//			Log4jUtil.error(AtoAttrSetting.class, e.getMessage(), e);
			e.printStackTrace();
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 获取grpc数据的copy
	 * @param condition
	 * @param commandArgs
	 * @return
	 */
	public void geConditionCopyArgs(V condition, Object commandArgs){
		try {
			lock.lock();
			BeanUtils.copyProperties(condition, commandArgs);
		} catch (Exception e) {
//			Log4jUtil.error(AtoAttrSetting.class, e.getMessage(), e);
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		EnterAlarmMonitoringParam.Builder parm = EnterAlarmMonitoringParam.newBuilder();
//		EnterAlarmMonitoringParam lastparm = parm.setConfirmstate("1")
//		.setId("kk").setOperate("ccccc").build();
//		DeviceMonitorCondition condition = new DeviceMonitorCondition();
//		AtoAttrSetting<EnterAlarmMonitoringParam, DeviceMonitorCondition>
//		   obj = new AtoAttrSetting<EnterAlarmMonitoringParam, DeviceMonitorCondition>();
		/** 第一个方法
		condition = obj.getConditionFromGrpc(lastparm, EnterAlarmMonitoringParam.class, DeviceMonitorCondition.class);
		System.out.println("......................");
		*/
		/** 第二个方法
		condition = obj.getSetConditionFromGrpc(lastparm, AtoAttrSetting.class, "getCondition");
		*/
		
		/** 第三个方法
		condition.setArea("hello");
		condition.setAssetTypes("checkhello");
		condition.setDeviceStatus("deviceStatus");
		DeviceMonitorCondition destcondition = new DeviceMonitorCondition();
		DeviceMonitorCondition lastCondition = obj.copyConditionArgs(condition);
		*/
		
		/** 第四个方法
		condition.setArea("hello");
		condition.setAssetTypes("checkhello");
		condition.setDeviceStatus("deviceStatus");
		DeviceMonitorCondition destcondition = new DeviceMonitorCondition();
		obj.getUtilConditionArgs(condition, destcondition, DeviceMonitorCondition.class);
		*/
		
		/** 第五个方法
		condition.setArea("hello");
		condition.setAssetTypes("checkhello");
		condition.setDeviceStatus("deviceStatus");
		condition = obj.getSetConditionArgs(condition, AtoAttrSetting.class, "getConditionPojo");
		*/
		
		
		System.out.println("................");
		
	}
	
	/*public DeviceMonitorCondition getCondition(EnterAlarmMonitoringParam parm){
		DeviceMonitorCondition condition = new DeviceMonitorCondition();
		condition.setId(parm.getId());
		condition.setOperate(parm.getConfirmstate());
		condition.setConfirmstate(parm.getConfirmstate());
		return condition;
	}
	
	public DeviceMonitorCondition getConditionPojo(DeviceMonitorCondition parm){
		DeviceMonitorCondition condition = new DeviceMonitorCondition();
		condition.setArea(parm.getArea());
		condition.setAssetTypes(parm.getAssetTypes());
		condition.setDeviceStatus(parm.getDeviceStatus());
		return condition;
	}*/

}

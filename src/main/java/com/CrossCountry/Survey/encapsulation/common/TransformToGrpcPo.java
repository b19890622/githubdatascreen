package com.CrossCountry.Survey.encapsulation.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TransformToGrpcPo {

	//获得 grpc 对象
	public static Object convertToPojo(Class classObj, Object source) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = classObj.getMethod("newBuilder", null);
		Object obj = method.invoke(null);
		Method[] methods = source.getClass().getDeclaredMethods();
		String methodName = "";
		String getMethodName = "";
		Method getMethod = null;
		Object getObj = null;
		Object objlast = null;
		String subString = null;
		List arr = null;
		for (int i = 0; i < methods.length; i++) {
			methodName = methods[i].getName();
			if (methodName.startsWith("set")) {
				subString = methodName.substring(3);
				getMethodName = "get" + subString;
				getMethod = source.getClass().getMethod(getMethodName, null);
				if (getMethod == null) {
					continue;
				}
				getObj = getMethod.invoke(source, null);
				if (getObj == null) {
					continue;
				}
				if (getObj instanceof List) {
					arr = (List) getObj;
					method = obj.getClass().getMethod("addAll" + subString, Iterable.class);
					method.invoke(obj, arr);
				} else {
					String returnType = getMethod.getReturnType().getName();
					try {
						method = obj.getClass().getMethod(methodName, getMethod.getReturnType());
						method.invoke(obj, getObj);
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			}
		}
		method = obj.getClass().getMethod("build", null);
		objlast = method.invoke(obj, null);
		return objlast;
	}

	//获得普通对象
	public static Object convertToFromGrpc(Class classObj, Object sourceGrpc) throws Exception {
		Object obj = classObj.newInstance();
		Method[] methods = obj.getClass().getDeclaredMethods();
		String methodName = null;
		Method method = sourceGrpc.getClass().getMethod("toBuilder", null);
		Object objGrpc = method.invoke(sourceGrpc, null);
		String subString = null;
		Method getMethod = null;
		Object getObj = null;
		for(int i=0; i<methods.length; i++){
			methodName = methods[i].getName();
			if (methodName.startsWith("set")) {
				methods[i].setAccessible(true);
				subString = methodName.substring(3);
				try{
					getMethod = objGrpc.getClass().getMethod("get"+subString, null);
				}catch(Exception e){
					continue;
				}
				if(getMethod == null){
					continue;
				}
				getObj = getMethod.invoke(objGrpc, null);
				method = obj.getClass().getMethod(methodName, getMethod.getReturnType());
				method.invoke(obj, getObj);
			}
		}
		return obj;
	}
}

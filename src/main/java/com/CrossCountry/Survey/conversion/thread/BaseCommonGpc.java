package com.CrossCountry.Survey.conversion.thread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.AtoAttrSetting;
import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.CrossCountry.Survey.utils.SpringContextUtil;

/**
 * 获取grpc结果基础基类 后期可能改成抽象
 * @author Administrator
 *
 */
public class BaseCommonGpc {
	
	// 输入逻辑类
	private Object inArgs;
	// 输出处理逻辑类
	private Object outArgs;
	
	//构造函数
	public BaseCommonGpc(Object inArgs, Object outArgs) {
		super();
		this.inArgs = inArgs;
		this.outArgs = outArgs;
	}
	
	private Object getPeerGrpcObjList(Object commandArgs, List<CommonInOutBoundArgs> inArgsList
			, CommonInOutBoundArgs outArgs){
		try {
			Object objCommandArgs = null;
			if(commandArgs instanceof AtoAttrSetting){
				AtoAttrSetting te = (AtoAttrSetting)commandArgs;
				objCommandArgs = te.getConditionCopyArgs(te);
			}else{
				objCommandArgs = commandArgs;
			}
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
					resultObj = method.invoke(obj, objCommandArgs);
				}catch (Exception e){
					if(e instanceof  NoSuchMethodException){
						method = objClass.getMethod(inArgsList.get(i).getMethodName(), null);
						Log4jUtil.error(BaseCommonGpc.class,e.getMessage(),e);
						resultObj = method.invoke(obj);
					}
					Log4jUtil.error(BaseCommonGpc.class,e.getMessage(),e);
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
				return resultObj;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(BaseCommonGpc.class,e.getMessage(),e);
			return null;
		}
	}
	
	private Object getPeerGrpcObj(Object commandArgs, CommonInOutBoundArgs inArgs, 
			CommonInOutBoundArgs outArgs){
		try {
			Object objCommandArgs = null;
			if(commandArgs instanceof AtoAttrSetting){
				AtoAttrSetting te = (AtoAttrSetting)commandArgs;
				objCommandArgs = te.getConditionCopyArgs(te);
			}else{
				objCommandArgs = commandArgs;
			}
			Class name = inArgs.getClassName();
			Object obj = null;
			try {
				obj = SpringContextUtil.getBeanByClass(name);
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(BaseCommonGpc.class,"未找到相应入参处理类"+name.getName(),e);
			}
			if (obj == null) {
			   obj = name.newInstance();
			}
			Method method = name.getMethod(inArgs.getMethodName(), Object.class);
			Object resultObj = method.invoke(obj, objCommandArgs);
			if(outArgs != null){ //获取待加工的数据到grpc对象
				Class classOut = outArgs.getClassName();
				Object outObj = classOut.newInstance();
				Method outMethod = classOut.getMethod(outArgs.getMethodName(), Object.class);
				resultObj = outMethod.invoke(outObj, resultObj);
			}
			return resultObj;
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(BaseCommonGpc.class,e.getMessage(),e);
			return null;
		}
	}
	
	public Object getMainPeerGrpcObj(Object commandArgs){
		Object result = null;
		if(inArgs instanceof List){
			result = getPeerGrpcObjList(commandArgs, (List<CommonInOutBoundArgs>) inArgs
					, (CommonInOutBoundArgs) outArgs);
		}else{
			result = getPeerGrpcObj(commandArgs, (CommonInOutBoundArgs) inArgs
					, (CommonInOutBoundArgs) outArgs);
		}
		return result;
	}
	
	

}

package com.CrossCountry.Survey.conversion.onetoone;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

import com.CrossCountry.Survey.conversion.thread.BaseCommonGpc;
import com.CrossCountry.Survey.encapsulation.common.AtoAttrSetting;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.datascreen.StateGridRequest;

public abstract class BaseOneToOneGrpc<T, V> extends BaseCommonGpc implements OneToOneStreamGrpc<T, V> {

	public BaseOneToOneGrpc(Object inArgs, Object outArgs) {
		super(inArgs, outArgs);
	}

	@Override
	public V getFirstResultGrpcObj(Object request) {
		// TODO Auto-generated method stub
		Object gprcObj = null;
		gprcObj = getMainPeerGrpcObj(request);
		if (gprcObj == null) {
			return null;
		} else {
			return (V) gprcObj;
		}
	}

	@Override
	public Object getRequestPojo(T v) {
		// TODO Auto-generated method stub
		if(v instanceof StateGridRequest) {
			StateGridRequest re = (StateGridRequest)v;
			CommonArgs args = new CommonArgs();
			args.setKey(UUID.randomUUID().toString().replaceAll("-", ""));
			args.setName(re.getName());
			return args;
		}
		return new Object();
	}

	@Override
	public boolean getFirstResultGrpcFlag() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getCopyRequestPojo(T v, Object commandsArgs) throws Exception {
		// TODO Auto-generated method stub
		Object obj = getRequestPojo(v);
		if (obj == null) {
			return null;
		}
		if (obj instanceof AtoAttrSetting) {
			if(commandsArgs == null){
				commandsArgs = obj.getClass().newInstance();
			}
			AtoAttrSetting te = (AtoAttrSetting) obj;
		    te.geConditionCopyArgs(commandsArgs, obj);
			return commandsArgs;
		}else{
			if(obj instanceof String){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof Byte){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof Short){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof Long){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof Integer){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof BigDecimal){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof Float){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof Double){
				commandsArgs = obj;
				return commandsArgs;
			}else if(obj instanceof BigInteger){
				commandsArgs = obj;
				return commandsArgs;
			}
			if(commandsArgs == null){
				commandsArgs = obj.getClass().newInstance();
			}
			BeanUtils.copyProperties(commandsArgs, obj);
			return commandsArgs;
		}
	}

}

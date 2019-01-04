package com.CrossCountry.Survey.conversion.thread;

import java.lang.reflect.Constructor;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.encapsulation.common.CommonInOutBoundArgs;

@Component
public class KafakaLoseAbTools implements KafkaAbTools {

	@Autowired
	CommonScheduledPool commonScheduledPool;
	

	@Override
	public boolean freeScheduledThread(ScheduledFuture future) {
		// TODO Auto-generated method stub
		if(future != null){
			return future.cancel(true);
		}
		return true;
	}


	
	@Override
	public ScheduledFuture dealWithOneToOneLoseMsg(CommonInOutBoundArgs inArgs, Object commandArgs, Queue<Boolean> queue,
			Object response, CommonInOutBoundArgs outArgs) throws Exception {
		// TODO Auto-generated method stub
		Constructor consor = null;
		consor = CommonNoStaticThread.class.getConstructor(Object.class, Deque.class,
				Object.class, CommonInOutBoundArgs.class, CommonInOutBoundArgs.class);
		Object obj = consor.newInstance(response, queue, commandArgs, inArgs, outArgs);
		return commonScheduledPool.executeRunning((Runnable) obj);
	}

	@Override
	public ScheduledFuture dealWithOneToOneLoseMsg(List<CommonInOutBoundArgs> inArgs, Object commandArgs,
			Queue<Boolean> queue, Object response, CommonInOutBoundArgs outArgs) throws Exception {
		// TODO Auto-generated method stub
		Constructor consor = null;
		consor = CommonMoreNoStaticThread.class.getConstructor(Object.class, Deque.class,
				Object.class, List.class, CommonInOutBoundArgs.class);
		Object obj = consor.newInstance(response, queue, commandArgs, inArgs, outArgs);
		return commonScheduledPool.executeRunning((Runnable) obj);
	}

	@Override
	public ScheduledFuture dealWithOneToOneLoseMsg(CommonInOutBoundArgs inArgs, Object commandArgs,
			Object response, CommonInOutBoundArgs outArgs, long timeInterval, long lazyintal) throws Exception {
		// TODO Auto-generated method stub
		Constructor consor = null;
		consor = CommonNoStaticPollingThread.class.getConstructor(Object.class, Object.class, 
				CommonInOutBoundArgs.class, CommonInOutBoundArgs.class);
		Object obj = consor.newInstance(response, commandArgs, inArgs, outArgs);
		return commonScheduledPool.executeRunning((Runnable) obj, timeInterval, lazyintal);
	}

	@Override
	public ScheduledFuture dealWithOneToOneLoseMsg(List<CommonInOutBoundArgs> inArgs, Object commandArgs,
			Object response, CommonInOutBoundArgs outArgs, long timeInterval, long lazyintal) throws Exception {
		// TODO Auto-generated method stub
		Constructor consor = null;
		consor = CommonMoreNoStaticPollThread.class.getConstructor(Object.class, Object.class, 
				List.class, CommonInOutBoundArgs.class);
		Object obj = consor.newInstance(response, commandArgs, inArgs, outArgs);
		return commonScheduledPool.executeRunning((Runnable) obj, timeInterval, lazyintal);
	}



	@Override
	public ScheduledFuture dealWithOneToOneLoseMsg(Class className, String methodName, Object commandArgs,
			Queue<Boolean> queue, Object response, String methodGrpc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledFuture dealWithMoreServiceOneToOneLoseMsg(List<Class> className, List<String> methodName,
			Object commandArgs, Queue<Boolean> queue, Object response, String methodGrpc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}

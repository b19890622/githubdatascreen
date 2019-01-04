package com.CrossCountry.Survey.conversion.thread;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.encapsulation.common.CommonConfig;

@Component
public class CommonScheduledPool{

	@Autowired
	ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
	
	@Autowired
	CommonConfig commonConfig;
	
	public ScheduledFuture executeRunning(Object runnable){
		ScheduledFuture future = scheduledThreadPoolExecutor.scheduleWithFixedDelay((Runnable)runnable, 0,
				commonConfig.intervaltime, TimeUnit.MILLISECONDS);
		return future;
	}
	
	/**
	 * 设置动态时间间隔执行
	 * @param runnable 
	 * @param timeInterval 时间间隔
	 * @return
	 */
	public ScheduledFuture executeRunning(Object runnable, long timeInterval, long lazyInterval){
		ScheduledFuture future = scheduledThreadPoolExecutor.scheduleWithFixedDelay((Runnable)runnable, lazyInterval,
				timeInterval, TimeUnit.MILLISECONDS);
		return future;
	}
}

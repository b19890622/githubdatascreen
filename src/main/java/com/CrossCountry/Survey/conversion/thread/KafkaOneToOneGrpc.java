package com.CrossCountry.Survey.conversion.thread;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public interface KafkaOneToOneGrpc<T> {

	public Deque<T> getQeque(Map map);
	
	public void dealErrorORComplementMsg(ScheduledFuture future);
	
	
	
	
}

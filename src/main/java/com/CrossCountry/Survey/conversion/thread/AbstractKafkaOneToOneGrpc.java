package com.CrossCountry.Survey.conversion.thread;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ScheduledFuture;

public abstract class AbstractKafkaOneToOneGrpc implements KafkaOneToOneGrpc<Boolean>{
	
    private String key;
    
    private Map map;
    
    public Deque<Boolean> deque;
    
	private KafkaAbTools kafkaAbTools;
    
	public AbstractKafkaOneToOneGrpc(String key, Map map, KafkaAbTools kafkaAbTools) {
		super();
		this.key = key;
		this.map = map;
		this.deque = getQeque(map);
		this.kafkaAbTools = kafkaAbTools;
		
	}

	@Override
	public Deque<Boolean> getQeque(Map map) {
		// TODO Auto-generated method stub
		ConcurrentLinkedDeque<Boolean> deque = new ConcurrentLinkedDeque<Boolean>();
		map.put(key, deque);
        return deque;
	}
	
	public abstract void freeThread(ScheduledFuture future);
	
	
	public void dealErrorORComplementMsg(ScheduledFuture future){
		map.remove(key);
		kafkaAbTools.freeScheduledThread(future);
	}

}

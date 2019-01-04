package com.CrossCountry.Survey.conversion.thread;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class DefaultKafkaOneToOneGrpc extends AbstractKafkaOneToOneGrpc{
	
	private KafkaAbTools kafkaAbTools;
	
	public DefaultKafkaOneToOneGrpc(String key, Map map, KafkaAbTools kafkaAbTools) {
		super(key, map, kafkaAbTools);
		// TODO Auto-generated constructor stub
		this.kafkaAbTools = kafkaAbTools;
	}

	@Override
	public void freeThread(ScheduledFuture future) {
		// TODO Auto-generated method stub
		super.dealErrorORComplementMsg(future);
	}

	public KafkaAbTools getKafkaAbTools() {
		return kafkaAbTools;
	}

	public void setKafkaAbTools(KafkaAbTools kafkaAbTools) {
		this.kafkaAbTools = kafkaAbTools;
	}
	
	
	
}

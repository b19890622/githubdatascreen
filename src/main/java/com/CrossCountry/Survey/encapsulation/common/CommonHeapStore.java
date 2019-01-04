package com.CrossCountry.Survey.encapsulation.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonHeapStore {
	
	public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

}

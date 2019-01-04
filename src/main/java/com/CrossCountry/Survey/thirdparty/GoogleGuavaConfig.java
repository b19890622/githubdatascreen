package com.CrossCountry.Survey.thirdparty;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.CrossCountry.Survey.utils.Log4jUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;

@Configuration
@EnableCaching
@Primary
public class GoogleGuavaConfig {
	@Bean
	public CacheManager cacheManager(){
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(CacheBuilder.newBuilder()
				.concurrencyLevel(8)
				.expireAfterWrite(5, TimeUnit.MINUTES)
				.expireAfterAccess(5, TimeUnit.MINUTES)
				.initialCapacity(10)
				.maximumSize(5000)
				.removalListener(RemovalListeners
						.asynchronous(new RemovalListener<Object, Object>(){
							@Override
							public void onRemoval(RemovalNotification<Object, Object> notification) {
								Log4jUtil.error(GoogleGuavaConfig.class, "INVALID EXPIRE HAPPENING: key:"
								+ notification.getKey().toString() + " corresponding " +  notification.getValue()+" !!");
							}
						}, Executors.newFixedThreadPool(1)))
				);
		return cacheManager;
	}
	
}

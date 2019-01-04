package com.CrossCountry.Survey.thirdparty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class GoogleCacheUtils {
	
	@Autowired
	CacheManager cacheManager;
	
	public void putCacheValue(String zone, String key, Object value){
		cacheManager.getCache(zone).put(key, value);
	}
	
	public Object getCacheValue(String zone, String key){
		try{
			return cacheManager.getCache(zone).get(key).get();
		}catch(Exception e){
			return null;
		}
	}
	
	public void putCacheValue(String key, Object value){
		cacheManager.getCache("datasreen").put(key, value);
	}
	
	public Object getCacheValue(String key){
		try{
			return cacheManager.getCache("datasreen").get(key).get();
		}catch(Exception e){
			return null;
		}
		
	}
	
	

}

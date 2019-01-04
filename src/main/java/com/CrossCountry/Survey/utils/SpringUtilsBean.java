package com.CrossCountry.Survey.utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtilsBean {
	
	@Bean
	ScheduledThreadPoolExecutor getThreadPoolExecutor(){
		return new ScheduledThreadPoolExecutor(30);
	}

}

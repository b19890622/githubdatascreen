package com.CrossCountry.Survey.encapsulation.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonConfig {

	@Value("${screen_interval_time}")
	public int intervaltime;
	

	public int getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(int intervaltime) {
		this.intervaltime = intervaltime;
	}

}

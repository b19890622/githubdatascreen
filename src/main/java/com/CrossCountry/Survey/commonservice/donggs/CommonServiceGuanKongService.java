package com.CrossCountry.Survey.commonservice.donggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.GaoJingDao;
import com.CrossCountry.Survey.modelvo.donggs.GuangKongNumber;
@Component
public class CommonServiceGuanKongService {
	@Autowired
	private GaoJingDao gaoJingDao;
	
	public GuangKongNumber getNumber( Object obj){
		return gaoJingDao.getNumber();
	}
	
}

package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.ProtectionNumberDao;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
@Component
public class CommonServiceProtectionNumberService {
	@Autowired
	private ProtectionNumberDao protectionNumberDao;
	
	public Mpcvf getProtection( Object obj){
		return protectionNumberDao.getProtection();
	}
	
}

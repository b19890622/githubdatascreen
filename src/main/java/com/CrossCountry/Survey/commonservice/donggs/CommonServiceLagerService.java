package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.LagerDao;
import com.CrossCountry.Survey.modelvo.donggs.Largen;
@Component
public class CommonServiceLagerService {
	@Autowired
	private LagerDao lagerDao;
	
	public List<Largen>  getMu( Object obj){
	
		return lagerDao.getMu();
	}
	
}

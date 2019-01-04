package com.CrossCountry.Survey.commonservice.donggs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.VisitTimesCommonDao;
import com.CrossCountry.Survey.modelvo.donggs.VisitTimesPo;

@Component
public class VisitTimesCommonService {

	@Autowired
	private VisitTimesCommonDao visitTimesCommonDao;
	

	public List<VisitTimesPo> getVisitTimes(Object object) {
	
				
		List<VisitTimesPo> secretTopTenEntitys = visitTimesCommonDao.getVisitTimesCommon();
		List<VisitTimesPo> secretTopTenEntityNews = new ArrayList<>();
		int a2 = 0 ;
		int b3 = 0 ;
		     for (VisitTimesPo visitTimesPo : secretTopTenEntitys) {
		    	 
				if("2".equals(visitTimesPo.getType())) {
					visitTimesPo.setType("服务器");
					 if (a2 < 7) {
			    		 a2++;
			    		 secretTopTenEntityNews.add(visitTimesPo);
			    		 }
				}else if ("3".equals(visitTimesPo.getType())) {
					visitTimesPo.setType("工作站");
					 if (b3 < 7) {
			    		 b3++;
			    		 secretTopTenEntityNews.add(visitTimesPo);
			    		 }
				}
			}
		     
		    	 
		return secretTopTenEntityNews;
	}

}

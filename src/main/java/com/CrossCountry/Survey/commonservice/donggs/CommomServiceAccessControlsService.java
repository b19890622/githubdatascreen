package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.AccessControlsDao;
import com.CrossCountry.Survey.modelvo.donggs.AccessControlsPo;
import com.CrossCountry.Survey.modelvo.donggs.VisitTimesPo;

@Component
public class CommomServiceAccessControlsService {

	@Autowired
	private AccessControlsDao accessControlsDao;
	

	public List<AccessControlsPo> getAccessControlsPo(Object object) {
	    int localityNum = 0; //本地访问数量 
	    int networkNum = 0; //网络访问数量 
	    int accreditNum = 0; //未授权访问数量 
	    int interdictNum = 0; //阻断访问数量 
		List<AccessControlsPo> accessControlss = accessControlsDao.getAccessControlsPo();
		for (AccessControlsPo accessControlsPo : accessControlss) {
			if ("本地访问".equals(accessControlsPo.getName())) {
				localityNum = accessControlsPo . getNum();
			}else if  ("网络访问".equals(accessControlsPo.getName())) {
				networkNum = accessControlsPo . getNum();
		}else if  ("未授权访问".equals(accessControlsPo.getName())) {
			accreditNum = accessControlsPo . getNum();
	}else if  ("阻断访问".equals(accessControlsPo.getName())) {
		interdictNum = accessControlsPo . getNum();
}
		
	}
		AccessControlsPo AccessControlsPo1 = new AccessControlsPo();
		AccessControlsPo1.setName("访问总数");
		AccessControlsPo1.setNum(localityNum+networkNum);
		accessControlss.add(AccessControlsPo1);
		
		AccessControlsPo AccessControlsPo2 = new AccessControlsPo();
		AccessControlsPo2.setName("授权访问");
		AccessControlsPo2.setNum(localityNum + networkNum - accreditNum );
		accessControlss.add(AccessControlsPo2);
		
		return accessControlss;

}
}

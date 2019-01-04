package com.CrossCountry.Survey.commonservice.donggs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.donggs.ComplianceNameAndNumDao;
import com.CrossCountry.Survey.mapper.donggs.ProtectionNumberDao;
import com.CrossCountry.Survey.modelvo.donggs.CompliancePo;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
@Component
public class CommonServiceComplianceNameAndNumService {
	@Autowired
	private ComplianceNameAndNumDao complianceNameAndNumDao;
	
	public List<CompliancePo> getComplianceNameAndNum( Object obj){
		return complianceNameAndNumDao.getComplianceNameAndNum();
	}
	
}

package com.CrossCountry.Survey.commonservice.dongl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.SecretTopTenCommonDao;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTen;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTenEntity;

@Component
public class SecretTopTenCommonService {

	@Autowired
	private SecretTopTenCommonDao secretTopTenCommonDao;
	

	public List<SecretTopTenEntity> getSecretTopTenEntity(Object object) {
	List<SecretTopTenEntity> secretTopTenEntityss = new ArrayList<>();
			
		 String name = "" ;//  名字	
	     double dectotal =0 ;//代表明文流量
	     double enctotal = 0 ;//代表密文流量
		 int encintnum = 0;//中断次数
		List<SecretTopTen> secretTopTenEntitys = secretTopTenCommonDao.getSecretTopTen();
		for (SecretTopTen secretTopTen : secretTopTenEntitys) {
			SecretTopTenEntity secretTopTenEntity = new SecretTopTenEntity();
			String abc = "";
			if (secretTopTen.getName().contains("市调")) {
				
				 abc = secretTopTen.getName().replace("市调", "");
			}else if (secretTopTen.getName().contains("省调")) {
				 abc = secretTopTen.getName().replace("省调", "");
			}else if (secretTopTen.getName().contains("区调")) {
				 abc = secretTopTen.getName().replace("区调", "");
			}else if (secretTopTen.getName().contains("分中心")) {
				 abc = secretTopTen.getName().replace("分中心", "");
			
			}
			secretTopTenEntity.setUnit(abc);
			enctotal =Double.parseDouble(secretTopTen.getEnctotal());
			dectotal =Double.parseDouble(secretTopTen.getDectotal());
			String secretPassRate = "0";
			DecimalFormat df = new DecimalFormat("0.00"); 
			
			if ((enctotal+dectotal) != 0) {
				
				 secretPassRate =df.format(( enctotal/(enctotal+dectotal)*100)) + "";
				
			}
			secretTopTenEntity.setSecretPassRate(secretPassRate);
			secretTopTenEntity.setOnlineRate(df.format(Double.parseDouble(secretTopTen.getAllname())) + "");
			secretTopTenEntity.setBreakNum(secretTopTen.getEncintnum() + "");
			secretTopTenEntityss.add(secretTopTenEntity);
		}

		Collections.sort(secretTopTenEntityss,new Comparator<SecretTopTenEntity>() {

			@Override
			public int compare(SecretTopTenEntity o1, SecretTopTenEntity o2) {
				 if (Double.parseDouble(o1.getSecretPassRate())> Double.parseDouble(o2.getSecretPassRate())) {  
	                    return -1;  
	                }  
	                if (Double.parseDouble(o1.getSecretPassRate())== Double.parseDouble(o2.getSecretPassRate())) {  
	                    return 0;  
	                }  
	                return 1;  
	            }  
		});
		
	for (int i = 0; i < secretTopTenEntityss.size(); i++) {
		SecretTopTenEntity secretTopTenEntity2 = secretTopTenEntityss.get(i);
		secretTopTenEntity2.setRank((i+1)+"");
	}
		return secretTopTenEntityss;
	}

}

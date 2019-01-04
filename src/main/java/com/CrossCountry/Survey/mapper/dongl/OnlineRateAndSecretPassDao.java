package com.CrossCountry.Survey.mapper.dongl;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.dongl.SecretPassRateEntity;
import com.CrossCountry.Survey.modelvo.dongl.OnlineRateEntity;

@Mapper
public interface OnlineRateAndSecretPassDao {
	
	public SecretPassRateEntity getSecretPassRateEntity();
	
	public OnlineRateEntity getOnlineRateEntity();
	
}

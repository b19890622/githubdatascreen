package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.AccessControlsPo;
import com.CrossCountry.Survey.modelvo.donggs.VisitTimesPo;
import com.CrossCountry.Survey.modelvo.dongl.SecretTopTen;


@Mapper
public interface AccessControlsDao {
	
	public List<AccessControlsPo> getAccessControlsPo();
	
}

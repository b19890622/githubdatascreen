package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.dongl.NumOneAreaLeftEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumThreeAreaLeftEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumTwoAreaLeftEntity;


@Mapper
public interface LeftSecurityFlawDao {
	public List<NumOneAreaLeftEntity> getNumOneAreaLeftList();
	
	public List<NumTwoAreaLeftEntity> getNumTwoAreaLeftList();
	
	public List<NumThreeAreaLeftEntity> getThreeAreaLeftList();
	
	
}

package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.dongl.NumOneAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumThreeAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumTwoAreaRightEntity;


@Mapper
public interface RightSecurityFlawDao {
	public List<NumOneAreaRightEntity> getNumOneAreaRightList();
	
	public List<NumTwoAreaRightEntity> getNumTwoAreaRightList();
	
	public List<NumThreeAreaRightEntity> getThreeAreaRightList();
	
	
}

package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.Largen;

@Mapper
public interface LagerDao {
	
	public List<Largen> getMu();
	
}

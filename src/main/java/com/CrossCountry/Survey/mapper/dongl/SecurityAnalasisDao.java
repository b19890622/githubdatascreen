package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.dongl.SecurityAnalasisEntity;


@Mapper
public interface SecurityAnalasisDao {
	
	public List<SecurityAnalasisEntity> getSecurityAnalasis(@Param("curstart")String curstart,@Param("curend")String curend);
}

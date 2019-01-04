package com.CrossCountry.Survey.mapper.dongl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.dongl.InputAreaEntity;

@Mapper
public interface InputCommonDao {

	public List<InputAreaEntity> getInputAreaEntity();

}

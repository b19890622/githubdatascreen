package com.CrossCountry.Survey.mapper.donggs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.CrossCountry.Survey.modelvo.donggs.AlarmNameAndNumber;
import com.CrossCountry.Survey.modelvo.donggs.PropertyDataAndOftenData;
import com.CrossCountry.Survey.modelvo.donggs.PropertyInDeviceReplyEntity;
@Mapper
public interface SecureTopologyDao {
	public List<AlarmNameAndNumber> getAlarmNameAndNumber();
	public  List<PropertyDataAndOftenData>  getPropertyDataAndOftenData();
	public  List<PropertyInDeviceReplyEntity>  getPropertyInDeviceReplyEntity();
}

package com.CrossCountry.Survey.mapper.shichf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.shichf.OnlineRateEntity;
import com.CrossCountry.Survey.modelvo.shichf.RealtimeStatisticsAllEntity;
import com.CrossCountry.Survey.modelvo.shichf.SecretPassRate;

@Mapper
public interface ScreenSecretPassRateDao {
	public List<SecretPassRate> getSecretPassRate(@Param("name") String name);

	public List<OnlineRateEntity> getOnlineRateEntityList(@Param("name") String name);

	public Integer getUrgentWarningsLocalCount();

	public Integer getImportantWarningsLocalCount();

	public Integer getUrgentWarningsLowCount(@Param("name") String name);

	public Integer getImportantWarningsLowCount(@Param("name") String name);

	public String getSecrityDays();

	public RealtimeStatisticsAllEntity getRealtimeStatisticsAllEntity(@Param("beginDate") String beginDate,
			@Param("endDate") String endDate);

	public List<RealtimeStatisticsAllEntity> getRealtimeStatisticsAllEntityByName(@Param("name") String name);

	public List<String> getRealtimeStatisticsAllListLowByName(@Param("name") String name);

	public RealtimeStatisticsAllEntity getRealtimeStatisticsAllEntityLowByName(@Param("beginDate") String beginDate,
			@Param("endDate") String endDate, @Param("name") String name);

	public List<RealtimeStatisticsAllEntity> getRealtimeStatisticsAllEntityByOnlinesql(
			@Param("onlinesql") String onlinesql);

	public List<RealtimeStatisticsAllEntity> getRealtimeStatisticsAllListByStime(@Param("name") String name);

	public List<RealtimeStatisticsAllEntity> getRealtimeStatisticsAllCenter();
}

package com.CrossCountry.Survey.mapper.donggs;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.CrossCountry.Survey.modelvo.donggs.Amount;
import com.CrossCountry.Survey.modelvo.donggs.ChangeRed;
import com.CrossCountry.Survey.modelvo.donggs.CircleRed;
import com.CrossCountry.Survey.modelvo.donggs.Largen;
import com.CrossCountry.Survey.modelvo.donggs.Power;
import com.CrossCountry.Survey.modelvo.donggs.RedWire;
@Mapper
public interface MenaceMonitorDao {
  public  List<Amount> getAmount();
  public  List<CircleRed> getCircleRed(@Param("oldDateTime")Date oldDateTime,@Param("newDateTime")Date newDateTime);
  public  List<ChangeRed> getChangeRed();
  public  List<Largen> getLargen();
  public  List<Power> getPower();
  public List<RedWire> getRedWire();
}

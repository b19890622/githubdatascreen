package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.VerificationCasePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VerificationCaseDao {

    public List<VerificationCasePo> getVerificationCase();

}

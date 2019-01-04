package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.UnitsVerificationPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UnitsVerificationDao {

    public List<UnitsVerificationPo> getUnitsVerification(@Param("name") String name);

}

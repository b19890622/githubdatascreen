package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.NetSecurityVerificaPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NetSecurityVerificaDao {

    public NetSecurityVerificaPo getNetSecurityVerifica();

}

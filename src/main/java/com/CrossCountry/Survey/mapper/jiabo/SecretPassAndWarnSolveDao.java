package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.SecretPassAndWarnSolvePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecretPassAndWarnSolveDao {

    public List<SecretPassAndWarnSolvePo> getSecretPassAndWarnSolve();

}

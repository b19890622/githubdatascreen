package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.SecretPassAndWarnSolveDao;
import com.CrossCountry.Survey.modelvo.jiabo.CircularEntity;
import com.CrossCountry.Survey.modelvo.jiabo.HistogramEntity;
import com.CrossCountry.Survey.modelvo.jiabo.SecretPassAndWarnSolvePo;
import com.CrossCountry.Survey.modelvo.jiabo.SecretPassAndWarnSolveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class SecretPassAndWarnSolveCommonService {

    @Autowired
    private SecretPassAndWarnSolveDao secretPassAndWarnSolveDao;

    public SecretPassAndWarnSolveVO getSecretPassAndWarnSolve(Object object) {
        SecretPassAndWarnSolveVO secretPassAndWarnSolveVO = new SecretPassAndWarnSolveVO();
        try {
            List<SecretPassAndWarnSolvePo> secretPassAndWarnSolvePo = secretPassAndWarnSolveDao.getSecretPassAndWarnSolve();
            List<CircularEntity> circularPoList = new ArrayList<CircularEntity>();
            List<HistogramEntity> histogramPoList = new ArrayList<HistogramEntity>();
            for(SecretPassAndWarnSolvePo po : secretPassAndWarnSolvePo){
                CircularEntity circularEntity = new CircularEntity();
                HistogramEntity histogramEntity = new HistogramEntity();
                String name = po.getName();//名称
                int unsolved = po.getUnsolved();//待解决
                int resolved = po.getResolved();//已解决
                String secretPass = po.getSecretPass();//密通率
                String online = po.getOnline();//在线率
                if(name.contains("分中心")){
                    circularEntity.setName(name.replace("分中心",""));
                    circularEntity.setWarningNumber(unsolved + resolved);
                    circularEntity.setSecretPass(secretPass);
                    circularEntity.setOnline(online);
                    circularEntity.setResolved(resolved);
                    circularPoList.add(circularEntity);
                }else{
                    String nameReplace = name.replace("省调","").replace("市调","")
                            .replace("地调","").replace("中调","").replace("区调","")
                            .replace("网调","");
                    histogramEntity.setName(nameReplace);
                    histogramEntity.setUnsolved(unsolved);
                    histogramEntity.setResolved(resolved);
                    histogramEntity.setSecretPass(secretPass);
                    histogramEntity.setOnline(online);
                    histogramPoList.add(histogramEntity);
                }
            }
            secretPassAndWarnSolveVO.setCircularPoList(circularPoList);
            secretPassAndWarnSolveVO.setHistogramPoList(histogramPoList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(SecretPassAndWarnSolveCommonService.class, e.getMessage(), e);
        }
        return secretPassAndWarnSolveVO;
    }
}

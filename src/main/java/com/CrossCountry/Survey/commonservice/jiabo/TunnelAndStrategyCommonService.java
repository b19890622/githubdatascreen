package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.TunnelAndStrategyDao;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class TunnelAndStrategyCommonService {

    @Autowired
    private TunnelAndStrategyDao tunnelAndStrategyDao;

    public TunnelAndStrategyVO getTunnelAndStrategy(Object object) {
        TunnelAndStrategyVO tunnelAndStrategyVO = new TunnelAndStrategyVO();
        try {
            List<TunnelAndStrategyPo> tunnelAndStrategyPo = tunnelAndStrategyDao.getTunnelAndStrategy();
            List<TunnelAndStrategyPo> tunnelAndStrategyList = new ArrayList<TunnelAndStrategyPo>();
            for(TunnelAndStrategyPo po : tunnelAndStrategyPo){
                TunnelAndStrategyPo TunnelAndStrategy = new TunnelAndStrategyPo();
                String name = po.getName();//名称
                int equipVerified = po.getEquipVerified();
                String propStrategyProblems = po.getPropStrategyProblems();//次数
                String propTunnelProblems = po.getPropTunnelProblems();
                int problemsNumber = po.getProblemsNumber();
                String nameReplace = name.replace("分中心","").replace("省调","").replace("市调","")
                        .replace("地调","").replace("中调","").replace("区调","").replace("网调","");
                TunnelAndStrategy.setName(nameReplace);
                TunnelAndStrategy.setEquipVerified(equipVerified);
                TunnelAndStrategy.setPropStrategyProblems(propStrategyProblems);
                TunnelAndStrategy.setPropTunnelProblems(propTunnelProblems);
                TunnelAndStrategy.setProblemsNumber(problemsNumber);
                tunnelAndStrategyList.add(TunnelAndStrategy);
            }
            tunnelAndStrategyVO.setTunnelAndStrategyPoList(tunnelAndStrategyList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(TunnelAndStrategyCommonService.class, e.getMessage(), e);
        }
        return tunnelAndStrategyVO;
    }
}

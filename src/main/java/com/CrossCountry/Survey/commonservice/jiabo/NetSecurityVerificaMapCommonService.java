package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.NetSecurityVerificaMapDao;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class NetSecurityVerificaMapCommonService {

    @Autowired
    private NetSecurityVerificaMapDao netSecurityVerificaMapDao;

    public NetSecurityVerificaMapVO getNetSecurityVerificaMap(Object object) {
        NetSecurityVerificaMapVO netSecurityVerificaMapVO = new NetSecurityVerificaMapVO();
        try {
            List<NetSecurityVerificaMapPo> netSecurityVerificaMapPo = netSecurityVerificaMapDao.getNetSecurityVerificaMap();
            List<NetSecurityVerificaMapPo> verificaMapCenterList = new ArrayList<NetSecurityVerificaMapPo>();
            List<NetSecurityVerificaMapPo> verificaMapProvinceList = new ArrayList<NetSecurityVerificaMapPo>();
            for(NetSecurityVerificaMapPo po : netSecurityVerificaMapPo){
                NetSecurityVerificaMapPo verificaMapCenter = new NetSecurityVerificaMapPo();
                NetSecurityVerificaMapPo verificaMapProvince = new NetSecurityVerificaMapPo();
                String name = po.getName();//名称
                int noCompliance = po.getNoCompliance();//不合规数
                int leakage = po.getLeakage();//漏洞数
                if(name.contains("分中心")){
                    verificaMapCenter.setName(name.replace("分中心",""));
                    verificaMapCenter.setNoCompliance(noCompliance);
                    verificaMapCenter.setLeakage(leakage);
                    verificaMapCenterList.add(verificaMapCenter);
                }else{
                    String nameReplace = name.replace("省调","").replace("市调","")
                            .replace("地调","").replace("中调","").replace("区调","")
                            .replace("网调","");
                    verificaMapProvince.setName(nameReplace);
                    verificaMapProvince.setNoCompliance(noCompliance);
                    verificaMapProvince.setLeakage(leakage);
                    verificaMapProvinceList.add(verificaMapProvince);
                }
            }
            netSecurityVerificaMapVO.setVerificaMapCenterList(verificaMapCenterList);
            netSecurityVerificaMapVO.setVerificaMapProvinceList(verificaMapProvinceList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(NetSecurityVerificaMapCommonService.class, e.getMessage(), e);
        }
        return netSecurityVerificaMapVO;
    }
}

package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class NetSecurityVerificaMapCommonServiceToGrpc {
    public NetSecurityVerificaMapReply getNetSecurityVerificaMap(Object object) {
        NetSecurityVerificaMapReply.Builder response = NetSecurityVerificaMapReply.newBuilder();
        NetSecurityVerificaMapVO netSecurityVerificaMapVO = (NetSecurityVerificaMapVO) object;
        try {
            List<NetSecurityVerificaMap> netSecurityVerificaMapCenterList = new ArrayList<NetSecurityVerificaMap>();
            List<NetSecurityVerificaMap> netSecurityVerificaMapProvinceList = new ArrayList<NetSecurityVerificaMap>();
            for(NetSecurityVerificaMapPo nc : netSecurityVerificaMapVO.getVerificaMapCenterList()){
                NetSecurityVerificaMap netSecurityVerificaMapCenter = (NetSecurityVerificaMap) TransformToGrpcPo.convertToPojo(NetSecurityVerificaMap.class,nc);
                netSecurityVerificaMapCenterList.add(netSecurityVerificaMapCenter);
            }
            for(NetSecurityVerificaMapPo np : netSecurityVerificaMapVO.getVerificaMapProvinceList()){
                NetSecurityVerificaMap netSecurityVerificaMapProvince = (NetSecurityVerificaMap) TransformToGrpcPo.convertToPojo(NetSecurityVerificaMap.class,np);
                netSecurityVerificaMapProvinceList.add(netSecurityVerificaMapProvince);
            }
            response.addAllNetSecurityVerificaMapCenter(netSecurityVerificaMapCenterList).addAllNetSecurityVerificaMapProvince(netSecurityVerificaMapProvinceList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(NetSecurityVerificaMapCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

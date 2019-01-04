package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class TunnelAndStrategyCommonServiceToGrpc {
    public TunnelAndStrategyReply getTunnelAndStrategy(Object object) {
        TunnelAndStrategyReply.Builder response = TunnelAndStrategyReply.newBuilder();
        TunnelAndStrategyVO tunnelAndStrategyVO = (TunnelAndStrategyVO) object;
        try {
            List<TunnelAndStrategy> tunnelAndStrategyList = new ArrayList<TunnelAndStrategy>();
            for(TunnelAndStrategyPo tp : tunnelAndStrategyVO.getTunnelAndStrategyPoList()){
                TunnelAndStrategy tunnelAndStrategy = (TunnelAndStrategy) TransformToGrpcPo.convertToPojo(TunnelAndStrategy.class,tp);
                tunnelAndStrategyList.add(tunnelAndStrategy);
            }
            response.addAllTunnelAndStrategy(tunnelAndStrategyList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(TunnelAndStrategyCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

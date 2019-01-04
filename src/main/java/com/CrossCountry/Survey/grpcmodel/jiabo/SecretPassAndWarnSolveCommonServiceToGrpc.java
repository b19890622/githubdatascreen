package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.CircularEntity;
import com.CrossCountry.Survey.modelvo.jiabo.HistogramEntity;
import com.CrossCountry.Survey.modelvo.jiabo.SecretPassAndWarnSolveVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class SecretPassAndWarnSolveCommonServiceToGrpc {
    public SecretPassAndWarnSolveReply getSecretPassAndWarnSolve(Object object) {
        SecretPassAndWarnSolveReply.Builder response = SecretPassAndWarnSolveReply.newBuilder();
        SecretPassAndWarnSolveVO secretPassAndWarnSolveVO = (SecretPassAndWarnSolveVO) object;
        try {
            List<Circular> circularList = new ArrayList<Circular>();
            List<Histogram> histogramList = new ArrayList<Histogram>();
            for(CircularEntity c : secretPassAndWarnSolveVO.getCircularPoList()){
                Circular circular = (Circular) TransformToGrpcPo.convertToPojo(Circular.class,c);
                circularList.add(circular);
            }
            for(HistogramEntity h : secretPassAndWarnSolveVO.getHistogramPoList()){
                Histogram histogram = (Histogram) TransformToGrpcPo.convertToPojo(Histogram.class,h);
                histogramList.add(histogram);
            }
            response.addAllCircular(circularList).addAllHistogram(histogramList);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(SecretPassAndWarnSolveCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}

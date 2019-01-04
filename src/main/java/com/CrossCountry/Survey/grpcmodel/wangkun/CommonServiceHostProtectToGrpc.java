package com.CrossCountry.Survey.grpcmodel.wangkun;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.wangkun.AccessListVO;
import com.CrossCountry.Survey.modelvo.wangkun.AccessVO;
import com.CrossCountry.Survey.modelvo.wangkun.HighRiskVO;
import com.CrossCountry.Survey.modelvo.wangkun.ResultMsgVO;
import com.CrossCountry.Survey.modelvo.wangkun.ResultVO;
import com.datascreen.*;

/**
 * @auther wangkun
 * @date 2018/12/30 13:44
 */
public class CommonServiceHostProtectToGrpc {
    public AccessMonitoringReplay getAccessMonitoringResponse(Object object){
        AccessMonitoringReplay.Builder builder = AccessMonitoringReplay.newBuilder();
        AccessListVO accessListVO = (AccessListVO) object;
        List<AccessMonitoring> accessMonitoringList = new ArrayList<>();
        List<AccessVO> accessVOList = accessListVO.getAccessVOS();
        try {
            for(AccessVO accessVO:accessVOList){
                AccessMonitoring accessMonitoring = (AccessMonitoring) TransformToGrpcPo.convertToPojo(AccessMonitoring.class,accessVO);
                accessMonitoringList.add(accessMonitoring);
            }
            builder.setCdRomNum(accessListVO.getCdRomNum());
            builder.setParallelNum(accessListVO.getParallelNum());
            builder.setSerialNum(accessListVO.getSerialNum());
            builder.setUsbNum(accessListVO.getUsbNum());
            builder.addAllMonitor(accessMonitoringList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.build();
    }
    public HighRiskSurveillanceReply getHighRiskSurveillanceResponse(Object object){
        HighRiskSurveillanceReply.Builder builder = HighRiskSurveillanceReply.newBuilder();
        List<HighRiskVO> highRiskVOList = (List<HighRiskVO>) object;
        List<HighRiskSurveillance> highRiskSurveillances = new ArrayList<>();
        try {
            int i =0;
            for (HighRiskVO highRiskVO:highRiskVOList) {
                i++;
                highRiskVO.setIndex(i);
                HighRiskSurveillance highRiskSurveillance = (HighRiskSurveillance) TransformToGrpcPo.convertToPojo(HighRiskSurveillance.class,highRiskVO);
                highRiskSurveillances.add(highRiskSurveillance);
            }
            builder.addAllRisk(highRiskSurveillances);
        } catch (Exception e)  {
            e.printStackTrace();
        }

        return builder.build();
    }

    public SystemAndAgentReply getSystemAndAgentCountResponse(Object object){
        SystemAndAgentReply.Builder builder = SystemAndAgentReply.newBuilder();
        ResultMsgVO resultMsgVO = (ResultMsgVO) object;
        List<ResultVO> resultVOList = resultMsgVO.getResultList();
        List<SystemAndAgent> systemAndAgentList = new ArrayList<>();
        try {
            for(ResultVO resultVO: resultVOList){
                SystemAndAgent systemAndAgent = (SystemAndAgent) TransformToGrpcPo.convertToPojo(SystemAndAgent.class,resultVO);
                systemAndAgentList.add(systemAndAgent);
            }
            builder.setTotal(resultMsgVO.getCountSum());
            builder.addAllSystem(systemAndAgentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
     return  builder.build();
    }

}

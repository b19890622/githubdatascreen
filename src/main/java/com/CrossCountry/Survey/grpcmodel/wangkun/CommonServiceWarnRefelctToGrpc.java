package com.CrossCountry.Survey.grpcmodel.wangkun;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.wangkun.NewRiskVO;
import com.CrossCountry.Survey.modelvo.wangkun.WarnFigureListVO;
import com.CrossCountry.Survey.modelvo.wangkun.WarnParamVO;
import com.datascreen.SecurityRisk;
import com.datascreen.SecurityRiskReply;
import com.datascreen.WarnFigure;
import com.datascreen.WarnFigureReply;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/29 13:51
 */
public class CommonServiceWarnRefelctToGrpc {
    /**
     *
     * @param object
     * @return
     */
    public WarnFigureReply getWarnFigureResponse(Object object){
        WarnFigureReply.Builder builder = WarnFigureReply.newBuilder();
        List<WarnFigure> warnFigureList = new ArrayList<>();
        WarnFigureListVO warnFigureListVO = (WarnFigureListVO) object;
        List<WarnParamVO> todayWarnParamList = warnFigureListVO.getTodayFigure();
        List<WarnParamVO> yesdayWarnParamList = warnFigureListVO.getYesdayFigure();
        List<WarnFigure> todayWarnFigure = new ArrayList<>();
        List<WarnFigure> yesdayWarnFigure = new ArrayList<>();
        //List<WarnParamVO> warnParamVOList = (List<WarnParamVO>) object;
        try {
            for (WarnParamVO warnParamVO:todayWarnParamList){
                WarnFigure warnFigure = (WarnFigure) TransformToGrpcPo.convertToPojo(WarnFigure.class,warnParamVO);
                todayWarnFigure.add(warnFigure);
            }
            for (WarnParamVO warnParamVO:yesdayWarnParamList){
                WarnFigure warnFigure = (WarnFigure) TransformToGrpcPo.convertToPojo(WarnFigure.class,warnParamVO);
                yesdayWarnFigure.add(warnFigure);
            }
            builder.addAllTodayFigure(todayWarnFigure);
            builder.addAllYestodayFiture(yesdayWarnFigure);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    /*

     */
    public SecurityRiskReply getSecurityRiskResponse(Object object){
        SecurityRiskReply.Builder builder = SecurityRiskReply.newBuilder();

        List<WarnParamVO> warnParamVOList = (List<WarnParamVO>) object;
        if(warnParamVOList == null){
            return null;
        }
        List<SecurityRisk> securityRiskList = new ArrayList<>();
        try {
            for(WarnParamVO warnParamVO:warnParamVOList){
                SecurityRisk securityRisk = (SecurityRisk) TransformToGrpcPo.convertToPojo(SecurityRisk.class,warnParamVO);
                securityRiskList.add(securityRisk);
            }
            builder.addAllSecurityRisk(securityRiskList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    public SecurityRiskReply getSecurityRiskDealResponse(Object object){
        SecurityRiskReply.Builder builder = SecurityRiskReply.newBuilder();
        List<WarnParamVO> warnParamVODeal = (List<WarnParamVO>) object;
        if(warnParamVODeal == null){
            return null;
        }
        List<SecurityRisk> securityRiskDealList = new ArrayList<>();
        try {
            for (WarnParamVO warnParamVO:warnParamVODeal){
                SecurityRisk securityRisk = (SecurityRisk) TransformToGrpcPo.convertToPojo(SecurityRisk.class,warnParamVO);
                securityRiskDealList.add(securityRisk);
            }
            builder.addAllSecurityRisk(securityRiskDealList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }
}

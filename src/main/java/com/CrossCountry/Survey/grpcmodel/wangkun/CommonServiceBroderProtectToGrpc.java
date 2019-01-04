package com.CrossCountry.Survey.grpcmodel.wangkun;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.wangkun.ChangeNumVO;
import com.CrossCountry.Survey.modelvo.wangkun.TopFiveWarnVO;
import com.datascreen.GetChangeNum;
import com.datascreen.GetChangeNumReplay;
import com.datascreen.GetTopFiveWarn;
import com.datascreen.GetTopFiveWarnReplay;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/28 14:16
 */
public class CommonServiceBroderProtectToGrpc {
    public GetChangeNumReplay getChangeNumResponse(Object object){
        GetChangeNumReplay.Builder builder = GetChangeNumReplay.newBuilder();
        List<ChangeNumVO> changeNumVOList = (List<ChangeNumVO>) object;
        List<GetChangeNum> changeNumList = new ArrayList<>();

        try {
            for (ChangeNumVO changeNumVO:changeNumVOList){
                GetChangeNum changeNum = (GetChangeNum) TransformToGrpcPo.convertToPojo(GetChangeNum.class,changeNumVO);
                changeNumList.add(changeNum);
            }
            builder.addAllChangeNum(changeNumList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return builder.build();
    }
    public GetTopFiveWarnReplay getTopFiveWarnResponse(Object object){
        GetTopFiveWarnReplay.Builder builder = GetTopFiveWarnReplay.newBuilder();
        List<TopFiveWarnVO> topFiveWarnVOList = (List<TopFiveWarnVO>) object;
        List<GetTopFiveWarn> topFiveWarnList = new ArrayList<>();

        try {
            for (TopFiveWarnVO topFiveWarnVO:topFiveWarnVOList){
                GetTopFiveWarn topFiveWarn = (GetTopFiveWarn) TransformToGrpcPo.convertToPojo(GetTopFiveWarn.class,topFiveWarnVO);
                topFiveWarnList.add(topFiveWarn);
            }
            builder.addAllTopFiveWarn(topFiveWarnList) ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return builder.build();
    }
}

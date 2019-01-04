package com.CrossCountry.Survey.grpcmodel.wangkun;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.wangkun.AllDeviceWarnVO;
import com.datascreen.AllDeviceNums;
import com.datascreen.AllDeviceNumsReplay;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/28 11:43
 */
public class CommonServiceDeviceWarnNumToGrpc {
    public AllDeviceNumsReplay getAllDeviceNumsResponse(Object object){
        List<AllDeviceWarnVO> allDeviceWarnVOList = (List<AllDeviceWarnVO>) object;
        AllDeviceNumsReplay.Builder allDeviceNumsReplay = AllDeviceNumsReplay.newBuilder();
        List<AllDeviceNums> allDeviceNumsList = new ArrayList<>();
        try {
            for (AllDeviceWarnVO allDeviceWarnVO:allDeviceWarnVOList){
                AllDeviceNums allDeviceNums = (AllDeviceNums) TransformToGrpcPo.convertToPojo(AllDeviceNums.class,allDeviceWarnVO);
                allDeviceNumsList.add(allDeviceNums);
            }
            allDeviceNumsReplay.addAllAllDeviceNums(allDeviceNumsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allDeviceNumsReplay.build();
    }
}

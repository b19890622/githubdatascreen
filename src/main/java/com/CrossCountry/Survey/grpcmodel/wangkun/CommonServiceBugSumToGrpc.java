package com.CrossCountry.Survey.grpcmodel.wangkun;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.wangkun.BugStatictisVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.VulnerabilityCircle;
import com.datascreen.VulnerabilityCircleReply;
import com.datascreen.VulnerabilityList;
import com.datascreen.VulnerabilityListReply;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/29 11:57
 */
public class CommonServiceBugSumToGrpc {
    /*

     */
    public VulnerabilityCircleReply getVulnerabilityCircleResponse(Object object){
        VulnerabilityCircleReply.Builder builder = VulnerabilityCircleReply.newBuilder();
        List<BugStatictisVO> bugStatictisVOList = (List<BugStatictisVO>) object;
        List<VulnerabilityCircle> circleList  = new ArrayList<>();
        try {
            for (BugStatictisVO bugStatictisVO:bugStatictisVOList){
                VulnerabilityCircle circle = (VulnerabilityCircle) TransformToGrpcPo.convertToPojo(VulnerabilityCircle.class,bugStatictisVO);
                circleList.add(circle);
            }
            builder.addAllCircle(circleList);
        } catch (Exception e) {
            Log4jUtil.error(CommonServiceBugSumToGrpc.class,"漏洞统计转换出错");
            e.printStackTrace();
        }
        return builder.build();
    }

    /**
     *
     * @param object
     * @return
     */
    public VulnerabilityListReply getVulnerabilityListResponse(Object object){
        Log4jUtil.info(CommonServiceBugSumToGrpc.class,"开心进入getVulnerabilityListResponse方法");
        VulnerabilityListReply.Builder builder = VulnerabilityListReply.newBuilder();
        List<BugStatictisVO> bugStatictisVOList = (List<BugStatictisVO>) object;
        List<VulnerabilityList> vulnerabilityLists = new ArrayList<>();
        try {
            for (BugStatictisVO bugStatictisVO:bugStatictisVOList){
                VulnerabilityList vulnerabilityList = (VulnerabilityList) TransformToGrpcPo.convertToPojo(VulnerabilityList.class,bugStatictisVO);
                vulnerabilityLists.add(vulnerabilityList);
            }
            builder.addAllVulnerabilityList(vulnerabilityLists);
        } catch (Exception e) {
            Log4jUtil.error(CommonServiceBugSumToGrpc.class,"漏洞统计列表转换出错");
        }
        Log4jUtil.info(CommonServiceBugSumToGrpc.class,"getVulnerabilityListResponse方法正确结束");
        return builder.build();
    }
}

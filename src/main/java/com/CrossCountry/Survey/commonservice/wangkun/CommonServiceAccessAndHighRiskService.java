package com.CrossCountry.Survey.commonservice.wangkun;

import com.CrossCountry.Survey.mapper.wangkun.AccessAndHighRiskDao;
import com.CrossCountry.Survey.modelvo.wangkun.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @auther wangkun
 * @date 2018/12/30 12:18
 */
@Component
public class CommonServiceAccessAndHighRiskService {
    @Autowired
    AccessAndHighRiskDao accessAndHighRiskDao;
    public AccessListVO getAccessMonitoringPojo(Object object){
        AccessListVO accessListVO = new AccessListVO();
        List<AccessVO> accessVOList = accessAndHighRiskDao.getAccessList();
        if (accessVOList == null || accessVOList.size() < 1) {
            accessListVO = null;
            Log4jUtil.error(CommonServiceAccessAndHighRiskService.class,"getAccessMonitoringPojo返回结果为空");
        } else {

            accessListVO = accessAndHighRiskDao.getAccesNum();
            accessListVO.setAccessVOS(accessVOList);
            Log4jUtil.info(CommonServiceAccessAndHighRiskService.class,"返回的结果大小是："+accessVOList.size());
        }
        return  accessListVO;
    }
    //高危服务
    public List<HighRiskVO> getHighRiskSurveillancePojo(Object object){
        List<HighRiskVO> highRiskVOList = accessAndHighRiskDao.getHighRiskList();
        if(highRiskVOList == null || highRiskVOList.size() <1){
            Log4jUtil.error(CommonServiceAccessAndHighRiskService.class,"getHighRiskSurveillancePojo返回结果为空");
        }
            Log4jUtil.info(CommonServiceAccessAndHighRiskService.class,"返回的结果大小是："+highRiskVOList.size());
        return highRiskVOList;
    }
    //主机系统agnet 数量
    public ResultMsgVO  getSystemAndAgentCountPojo(Object object){
        List<SystemVO> systemAndAgentVOList = accessAndHighRiskDao.getSystemAndAgentList();
        if (systemAndAgentVOList == null || systemAndAgentVOList.size() < 1) {
            Log4jUtil.error(CommonServiceAccessAndHighRiskService.class, "getSystemAndAgentCountPojo返回结果为空");
            return null;
        } else {
            List<SystemJsonVO> systemOneList = new ArrayList<>();
            ResultMsgVO resultMsgVO = getResultVO(systemAndAgentVOList);
            Log4jUtil.error(CommonServiceAccessAndHighRiskService.class, "getSystemAndAgentCountPojo返回结果大小："+systemOneList.size());
            return resultMsgVO;
        }
    }
    //结果转换
    public ResultMsgVO getResultVO(List<SystemVO> voList) {
        String bgpreVersion = null;
        String bgnextVersion = null;
        SystemVO systemVO = null;
        ResultVO node = null;
        int sumTotal = 0;
        int allTotal = 0;
        List<SystemVO> rockyList = new ArrayList<SystemVO>();
        List<ResultVO> nodeList = new ArrayList<ResultVO>();
        for(int j=0; j<voList.size(); j++) {
            systemVO = voList.get(j);
            allTotal += systemVO.getAgentNum();
            bgnextVersion = systemVO.getSysType();
            if(!bgnextVersion.equals(bgpreVersion)) {
                bgpreVersion = bgnextVersion;
                if(node != null) {
                    nodeList.add(node);
                }
                node = new ResultVO();
                sumTotal = 0;
                node.setName(systemVO.getSysType());
                node.setVersion(systemVO.getSysType());
                node.setParent("agent");
                sumTotal += systemVO.getAgentNum();
                node.setValue(sumTotal);
            }else {
                sumTotal += systemVO.getAgentNum();
                node.setValue(sumTotal);
            }
            if(bgnextVersion.equalsIgnoreCase("Windows") || bgnextVersion.equalsIgnoreCase("CentOS")){
                ResultVO nodeVO = new ResultVO();
                SystemVO tempVO = voList.get(j);
                nodeVO.setName(tempVO.getSysVersion());
                nodeVO.setVersion(tempVO.getSysVersion());
                nodeVO.setParent(tempVO.getSysType());
                nodeVO.setValue(tempVO.getAgentNum());
                nodeList.add(nodeVO);
            }else {
                rockyList.add(voList.get(j));
            }
        }
        if(node != null) {
            nodeList.add(node);
        }
        node = null;
        bgnextVersion = null;
        bgpreVersion = null;
        for(int i=0;i<rockyList.size();i++) {
            systemVO = rockyList.get(i);
            bgnextVersion = rockyList.get(i).getSysVersion();
            if("V8.0".equalsIgnoreCase(bgnextVersion)) {
                ResultVO nodeVO = new ResultVO();
                nodeVO.setName(systemVO.getSysVersion());
                nodeVO.setVersion(systemVO.getSysVersion());
                nodeVO.setParent("Rocky");
                nodeVO.setValue(systemVO.getAgentNum());
                nodeList.add(nodeVO);
                continue;
            }else {
                ResultVO nodeVO = new ResultVO();
                nodeVO.setName(systemVO.getAgentVersion());
                nodeVO.setVersion(systemVO.getAgentVersion());
                nodeVO.setParent(systemVO.getSysVersion());
                nodeVO.setValue(systemVO.getAgentNum());
                nodeList.add(nodeVO);
            }
            if(!bgnextVersion.equals(bgpreVersion)) {
                bgpreVersion = bgnextVersion;
                if(node != null) {
                    nodeList.add(node);
                }
                node = new ResultVO();
                sumTotal = 0;
                node.setName(systemVO.getSysVersion());
                node.setVersion(systemVO.getSysVersion());
                node.setParent("Rocky");
                sumTotal += systemVO.getAgentNum();
                node.setValue(sumTotal);
            }else {
                sumTotal += systemVO.getAgentNum();
                node.setValue(sumTotal);
            }
        }
        if(node != null) {
            nodeList.add(node);
        }
        ResultMsgVO msg = new ResultMsgVO();
        msg.setResultList(nodeList);
        msg.setCountSum(allTotal);
        return msg;
    }
}

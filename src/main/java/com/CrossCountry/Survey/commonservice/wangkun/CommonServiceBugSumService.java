package com.CrossCountry.Survey.commonservice.wangkun;

import com.CrossCountry.Survey.mapper.wangkun.BugStatictisDao;
import com.CrossCountry.Survey.modelvo.wangkun.BugStatictisVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/29 11:42
 */
@Component
public class CommonServiceBugSumService {
    @Autowired
    BugStatictisDao bugStatictisDao;
    public List<BugStatictisVO> getVulnerabilityCirclePojo(Object object){
        Log4jUtil.info(CommonServiceBugSumService.class,"getVulnerabilityCirclePojo方法进入");
        List<BugStatictisVO> bugStatictisVOList = bugStatictisDao.getBugNums();
        if(bugStatictisVOList == null || bugStatictisVOList.size() <1){
        Log4jUtil.error(CommonServiceBugSumService.class,"返回结果集为空");
           return null;
        }
        Log4jUtil.info(CommonServiceBugSumService.class,"size():"+bugStatictisVOList.size());
        return bugStatictisVOList;
    }

    public List<BugStatictisVO> getVulnerabilityListPojo(Object object){
        Log4jUtil.error(CommonServiceBugSumService.class,"getVulnerabilityListPojo方法进入");
        List<BugStatictisVO> bugStatictisVOList = bugStatictisDao.getBugList();
        if(bugStatictisVOList == null || bugStatictisVOList.size() <1){
        Log4jUtil.error(CommonServiceBugSumService.class,"漏洞列表返回结果集为空");
            return null;
        }
        for (BugStatictisVO bugStatictisVO:bugStatictisVOList){
            bugStatictisVO.setIndex(bugStatictisVO.getIndexOne());
        }
        Log4jUtil.info(CommonServiceBugSumService.class,"size():"+bugStatictisVOList.size());
        return bugStatictisVOList;
    }

}

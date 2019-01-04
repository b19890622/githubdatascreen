package com.CrossCountry.Survey.commonservice.wangkun;

import com.CrossCountry.Survey.mapper.wangkun.ChangeAndTopFDao;
import com.CrossCountry.Survey.modelvo.wangkun.ChangeNumVO;
import com.CrossCountry.Survey.modelvo.wangkun.TopFiveWarnVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/28 13:48
 */
@Component
public class CommonServiceBorderProtectService {
    @Autowired
    ChangeAndTopFDao changeAndTopFDao;

    /**
     * 设备变更统计
     * @param object 传入的参数
     * @return ChangeNumVO
     */
    public List<ChangeNumVO> getChangeNumPojo(Object object){
        Log4jUtil.info(CommonServiceBorderProtectService.class,"进入getChangeNumPojo方法");
        List<ChangeNumVO> changeNumVOList = changeAndTopFDao.getChangeNums();
        if(null ==changeNumVOList|| changeNumVOList.size()<1){
        Log4jUtil.error(CommonServiceBorderProtectService.class,"返回结果集为空");
            return null;
        }
        Log4jUtil.info(CommonServiceBorderProtectService.class,"返回结果集大小："+changeNumVOList.size());
        return changeNumVOList;
    }

    /**
     * 前五的告警类型统计
     * @param object 前台传入的参数
     * @return TopFiveWarnVO
     */
    public List<TopFiveWarnVO> getTopFiveWarnPojo(Object object){
        Log4jUtil.info(CommonServiceBorderProtectService.class,"进入getTopFiveWarnPojo方法");
        List<TopFiveWarnVO> topFiveWarnVOList = changeAndTopFDao.getTopFiveList();
        if(topFiveWarnVOList ==null||topFiveWarnVOList.size()<1){
        Log4jUtil.error(CommonServiceBorderProtectService.class,"返回结果为空");
            return null;
        }
        Log4jUtil.info(CommonServiceBorderProtectService.class,"返回结果总数："+topFiveWarnVOList.size());
        return topFiveWarnVOList;
    }

}

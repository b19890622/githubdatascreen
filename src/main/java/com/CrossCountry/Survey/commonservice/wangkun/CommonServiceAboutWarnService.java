package com.CrossCountry.Survey.commonservice.wangkun;

import com.CrossCountry.Survey.mapper.wangkun.WarnMonitorDao;
import com.CrossCountry.Survey.modelvo.wangkun.NewRiskVO;
import com.CrossCountry.Survey.modelvo.wangkun.WarnFigureListVO;
import com.CrossCountry.Survey.modelvo.wangkun.WarnParamVO;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.Log4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/29 13:52
 */
@Component
public class CommonServiceAboutWarnService {
    @Autowired
    WarnMonitorDao warnMonitorDao;
    @Autowired
    GoogleCacheUtils cacheUtils;
    //安全风险监测
    public List<WarnParamVO> getSecurityRiskPojo(Object object) throws ParseException {
       /* boolean flag = false;
        Date dateNew = new Date();*//*

        List<WarnParamVO> warnParamVOList = new ArrayList<>();
        //List<WarnParamVO> warnParamVODeal = new ArrayList<>();
        NewRiskVO newRiskVO = new NewRiskVO();
        String cache = (String) cacheUtils.getCacheValue(object.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (cache == null) {
            //cacheUtils.putCacheValue(object.toString(), memoryDate);
            warnParamVOList = warnMonitorDao.getSecurityRisk(tempTime);
            //warnParamVODeal = warnMonitorDao.getSecurityRiskDeal(tempTime);
            long time = System.currentTimeMillis();
            tempTime = sdf.format(time);
            if (warnParamVODeal == null || warnParamVODeal.size() < 1) {
                Log4jUtil.error(CommonServiceAboutWarnService.class, "安全风险处置查询出错");
                newRiskVO.setWarnRiskList(null);
            } else {
                for (WarnParamVO warnParamVO : warnParamVOList) {
                    warnParamVO.setIsNew("0");
                }
                newRiskVO.setWarnRiskList(warnParamVOList);
            }


            if (warnParamVOList == null || warnParamVOList.size() < 1) {
                Log4jUtil.error(CommonServiceAboutWarnService.class, "安全风险查询出错");
                newRiskVO.setWarnRiskDealList(null);
            } else {
                for (WarnParamVO warnParamVO : warnParamVODeal) {
                    warnParamVO.setIsNew("0");
                }
                newRiskVO.setWarnRiskDealList(warnParamVODeal);
            }
            cacheUtils.putCacheValue(object.toString(), tempTime);
        } else {
            tempTime = cache;
            warnParamVOList = warnMonitorDao.getSecurityRisk(tempTime);
            warnParamVODeal = warnMonitorDao.getSecurityRiskDeal(tempTime);
            if (warnParamVODeal == null || warnParamVODeal.size() < 1) {
                Log4jUtil.error(CommonServiceAboutWarnService.class, "安全风险处置查询出错，没有新的数据");
                warnParamVODeal = null;
            }else{
                for (WarnParamVO warnParamVO : warnParamVODeal) {
                    warnParamVO.setIsNew("1");
                }
            }
            if (warnParamVOList == null || warnParamVOList.size() < 1) {
                Log4jUtil.error(CommonServiceAboutWarnService.class, "安全风险查询没有新的数据");
                warnParamVOList = null ;
            }else{
                for (WarnParamVO warnParamVO : warnParamVOList) {
                    warnParamVO.setIsNew("1");
                }
            }

            if(warnParamVODeal !=null && warnParamVOList != null){
                String riskTime = sdf.format(warnParamVOList.get(0).getAlarmTime());
                String riskDealTime = sdf.format(warnParamVODeal.get(0).getAlarmTime());
                long riskMiTime = sdf.parse(riskTime).getTime();
                long riskDealMiTime = sdf.parse(riskDealTime).getTime();
                long tempValue = riskMiTime-riskDealMiTime;
                if(tempValue>0){
                    tempTime = riskTime;
                }else{
                    tempTime = riskDealTime;
                }
            }else if(warnParamVODeal !=null){
                tempTime = sdf.format(warnParamVODeal.get(0).getAlarmTime());

            }else if(warnParamVOList !=null){
                tempTime = sdf.format(warnParamVOList.get(0).getAlarmTime());
            }

            newRiskVO.setWarnRiskDealList(warnParamVODeal);
            newRiskVO.setWarnRiskList(warnParamVOList);
            cacheUtils.putCacheValue(object.toString(), tempTime);
        }*/
        //List<WarnParamVO> warnParamVOList = warnMonitorDao.getSecurityRisk();
        //List<WarnParamVO> warnParamVODeal = warnMonitorDao.getSecurityRiskDeal();
       /* if(warnParamVODeal == null || warnParamVODeal.size()<1){
            Log4jUtil.error(CommonServiceAboutWarnService.class,"安全风险处置查询出错");
        }
        if(warnParamVOList == null || warnParamVOList.size()<1){
            Log4jUtil.error(CommonServiceAboutWarnService.class,"安全风险查询出错");
        }*/

       /* newRiskVO.setWarnRiskDealList(warnParamVODeal);
        newRiskVO.setWarnRiskList(warnParamVOList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        *//*for(WarnParamVO warnParamVO:warnParamVOList){
            warnParamVO.setFlag("0");
            returnList.add(warnParamVO);
        }
        for(WarnParamVO warnParamVO:warnParamVODeal){
            warnParamVO.setFlag("1");
            returnList.add(warnParamVO);
        }*//*
        String beforeTime = sdf.format(memoryDate);
         Date beforeDate = sdf.parse(beforeTime);
        //Date dateBefore = cacheUtils.getCacheValue(sdf.parse(sdf.format(memoryDate)));
        for(WarnParamVO warnParamVO:warnParamVOList){
            if(i != 1){
                Date dateWarn = sdf.parse(warnParamVO.getAlarmTime());
                if (dateWarn.after(beforeDate)){
                    warnParamVO.setIsNew("1");
                    flag = true;
                }else{
                    warnParamVO.setIsNew("0");
                }
            }else{
                warnParamVO.setIsNew("0");
            }
        }
        for (WarnParamVO warnParamVO:warnParamVODeal){
            if(i != 1){
                Date dateWarn = sdf.parse(warnParamVO.getAlarmTime());
                if (dateWarn.after(beforeDate)){
                    warnParamVO.setIsNew("1");
                    flag = true;
                }else{
                    warnParamVO.setIsNew("0");
                }
            }else{
                warnParamVO.setIsNew("0");
            }
        }
        if(flag && i !=1){
            cacheUtils.putCacheValue(object.toString(),sdf.parse(warnParamVOList.get(0).getAlarmTime()));

        }else{
            newRiskVO = null;
        }
*/
        String tempTime  ="1970-01-01";
        List<WarnParamVO> warnParamVOList = new ArrayList<>();
        String cache = (String) cacheUtils.getCacheValue(object.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String isNew = null;
        if (cache != null) {
            tempTime = cache;
            isNew = "1";
        }else{
            //第一次进入
            isNew = "0";
        }
        warnParamVOList = warnMonitorDao.getSecurityRisk(tempTime);
        if (warnParamVOList == null || warnParamVOList.size() < 1) {
            Log4jUtil.error(CommonServiceAboutWarnService.class, "安全风险处置查询出错");
            warnParamVOList = null;
        } else {
            for (WarnParamVO warnParamVO : warnParamVOList) {
                warnParamVO.setIsNew(isNew);
            }
            tempTime = warnParamVOList.get(0).getAlarmTime();
            cacheUtils.putCacheValue(object.toString(),tempTime);
        }
        return warnParamVOList;
    }
    //获取告警曲线
    public WarnFigureListVO getWarnFigurePojo(Object object){
        Calendar cal= Calendar.getInstance();
        long time = System.currentTimeMillis();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        String today = sdf.format(now.getTime());
        now.add(Calendar.DAY_OF_MONTH, -1);
        String yestoday = sdf.format(now.getTime());
        List<WarnParamVO> warnParamVOList = warnMonitorDao.getWarnFigure(today,yestoday,hour);
        List<WarnParamVO> todayFigure = new ArrayList<>();
        List<WarnParamVO> yesdayFigure = new ArrayList<>();
        WarnFigureListVO warnFigureListVO = new WarnFigureListVO();
        if(warnParamVOList ==null|| warnParamVOList.size() < 1 ){
            warnParamVOList = new ArrayList<>(hour*2);
            for (int i = 0; i < hour; i++) {
                WarnParamVO todayVO = new WarnParamVO();
                todayVO.setDate(today);
                todayVO.setPeriod(i+1);
                todayVO.setNums(0);
                todayFigure.add(todayVO);
            }
            for (int i = 0; i < hour ; i++) {
                WarnParamVO yesdayVO = new WarnParamVO();
                yesdayVO.setDate(yestoday);
                yesdayVO.setPeriod(i+1);
                yesdayVO.setNums(0);
                yesdayFigure.add(yesdayVO);

            }
        }else{
            if (warnParamVOList.size() < hour * 2) {
                Log4jUtil.error(CommonServiceAboutWarnService.class, "数据库中相应的视图数据不全");
                for (int i = 0; i < hour ; i++) {
                        WarnParamVO warnParamVO01 = new WarnParamVO();
                    warnParamVO01.setPeriod(i+1);
                    warnParamVO01.setNums(0);
                    warnParamVO01.setDate(today);
                        todayFigure.add(warnParamVO01);

                    WarnParamVO warnParamVO02 = new WarnParamVO();
                    warnParamVO02.setNums(0);
                    warnParamVO02.setPeriod(i+1);
                    warnParamVO02.setDate(yestoday);
                        yesdayFigure.add(warnParamVO02);
                }
                for(int i= 0;i< warnParamVOList.size() ;i++){
                    WarnParamVO warnParamVO = warnParamVOList.get(i);
                    String timeValue = warnParamVO.getDate();
                    int period = warnParamVO.getPeriod();
                    if(timeValue.equals(today)){
                        todayFigure.set(period-1,warnParamVO);
                    }else{
                        yesdayFigure.set(period-1,warnParamVO);
                    }
                }


            } else  {
                for (int i = 0; i < hour * 2; i++) {
                    if (i < hour) {
                        todayFigure.add(warnParamVOList.get(i));
                    } else {
                        yesdayFigure.add(warnParamVOList.get(i));
                    }
                }

            }
        }
        warnFigureListVO.setTodayFigure(todayFigure);
        warnFigureListVO.setYesdayFigure(yesdayFigure);
        return warnFigureListVO;
    }
    public  List<WarnParamVO> getSecurityRiskDealPojo(Object object){
        String tempTime  ="1970-01-01";
        List<WarnParamVO> warnParamVODeal = new ArrayList<>();
        String cache = (String) cacheUtils.getCacheValue(object.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String isNew = null;
        if (cache != null) {
            tempTime = cache;
            isNew = "1";
        }else{
            //第一次进入
            isNew = "0";
        }
        warnParamVODeal = warnMonitorDao.getSecurityRiskDeal(tempTime);
        if (warnParamVODeal == null || warnParamVODeal.size() < 1) {
            Log4jUtil.error(CommonServiceAboutWarnService.class, "安全风险处置查询出错");
            warnParamVODeal = null;
        } else {
            for (WarnParamVO warnParamVO : warnParamVODeal) {
                warnParamVO.setIsNew(isNew);
            }
            tempTime = warnParamVODeal.get(0).getAlarmTime();
            cacheUtils.putCacheValue(object.toString(),tempTime);
        }
        return warnParamVODeal;
    }
}

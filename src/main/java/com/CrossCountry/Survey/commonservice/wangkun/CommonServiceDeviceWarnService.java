package com.CrossCountry.Survey.commonservice.wangkun;

import com.CrossCountry.Survey.mapper.wangkun.DeviceWarnNumDao;
import com.CrossCountry.Survey.modelvo.wangkun.AllDeviceWarnVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @auther wangkun
 * @date 2018/12/28 11:24
 */
@Component
public class CommonServiceDeviceWarnService {
    @Autowired
    DeviceWarnNumDao deviceWarnNumDao;
    public List<AllDeviceWarnVO> getAllDeviceNumsPojo(Object object){
        Log4jUtil.info(CommonServiceDeviceWarnService.class,"进入getAllDeviceNumsPojo方法");
        List<AllDeviceWarnVO> deviceWarnVOList = deviceWarnNumDao.getAllDeviceWarnNums();
        if(deviceWarnVOList == null || deviceWarnVOList.size()<1){
            Log4jUtil.error(CommonServiceDeviceWarnService.class,"getAllDeviceNumsPojo方法没有结果集返回");
            return null;
        }
        Log4jUtil.info(CommonServiceDeviceWarnService.class,"DB返回的结果集大小："+deviceWarnVOList.size());
        int num = 0;
        Map<String,Integer> map = new HashMap<>();
        for (AllDeviceWarnVO allDeviceWarnVO:deviceWarnVOList){
            String name = allDeviceWarnVO.getName();
            if("7".equals(name)){
                //allDeviceWarnVO.setName("主机设备");
                map.put("主机设备",allDeviceWarnVO.getNum());
            }else if("12".equals(name)){
                //allDeviceWarnVO.setName("网络设备");
                map.put("网络设备",allDeviceWarnVO.getNum());
            }else if("11".equals(name)){
                //allDeviceWarnVO.setName("数据库");
                map.put("数据库",allDeviceWarnVO.getNum());
            }else if("0".equals(name)||"1".equals(name)||"4".equals(name)
                    ||"5".equals(name)||"6".equals(name)||"8".equals(name)){
                map.put("安防设备",allDeviceWarnVO.getNum()+num);
                num = map.get("安防设备");

            }
        }
        List<AllDeviceWarnVO> returnList = new ArrayList<>();

        if(map.containsKey("主机设备")){
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("主机设备");
           num =  map.get("主机设备");
           allDeviceWarnVO.setNum(num);
           returnList.add(allDeviceWarnVO);
        }else{
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("主机设备");
            allDeviceWarnVO.setNum(0);
            returnList.add(allDeviceWarnVO);
        }
        if(map.containsKey("网络设备")){
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("网络设备");
            num =  map.get("网络设备");
            allDeviceWarnVO.setNum(num);
            returnList.add(allDeviceWarnVO);
        }else{
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("网络设备");
            allDeviceWarnVO.setNum(0);
            returnList.add(allDeviceWarnVO);
        }
        if(map.containsKey("数据库")){
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("数据库");
            num =  map.get("数据库");
            allDeviceWarnVO.setNum(num);
            returnList.add(allDeviceWarnVO);
        }else{
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("数据库");
            allDeviceWarnVO.setNum(0);
            returnList.add(allDeviceWarnVO);
        }
        if(map.containsKey("安防设备")){
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("安防设备");
            num =  map.get("安防设备");
            allDeviceWarnVO.setNum(num);
            returnList.add(allDeviceWarnVO);
        }else{
            AllDeviceWarnVO allDeviceWarnVO = new AllDeviceWarnVO();
            allDeviceWarnVO.setName("安防设备");
            allDeviceWarnVO.setNum(0);
            returnList.add(allDeviceWarnVO);
        }
        Log4jUtil.info(CommonServiceDeviceWarnService.class,"返回到界面的结果大小："+returnList.size());
        return returnList;
    }
}

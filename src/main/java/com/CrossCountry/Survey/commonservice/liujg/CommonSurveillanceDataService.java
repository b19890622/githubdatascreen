package com.CrossCountry.Survey.commonservice.liujg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.CustomSurveillanceDataDao;
import com.CrossCountry.Survey.modelvo.liujg.AllNetWorkMsgVO;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class CommonSurveillanceDataService {

	@Autowired
	CustomSurveillanceDataDao customSurveillanceDataDao;
	
	public List<AllNetWorkMsgVO> getAllNetWorkMsg(Object obj){
		List<AllNetWorkMsgVO> list = null;
		try {
			list = customSurveillanceDataDao.getAllNetWorkMsg();
			if(list == null || list.size() == 0) {
				return null;
			}
			String msg = "";
			for(AllNetWorkMsgVO msgVO : list) {
				msg = msgVO.getRegionName();
				msg = msg.replace("省调", "").replace("市调", "")
						.replace("地调", "").replace("区调", "")
						.replace("中调", "");
				msgVO.setRegionName(msg);
			}
		}catch(Exception e) {
			Log4jUtil.error(CommonSurveillanceDataService.class, e.getMessage(), e);
			return null;
		}
		return list;
	}
}

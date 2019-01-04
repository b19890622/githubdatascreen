package com.CrossCountry.Survey.commonservice.liujg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.CustomBorderProDao;
import com.CrossCountry.Survey.modelvo.liujg.TendencyOnWeekMsgVO;
import com.CrossCountry.Survey.modelvo.liujg.TendencyOnWeekVO;
import com.CrossCountry.Survey.modelvo.liujg.ZxDeviceWarnVO;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class CommonBorderProService {
	
	@Autowired
	CustomBorderProDao customBorderProDao;
	
	public List<ZxDeviceWarnVO> getBroderProVOMsg(Object obj){
		List<ZxDeviceWarnVO> voList = customBorderProDao.getBroderProVOMsg();
		if(voList == null || voList.size() == 0) {
			Log4jUtil.warn(CommonBorderProService.class, "获取地区区域为空");
			return null;
		}
		String msg = "";
		List<ZxDeviceWarnVO> centerList = new ArrayList<ZxDeviceWarnVO>();
		for(ZxDeviceWarnVO warnVO : voList) {
			msg = warnVO.getRegionName();
			if(msg.indexOf("分中心") != -1) {
				centerList.add(warnVO);
			}else {
				msg = msg.replace("省调", "").replace("市调", "")
						.replace("地调", "").replace("区调", "")
						.replace("中调", "");
				warnVO.setRegionName(msg);
			}
		}
		voList.removeAll(centerList);
		return voList;
	}
	
	public TendencyOnWeekMsgVO getTendencyOnWeekVOList(Object obj){
		List<TendencyOnWeekVO> voList = customBorderProDao.getTendencyOnWeek();
		if(voList == null || voList.size() == 0) {
			Log4jUtil.warn(CommonBorderProService.class, "获取一星期趋势数据为空");
			return null;
		}
		int num = 0;
		for(TendencyOnWeekVO weekVO : voList) {
			num += weekVO.getEventNum();
		}
		TendencyOnWeekMsgVO vo = new TendencyOnWeekMsgVO();
		vo.setList(voList);
		vo.setSumNum(num);
		return vo;
	}

}

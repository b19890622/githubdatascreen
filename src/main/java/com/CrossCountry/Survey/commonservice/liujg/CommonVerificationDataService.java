package com.CrossCountry.Survey.commonservice.liujg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.CustomVerificationDao;
import com.CrossCountry.Survey.modelvo.liujg.SurveillanceRegionMsgVO;
import com.CrossCountry.Survey.modelvo.liujg.SurveillanceRegionVO;
import com.CrossCountry.Survey.modelvo.liujg.ZxSurveillanceLable;
import com.CrossCountry.Survey.modelvo.liujg.ZxSurveillanceMsgVO;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class CommonVerificationDataService {
	
	@Autowired
	CustomVerificationDao customVerificationDao;
	
	public List<ZxSurveillanceMsgVO> getZxSurveillanceMsg(Object obj){
		List<ZxSurveillanceMsgVO> msg = null;
		try {
			ZxSurveillanceLable lable = customVerificationDao.getZxSurveillanceMsg();
			if(lable == null) {
				return null;
			}else {
				int sumTotal = Integer.valueOf(lable.getScrRate())
						+Integer.valueOf(lable.getPortRate())
						+Integer.valueOf(lable.getIpRate())
						+Integer.valueOf( lable.getTunnelRate());
				msg = new ArrayList<ZxSurveillanceMsgVO>();
				ZxSurveillanceMsgVO zxMsgVO = new ZxSurveillanceMsgVO();
				zxMsgVO.setSurveillanceName("密通隧道下存在明通策略");
				zxMsgVO.setSurveillanceRate(lable.getScrRate());
				msg.add(zxMsgVO);
				zxMsgVO = new ZxSurveillanceMsgVO();
				zxMsgVO.setSurveillanceName("端口范围设置不符合规范");
				zxMsgVO.setSurveillanceRate(lable.getPortRate());
				msg.add(zxMsgVO);
				zxMsgVO = new ZxSurveillanceMsgVO();
				zxMsgVO.setSurveillanceName("ip地址范围不在指定规范");
				zxMsgVO.setSurveillanceRate(lable.getIpRate());
				msg.add(zxMsgVO);
				zxMsgVO = new ZxSurveillanceMsgVO();
				zxMsgVO.setSurveillanceName("明通隧道");
				zxMsgVO.setSurveillanceRate(lable.getTunnelRate());
				msg.add(zxMsgVO);
				if(sumTotal == 0) {
					for(int i=0;i<msg.size();i++) {
						msg.get(i).setSurveillanceRate(msg.get(i).getSurveillanceRate()+"%");
					}
					return msg;
				}
				zxMsgVO = null;
				for(int i=0;i<msg.size();i++) {
					if(zxMsgVO == null) {
						zxMsgVO = msg.get(i);
					}else {
						if(Integer.valueOf(zxMsgVO.getSurveillanceRate()) >
						      Integer.valueOf(msg.get(i).getSurveillanceRate())){
							     zxMsgVO = msg.get(i);
						      }
					}
					int num = Integer.valueOf(msg.get(i).getSurveillanceRate())*100/sumTotal;
					msg.get(i).setSurveillanceRate(num+"");
				}
				msg.remove(zxMsgVO);
				int otherRate = 0;
				for(int i=0;i<msg.size();i++) {
					otherRate += Integer.valueOf(msg.get(i).getSurveillanceRate());
					msg.get(i).setSurveillanceRate(msg.get(i).getSurveillanceRate()+"%");
				}
				zxMsgVO.setSurveillanceRate((100-otherRate)+"%");
				msg.add(zxMsgVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonVerificationDataService.class, e.getMessage(), e);
		}
		return msg;
	}
	
	public List<SurveillanceRegionMsgVO> getSurveillanceRegionMsg(Object obj){
		List<SurveillanceRegionMsgVO> msg = null;
		try {
			SurveillanceRegionVO regionVO = customVerificationDao.getSurveillanceRegionMsg();
			if(regionVO == null) {
				return null;
			}
			msg = new ArrayList<SurveillanceRegionMsgVO>();
			SurveillanceRegionMsgVO msgVO = new SurveillanceRegionMsgVO();
			msgVO.setSurveillanceRegion("I区");
			msgVO.setSurveillanceNum(regionVO.getOneRegion());
			msg.add(msgVO);
			msgVO = new SurveillanceRegionMsgVO();
			msgVO.setSurveillanceRegion("II区");
			msgVO.setSurveillanceNum(regionVO.getTwoRegion());
			msg.add(msgVO);
			msgVO = new SurveillanceRegionMsgVO();
			msgVO.setSurveillanceRegion("III区");
			msgVO.setSurveillanceNum(regionVO.getThreeRegion());
			msg.add(msgVO);
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonVerificationDataService.class, e.getMessage(), e);
		}
		return msg;
	}

}

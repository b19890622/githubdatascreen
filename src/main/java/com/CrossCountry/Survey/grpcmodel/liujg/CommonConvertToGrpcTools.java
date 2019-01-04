package com.CrossCountry.Survey.grpcmodel.liujg;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.liujg.AllNetWorkMsgVO;
import com.CrossCountry.Survey.modelvo.liujg.OnDutyPersionVO;
import com.CrossCountry.Survey.modelvo.liujg.RiskWarnSumVO;
import com.CrossCountry.Survey.modelvo.liujg.RiskWarnVO;
import com.CrossCountry.Survey.modelvo.liujg.SurveillanceRegionMsgVO;
import com.CrossCountry.Survey.modelvo.liujg.TendencyOnWeekMsgVO;
import com.CrossCountry.Survey.modelvo.liujg.TendencyOnWeekVO;
import com.CrossCountry.Survey.modelvo.liujg.ZxDeviceWarnVO;
import com.CrossCountry.Survey.modelvo.liujg.ZxSurveillanceMsgVO;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AllNetWork;
import com.datascreen.AllNetWorkMsg;
import com.datascreen.DutyPersion;
import com.datascreen.OnDutyPersion;
import com.datascreen.RiskWarning;
import com.datascreen.RiskWarningProto;
import com.datascreen.SurveillanceRegion;
import com.datascreen.SurveillanceRegionMsg;
import com.datascreen.TendencyOnWeek;
import com.datascreen.TendencyOnWeekMsg;
import com.datascreen.ZxDeviceWarn;
import com.datascreen.ZxDeviceWarnMsg;
import com.datascreen.ZxSurveillance;
import com.datascreen.ZxSurveillanceMsg;

public class CommonConvertToGrpcTools {

	/**
	 * 值班变化须知
	 * @param obj
	 * @return
	 */
	public Object getConvertToDutyPersion(Object obj) {
		try {
			if(obj == null){
				return null;
			}
			List<OnDutyPersionVO> list = (List<OnDutyPersionVO>) obj;
			List<DutyPersion> grpcList = new ArrayList<DutyPersion>();
			DutyPersion persionGrpc = null;
			for (OnDutyPersionVO persion : list) {
				persionGrpc = (DutyPersion) TransformToGrpcPo.convertToPojo(DutyPersion.class, persion);
				grpcList.add(persionGrpc);
			}
			OnDutyPersion result = OnDutyPersion.newBuilder().addAllDutyPerson(grpcList).build();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
	}
	
	//风险预警告警
	public Object getConvertToRisk(Object obj) {
		try {
			if(obj == null){
				return null;
			}
			RiskWarnSumVO sumVO = (RiskWarnSumVO)obj;
			List<RiskWarnVO> list = sumVO.getRiskWarnVO();
			List<RiskWarning> warnList = new ArrayList<RiskWarning>();
			RiskWarning warning = null;
			for(RiskWarnVO vo : list){
				warning = (RiskWarning) TransformToGrpcPo.convertToPojo(RiskWarning.class, vo);
				warnList.add(warning);
			}
			RiskWarningProto proto = RiskWarningProto.newBuilder().addAllRiskWarn(warnList)
					.setCount(sumVO.getCount()).build();
			return proto;
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
	}
	
	public Object getConvertToZxDevice(Object obj) {
		if(obj == null){
			return null;
		}
		ZxDeviceWarnMsg warnMsg = null;
		try {
			List<ZxDeviceWarnVO> zxDeviceList = (List<ZxDeviceWarnVO>)obj;
			List<ZxDeviceWarn> childGrpcList = new ArrayList<ZxDeviceWarn>();
			ZxDeviceWarn msg = null;
			for(ZxDeviceWarnVO vo : zxDeviceList) {
				msg = (ZxDeviceWarn) TransformToGrpcPo.convertToPojo(ZxDeviceWarn.class, vo);
				childGrpcList.add(msg);
			}
			warnMsg = ZxDeviceWarnMsg.newBuilder().addAllZxDeviceWarn(childGrpcList).build();
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
		return warnMsg;
	}
	
	public Object getConvertToTendencyWeek(Object obj) {
		if(obj == null){
			return null;
		}
		TendencyOnWeekMsg warnMsg = null;
		try {
			TendencyOnWeekMsgVO weekMsgVO = (TendencyOnWeekMsgVO)obj;
			List<TendencyOnWeek> childGrpcList = new ArrayList<TendencyOnWeek>();
			TendencyOnWeek msg = null;
			for(TendencyOnWeekVO vo : weekMsgVO.getList()) {
				msg = (TendencyOnWeek) TransformToGrpcPo.convertToPojo(TendencyOnWeek.class, vo);
				childGrpcList.add(msg);
			}
			warnMsg = TendencyOnWeekMsg.newBuilder().addAllTendencyOnWeek(childGrpcList)
					.setEventSum(weekMsgVO.getSumNum()).build();
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
		return warnMsg;
	}
	
	public Object getConvertToSurveillance(Object obj) {
		if(obj == null){
			return null;
		}
		AllNetWorkMsg warnMsg = null;
		try {
			List<AllNetWorkMsgVO> allNetWorkList = (List<AllNetWorkMsgVO>)obj;
			List<AllNetWork> childGrpcList = new ArrayList<AllNetWork>();
			AllNetWork msg = null;
			for(AllNetWorkMsgVO vo : allNetWorkList) {
				msg = (AllNetWork) TransformToGrpcPo.convertToPojo(AllNetWork.class, vo);
				childGrpcList.add(msg);
			}
			warnMsg = AllNetWorkMsg.newBuilder().addAllAllNetWork(childGrpcList)
					.build();
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
		return warnMsg;
	}
	
	public Object getConvertToZxSurveillance(Object obj) {
		if(obj == null){
			return null;
		}
		ZxSurveillanceMsg warnMsg = null;
		try {
			List<ZxSurveillanceMsgVO> zxSurveillanceList = (List<ZxSurveillanceMsgVO>)obj;
			List<ZxSurveillance> childGrpcList = new ArrayList<ZxSurveillance>();
			ZxSurveillance msg = null;
			for(ZxSurveillanceMsgVO vo : zxSurveillanceList) {
				msg = (ZxSurveillance) TransformToGrpcPo.convertToPojo(ZxSurveillance.class, vo);
				childGrpcList.add(msg);
			}
			warnMsg = ZxSurveillanceMsg.newBuilder().addAllZxSurveillance(childGrpcList)
					.build();
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
		return warnMsg;
	}

	public Object getConvertToSurveillanceRegion(Object obj) {
		if(obj == null){
			return null;
		}
		SurveillanceRegionMsg warnMsg = null;
		try {
			List<SurveillanceRegionMsgVO> zxSurveillanceList = (List<SurveillanceRegionMsgVO>)obj;
			List<SurveillanceRegion> childGrpcList = new ArrayList<SurveillanceRegion>();
			SurveillanceRegion msg = null;
			for(SurveillanceRegionMsgVO vo : zxSurveillanceList) {
				msg = (SurveillanceRegion) TransformToGrpcPo.convertToPojo(SurveillanceRegion.class, vo);
				childGrpcList.add(msg);
			}
			warnMsg = SurveillanceRegionMsg.newBuilder().addAllSurveillanceRegion(childGrpcList)
					.build();
		}catch(Exception e) {
			e.printStackTrace();
			Log4jUtil.error(CommonConvertToGrpcTools.class, e.getMessage(), e);
			return null;
		}
		return warnMsg;
	}

}

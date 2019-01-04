package com.CrossCountry.Survey.commonservice.liujg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.CustomUserPesonDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.UserInfoVO;
import com.CrossCountry.Survey.modelvo.liujg.OnDutyPersionVO;
import com.CrossCountry.Survey.modelvo.liujg.RiskWarnSumVO;
import com.CrossCountry.Survey.modelvo.liujg.RiskWarnVO;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;

@Component
public class CommonServiceDataScreenService {

	@Autowired
	private CustomUserPesonDao customUserPesonDao;

	@Autowired
	GoogleCacheUtils cacheUtils;

	public UserInfoVO getUsername() {
		return customUserPesonDao.getUserInfoFirst();
	}

	// 返回风险预警信息
	public RiskWarnSumVO getRiskWarnMsg(Object obj) {
		SimpleDateFormat formar = new SimpleDateFormat("yyyy-MM-dd");
		List<RiskWarnVO> vo = customUserPesonDao.getRiskWarn();
		int sum = customUserPesonDao.getRiskWarnCount();
		for (RiskWarnVO riskVO : vo) {
			riskVO.setRiskDate(formar.format(riskVO.getRiskRDate()));
		}
		RiskWarnSumVO sumVO = new RiskWarnSumVO();
		sumVO.setCount(sum);
		sumVO.setRiskWarnVO(vo);
		return sumVO;
	}

	// 返回值班数据，需跟上一次值班人员进行比较，相等的话就不推送
	@SuppressWarnings("unchecked")
	public List<OnDutyPersionVO> getOnDutyPersionMsg(Object obj) {
		CommonArgs commonArgs = (CommonArgs) obj;
		String key = commonArgs.getKey();
		Object objOnduty = cacheUtils.getCacheValue(key);
		List<OnDutyPersionVO> msg = null;
		try {
			msg = customUserPesonDao.getOnDutyPersion();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (msg == null || msg.size() == 0) {
			return null;
		}
		List<OnDutyPersionVO> vo = null;
		if (objOnduty == null) {
			vo = new ArrayList<OnDutyPersionVO>();
			contertTo(vo, msg);
			cacheUtils.putCacheValue(key, vo);
			return msg;
		} else {
			vo = (List<OnDutyPersionVO>) objOnduty;
			if (vo.size() != msg.size()) {
				contertTo(vo, msg);
				return msg;
			}
		}
		boolean flag = false;
		for (OnDutyPersionVO temp : vo) {
			if (flag) {
				contertTo(vo, msg);
				return msg;
			}
			for (OnDutyPersionVO chk : msg) {
				if (temp.getName().equals(chk.getName())) {
					flag = false;
					break;
				}
				flag = true;
			}
		}
		if (!flag) {
			return null;
		}
		return msg;
	}

	private void contertTo(List<OnDutyPersionVO> dest, List<OnDutyPersionVO> source) {
		dest.clear();
		dest.addAll(source);
	}
}

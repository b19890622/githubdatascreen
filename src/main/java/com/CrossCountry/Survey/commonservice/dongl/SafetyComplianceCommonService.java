package com.CrossCountry.Survey.commonservice.dongl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.dongl.SafetyComplianceDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.dongl.SafetyComplianceEntity;
import com.CrossCountry.Survey.modelvo.dongl.SafetyComplianceLocalVo;
import com.CrossCountry.Survey.modelvo.dongl.SafetyComplianceVo;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class SafetyComplianceCommonService {

	@Autowired
	private SafetyComplianceDao safetyComplianceDao;
	@Autowired
	private DataChangePublicService dataChangePublicService;
	@Autowired
	GoogleCacheUtils cacheUtils;

	public SafetyComplianceVo getSafetyCompliance(Object object) {
		CommonArgs args = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(args.getName());
		String key = args.getKey();
		SafetyComplianceVo safetyComplianceVo = new SafetyComplianceVo();
		int CVSIlegalNum = 0;
		int CVSLegalNum = 0;
		int VBSNum = 0;
		int VBSIlegalNum = 0;
		int UserCheckNum = 0;
		int UserCheckIlegalNum = 0;
		int VEADTunnelNum = 0;
		int VEADPolicyNum = 0;
		int VEADIlegalTunnelNum = 0;
		int VEADIlegalPolicyNum = 0;

		try {
			List<SafetyComplianceEntity> list = safetyComplianceDao.getSafetyComplianceSub(name);
			if (null != list && 0 < list.size()) {
				for (SafetyComplianceEntity s : list) {
					if (s.getCheckDimen() == 1) {
						CVSLegalNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 2) {
						CVSIlegalNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 3) {
						VBSNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 4) {
						VBSIlegalNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 5) {
						UserCheckNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 6) {
						UserCheckIlegalNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 7) {
						VEADTunnelNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 8) {
						VEADIlegalTunnelNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 9) {
						VEADPolicyNum += s.getCheckCount();
					}
					if (s.getCheckDimen() == 10) {
						VEADIlegalPolicyNum += s.getCheckCount();
					}
				}
			}
			SafetyComplianceLocalVo safetyComplianceLocalVo = new SafetyComplianceLocalVo();
			int CVSIlegalNumAll = 0;
			int CVSLegalNumAll = 0;
			int VBSNumAll = 0;
			int VBSIlegalNumAll = 0;
			int UserCheckNumAll = 0;
			int UserCheckIlegalNumAll = 0;
			int VEADTunnelNumAll = 0;
			int VEADPolicyNumAll = 0;
			int VEADIlegalTunnelNumAll = 0;
			int VEADIlegalPolicyNumAll = 0;
			if (StringUtils.isBlank(name)) {
				safetyComplianceLocalVo = safetyComplianceDao.getSafetyComplianceLocal();
				if (null != safetyComplianceLocalVo) {
					CVSIlegalNumAll = safetyComplianceLocalVo.getCVSLegalNum();
					CVSLegalNumAll = safetyComplianceLocalVo.getCVSIlegalNum();
					VBSNumAll = safetyComplianceLocalVo.getVBSNum();
					VBSIlegalNumAll = safetyComplianceLocalVo.getVBSIlegalNum();
					UserCheckNumAll = safetyComplianceLocalVo.getUserCheckNum();
					UserCheckIlegalNumAll = safetyComplianceLocalVo.getUserCheckIlegalNum();
					VEADTunnelNumAll = safetyComplianceLocalVo.getVEADTunnelNum();
					VEADPolicyNumAll = safetyComplianceLocalVo.getVEADPolicyNum();
					VEADIlegalTunnelNumAll = safetyComplianceLocalVo.getVEADIlegalTunnelNum();
					VEADIlegalPolicyNumAll = safetyComplianceLocalVo.getVEADIlegalPolicyNum();
				}
			}
			safetyComplianceVo.setCVSLegalNum(CVSIlegalNumAll + CVSLegalNum);
			safetyComplianceVo.setCVSIlegalNum(CVSLegalNumAll + CVSIlegalNum);
			safetyComplianceVo.setVBSNum(VBSNumAll + VBSNum);
			safetyComplianceVo.setVBSIlegalNum(VBSIlegalNumAll + VBSIlegalNum);
			safetyComplianceVo.setUserCheckNum(UserCheckNumAll + UserCheckNum);
			safetyComplianceVo.setUserCheckIlegalNum(UserCheckIlegalNumAll + UserCheckIlegalNum);
			safetyComplianceVo.setVEADTunnelNum(VEADTunnelNumAll + VEADTunnelNum);
			safetyComplianceVo.setVEADIlegalTunnelNum(VEADIlegalTunnelNumAll + VEADIlegalTunnelNum);
			safetyComplianceVo.setVEADPolicyNum(VEADPolicyNumAll + VEADPolicyNum);
			safetyComplianceVo.setVEADIlegalPolicyNum(VEADIlegalPolicyNumAll + VEADIlegalPolicyNum);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(SafetyComplianceCommonService.class, e.getMessage(), e);
		}
		// 需要判断是否和上次数据一致，如果一致返回null
		boolean bl = isOrReturnVO(key, safetyComplianceVo);
		if (!bl) {
			return null;
		}
		return safetyComplianceVo;
	}

	private boolean isOrReturnVO(String key, SafetyComplianceVo safetyComplianceVo) {
		Object objOnduty = cacheUtils.getCacheValue(key);
		if (objOnduty == null) {
			cacheUtils.putCacheValue(key, safetyComplianceVo);
			return true;
		}
		SafetyComplianceVo origal = (SafetyComplianceVo) objOnduty;
		if (origal.equals(safetyComplianceVo)) {
			return false;
		}
		cacheUtils.putCacheValue(key, safetyComplianceVo);
		return true;
	}
}

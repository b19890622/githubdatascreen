package com.CrossCountry.Survey.commonservice.shichf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.shichf.ScreenDrawMapDataDao;
import com.CrossCountry.Survey.mapper.shichf.ScreenSecretPassRateDao;
import com.CrossCountry.Survey.mapper.wangkun.LastSubSecurityEventDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.shichf.DrawingMapsForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.LowLevelMapEntity;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatCenterIndexVO;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatSecurityArrayVO;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatWarningArrayEntity;
import com.CrossCountry.Survey.modelvo.shichf.MapFormatWarningArrayVO;
import com.CrossCountry.Survey.modelvo.shichf.ProvinceSecurityEventToSolvedNumVO;
import com.CrossCountry.Survey.modelvo.shichf.RealtimeStatisticsAllEntity;
import com.CrossCountry.Survey.modelvo.shichf.SafetyRateLocalEntity;
import com.CrossCountry.Survey.modelvo.shichf.SafetyRateSubEntity;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.CrossCountry.Survey.utils.shichf.CommonCheck;
import com.CrossCountry.Survey.utils.shichf.StringFilterUtil;

@Component
public class ScreenDrawMapDataService {
	@Autowired
	private ScreenDrawMapDataDao screenDrawMapDataDao;
	@Autowired
	private DataChangePublicService dataChangePublicService;
	@Autowired
	GoogleCacheUtils cacheUtils;
	@Autowired
	private ScreenSecretPassRateDao screenSecretPassRateDao;
	@Autowired
	private LastSubSecurityEventDao lastSubSecurityEventDao;

	public DrawingMapsForParametersVO getDrawingMapsForParametersVO(Object object) {
		CommonArgs commonArgs = (CommonArgs) object;
		String name = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		String key = commonArgs.getKey();
		String[] run = key.split(",");
		DrawingMapsForParametersVO drawingMapsForParametersVO = new DrawingMapsForParametersVO();
		// 安全指标数组
		List<MapFormatSecurityArrayVO> mapFormatSecurityArrays = new ArrayList<>();
		// 告警数组
		List<MapFormatWarningArrayVO> warningArray = new ArrayList<>();
		// 分中心指标数组
		List<MapFormatCenterIndexVO> centerIndexArray = new ArrayList<>();
		// 安全指标、分中心指标
		getSecurityArrayParameters(mapFormatSecurityArrays, drawingMapsForParametersVO, name, centerIndexArray);
		// 告警指标
		getWarningArrayArrayParameters(run[1], warningArray, drawingMapsForParametersVO, name);
		// 需要判断是否和上次数据一致，如果一致返回null
		boolean bl = isOrReturnVO(run[0], drawingMapsForParametersVO);
		if (!bl) {
			return null;
		}
		return drawingMapsForParametersVO;
	}

	private boolean isOrReturnVO(String key, DrawingMapsForParametersVO drawingMapsForParametersVO) {
		Object objOnduty = cacheUtils.getCacheValue(key);
		if (objOnduty == null) {
			cacheUtils.putCacheValue(key, drawingMapsForParametersVO);
			return true;
		}
		DrawingMapsForParametersVO origal = (DrawingMapsForParametersVO) objOnduty;
		List<MapFormatSecurityArrayVO> securitysOrigal = origal.getSecurityArray();
		if (securitysOrigal == null) {
			securitysOrigal = new ArrayList<>();
		}
		List<MapFormatWarningArrayVO> warningsOrigal = origal.getWarningArray();
		if (warningsOrigal == null) {
			warningsOrigal = new ArrayList<>();
		}
		List<MapFormatCenterIndexVO> centerOrigal = origal.getCenterIndexArray();
		if (centerOrigal == null) {
			centerOrigal = new ArrayList<>();
		}
		List<MapFormatSecurityArrayVO> securitysDest = drawingMapsForParametersVO.getSecurityArray();
		if (securitysDest == null) {
			securitysDest = new ArrayList<>();
		}
		List<MapFormatWarningArrayVO> warningsDest = drawingMapsForParametersVO.getWarningArray();
		if (warningsDest == null) {
			warningsDest = new ArrayList<>();
		}
		List<MapFormatCenterIndexVO> centerDest = drawingMapsForParametersVO.getCenterIndexArray();
		if (centerDest == null) {
			centerDest = new ArrayList<>();
		}
		if (securitysOrigal.containsAll(securitysDest) && warningsOrigal.containsAll(warningsDest)
				&& centerOrigal.containsAll(centerDest)) {
			return false;
		}
//		if (securitysOrigal.containsAll(securitysDest) && warningsOrigal.containsAll(warningsDest)) {
//			return false;
//		}
		cacheUtils.putCacheValue(key, drawingMapsForParametersVO);
		return true;
	}

	private void getWarningArrayArrayParameters(String warnrun, List<MapFormatWarningArrayVO> warningArray,
			DrawingMapsForParametersVO drawingMapsForParametersVO, String name) {
		Date dateNew = new Date();
		List<MapFormatWarningArrayEntity> warnings = new ArrayList<>();
		Date memoryDate = (Date) cacheUtils.getCacheValue(warnrun);
		int i = 0;
		if (memoryDate == null) {
			i = 1;
			memoryDate = dateNew;
			cacheUtils.putCacheValue(warnrun, memoryDate);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String beforeTime = sdf.format(memoryDate);
		try {
			// Date beforeDate = sdf.parse(beforeTime);

			if (StringUtils.isBlank(name)) {
				warnings = screenDrawMapDataDao.getMapFormatWarningArrayEntityList(name);
			} else {
				warnings = screenDrawMapDataDao.getMapFormatWarningArrayEntityCityList(name);
			}
			if (null != warnings) {
				// boolean flag = false;
				for (MapFormatWarningArrayEntity warningEntity : warnings) {
					MapFormatWarningArrayVO vo = new MapFormatWarningArrayVO();
					vo.setWarningTime(sdf.format(warningEntity.getWarningTime()));
					if (StringUtils.isBlank(name)) {
						vo.setCutprovince(StringFilterUtil.filterName(null == warningEntity.getProvinceName()
								|| "NULL".equals(warningEntity.getProvinceName())
								|| "null".equals(warningEntity.getProvinceName()) ? ""
										: warningEntity.getProvinceName()));
					} else {
						// 如果是撰取，截断城市取localProvince
						vo.setCutprovince(StringFilterUtil.filterName(null == warningEntity.getLocalProvince()
								|| "NULL".equals(warningEntity.getLocalProvince())
								|| "null".equals(warningEntity.getLocalProvince()) ? ""
										: warningEntity.getLocalProvince()));
					}

					vo.setLocalprovince(StringFilterUtil.filterName(
							null == warningEntity.getLocalProvince() || "NULL".equals(warningEntity.getLocalProvince())
									|| "null".equals(warningEntity.getLocalProvince()) ? ""
											: warningEntity.getLocalProvince()));
					vo.setRemoteprovince(StringFilterUtil.filterName(null == warningEntity.getRemoteProvince()
							|| "NULL".equals(warningEntity.getRemoteProvince())
							|| "null".equals(warningEntity.getRemoteProvince()) ? ""
									: warningEntity.getRemoteProvince()));
					if (i != 1) { // 不是第一次
						Date dateWarn = new Date((warningEntity.getWarningTime().getTime()));
						// 增加15秒动画消失
						long warnningStartTime = dateWarn.getTime();
						if ((dateNew.getTime() - warnningStartTime) / 1000 >= 20) {
							vo.setWarningstate("2");
						} else {
							// if (dateWarn.after(beforeDate)) {
							// flag = true;
							if (StringUtils.isBlank(vo.getLocalprovince())
									|| StringUtils.isBlank(vo.getRemoteprovince())
									|| (vo.getLocalprovince().equals(vo.getRemoteprovince()))
									|| (vo.getLocalprovince().substring(0, vo.getLocalprovince().length() - 2)
											.equals(vo.getRemoteprovince()))
									|| (vo.getRemoteprovince().substring(0, vo.getRemoteprovince().length() - 2)
											.equals(vo.getLocalprovince()))) {
								vo.setWarningstate("1");
								Log4jUtil.error(ScreenDrawMapDataService.class, "--------huaquanbaojing------shijian:"
										+ sdf.format(new Date()) + "  " + vo.getCutprovince());
							} else {
								vo.setWarningstate("0");
							}
							// } else {
							// vo.setWarningstate("2");
							// }
						}
					} else {
						vo.setWarningstate("2");
					}

					warningArray.add(vo);
				}
				// if (flag) {
				if (warnings != null && warnings.size() > 0) {
					cacheUtils.putCacheValue(warnrun, warnings.get(0).getWarningTime());
				} else {
					cacheUtils.putCacheValue(warnrun, new Date());
				}
				// }
				drawingMapsForParametersVO.setWarningArray(warningArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenDrawMapDataService.class, e.getMessage(), e);
		}

	}

	private void getSecurityArrayParameters(List<MapFormatSecurityArrayVO> mapFormatSecurityArrays,
			DrawingMapsForParametersVO drawingMapsForParametersVO, String name,
			List<MapFormatCenterIndexVO> centerIndexArray) {
		// 构造下级核查指标
		Map<String, BigDecimal> subCheckVO = new HashMap<>();
		// 分中心核查指标
		Map<String, BigDecimal> centerCheckVO = new HashMap<>();
		// 分中心密通指标
		Map<String, BigDecimal> centerSecretVO = new HashMap<>();
		// 省调未解决事件数
		Map<String, String> toSolvedVO = new HashMap<>();
		// 分中心在线率
		Map<String, BigDecimal> centerOnlineVO = new HashMap<>();
		// 分中心未解决事件数
		Map<String, String> toSolvedCenterVO = new HashMap<>();
		if (StringUtils.isBlank(name)) {
			createProvinceSecurityEventToSolvedNum(toSolvedVO);
			createSafeRateMap(subCheckVO, name);
			createSafeRateMapByCenter(centerCheckVO);
			createSecretRateMapByCenter(centerSecretVO);
			createOnlineRateMapByCenter(centerOnlineVO);
			createCenterSecurityEventToSolvedNum(toSolvedCenterVO);
		}
		// 所有下级区域名称数据
		List<LowLevelMapEntity> lowLevelMapEntityDbs = new ArrayList<>();
		if (StringUtils.isBlank(name)) {
			lowLevelMapEntityDbs = screenDrawMapDataDao.getLowLevelMapEntityList(name);
		} else {
			lowLevelMapEntityDbs = screenDrawMapDataDao.getLowLevelMapEntityCityList(name);
		}

		if (null != lowLevelMapEntityDbs) {
			try {
				DecimalFormat dFormat = new DecimalFormat("0.00%");
				for (LowLevelMapEntity lowLevelMapEntity : lowLevelMapEntityDbs) {
					String securityIndexNum = "100";
					// 安全指标数组
					MapFormatSecurityArrayVO mapFormatSecurityArray = new MapFormatSecurityArrayVO();
					// 未解决紧急告警数
					int UWARNINGLEVEL0 = CommonCheck.returnInt(ScreenDrawMapDataService.class, "UWARNINGLEVEL0",
							String.valueOf(lowLevelMapEntity.getUnsolvedemergnum())).intValue();
					// 未解决重要告警数
					int UWARNINGLEVEL1 = CommonCheck.returnInt(ScreenDrawMapDataService.class, "UWARNINGLEVEL1",
							String.valueOf(lowLevelMapEntity.getUnsolvederrnum())).intValue();
					// 未解决一般告警数
					int UWARNINGLEVEL2 = CommonCheck.returnInt(ScreenDrawMapDataService.class, "UWARNINGLEVEL2",
							String.valueOf(lowLevelMapEntity.getUnsolvedwarnningnum())).intValue();
					// 明文流量
					BigDecimal decryptpackage = null == BigDecimal.valueOf(lowLevelMapEntity.getDecryptpackage())
							? new BigDecimal(0.00)
							: BigDecimal.valueOf(lowLevelMapEntity.getDecryptpackage());
					// 密文流量
					BigDecimal encryptpackage = null == BigDecimal.valueOf(lowLevelMapEntity.getEncryptpackage())
							? new BigDecimal(0.00)
							: BigDecimal.valueOf(lowLevelMapEntity.getEncryptpackage());
					// 密文明文流量总和
					BigDecimal totalPackage = decryptpackage.add(encryptpackage);
					// 密通率
					BigDecimal closeRate = new BigDecimal(1);
					if (totalPackage.compareTo(new BigDecimal(0.00)) != 0) {
						closeRate = encryptpackage.divide(totalPackage, 2, BigDecimal.ROUND_HALF_UP);
					}
					// 离线数
					int offline = CommonCheck.returnInt(ScreenDrawMapDataService.class, "closeRate",
							String.valueOf(lowLevelMapEntity.getOfflinedevicenum())).intValue();
					// 资产数
					int assetsNum = CommonCheck.returnInt(ScreenDrawMapDataService.class, "closeRate",
							String.valueOf(lowLevelMapEntity.getDevicenum())).intValue();
					// 资产在线率计算：当资产数为0时，资产在线率为‘0%’，否则，资产在线率为：（1-离线数/资产数）*100 （单位%），取小数点后2位。
					BigDecimal onlineRate = new BigDecimal(0);
					if (0 != assetsNum) {
						onlineRate = new BigDecimal(
								CommonCheck.returnRate2(ScreenDrawMapDataService.class, "onlineRate",
										(new BigDecimal(1).subtract(new BigDecimal(offline)
												.divide(new BigDecimal(assetsNum), 2, BigDecimal.ROUND_HALF_UP)))
												+ ""));
					}
					// 风险指标
					securityIndexNum = securityIndexNum2(UWARNINGLEVEL0, UWARNINGLEVEL1, UWARNINGLEVEL2, closeRate,
							onlineRate, subCheckVO.get(lowLevelMapEntity.getAreaname()));
					mapFormatSecurityArray.setName(StringFilterUtil.filterName(lowLevelMapEntity.getAreaname()));
					mapFormatSecurityArray.setValue(securityIndexNum);
					mapFormatSecurityArrays.add(mapFormatSecurityArray);
					// 省级其他指标
					MapFormatCenterIndexVO mapFormatCenterIndexVOProvnce = new MapFormatCenterIndexVO();
					mapFormatCenterIndexVOProvnce.setName(StringFilterUtil.filterName(lowLevelMapEntity.getAreaname()));
					if (subCheckVO.get(lowLevelMapEntity.getAreaname()) == null) {
						mapFormatCenterIndexVOProvnce.setSafetyCheckRate(dFormat.format(1.0000));
					} else {
						mapFormatCenterIndexVOProvnce
								.setSafetyCheckRate(dFormat.format(subCheckVO.get(lowLevelMapEntity.getAreaname())));
					}
					if (onlineRate.compareTo(new BigDecimal(0)) == 0) {
						mapFormatCenterIndexVOProvnce.setOnlineRate(dFormat.format(1.0000));
					} else {
						mapFormatCenterIndexVOProvnce.setOnlineRate(dFormat.format(onlineRate));
					}
					if (closeRate.compareTo(new BigDecimal(0)) == 0) {
						mapFormatCenterIndexVOProvnce.setSecretRate(dFormat.format(1.0000));
					} else {
						mapFormatCenterIndexVOProvnce.setSecretRate(dFormat.format(closeRate));
					}
					if (!toSolvedVO.containsKey(lowLevelMapEntity.getAreaname())) {
						mapFormatCenterIndexVOProvnce.setSafetyEventNums("0");
					} else {
						mapFormatCenterIndexVOProvnce
								.setSafetyEventNums(toSolvedVO.get(lowLevelMapEntity.getAreaname()));
					}
					centerIndexArray.add(mapFormatCenterIndexVOProvnce);
					// 计算分中心指标
					if (lowLevelMapEntity.getAreaname().indexOf("分中心") != -1 && StringUtils.isBlank(name)) {
						MapFormatCenterIndexVO mapFormatCenterIndexVO = new MapFormatCenterIndexVO();
						createCenterIndex(lowLevelMapEntity, mapFormatCenterIndexVO, centerCheckVO, centerSecretVO,
								centerOnlineVO, toSolvedCenterVO);
						centerIndexArray.add(mapFormatCenterIndexVO);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(ScreenDrawMapDataService.class, e.getMessage(), e);
			}
		}
		drawingMapsForParametersVO.setSecurityArray(mapFormatSecurityArrays);
		drawingMapsForParametersVO.setCenterIndexArray(centerIndexArray);
	}

	private void createCenterSecurityEventToSolvedNum(Map<String, String> toSolvedCenterVO) {
		List<ProvinceSecurityEventToSolvedNumVO> toSolvedNum = lastSubSecurityEventDao
				.getSubSecurityEventToSolvedNumByCenter();
		if (toSolvedNum != null) {
			for (ProvinceSecurityEventToSolvedNumVO toSolvedNumVO : toSolvedNum) {
				toSolvedCenterVO.put(toSolvedNumVO.getProvinceName(), toSolvedNumVO.getCount());
			}
		}

	}

	private void createOnlineRateMapByCenter(Map<String, BigDecimal> centerOnlineVO) {
		BigDecimal onlineRate = new BigDecimal(1.0000);
		List<RealtimeStatisticsAllEntity> realtimeStatisticsAlls = screenSecretPassRateDao
				.getRealtimeStatisticsAllCenter();
		if (realtimeStatisticsAlls != null) {
			for (RealtimeStatisticsAllEntity realtimeStatisticsAllEntity : realtimeStatisticsAlls) {
				if (realtimeStatisticsAllEntity != null) {
					if ((new BigDecimal(realtimeStatisticsAllEntity.getOnlinetime())
							.add(new BigDecimal(realtimeStatisticsAllEntity.getOnlinetime())))
									.compareTo(new BigDecimal(0)) > 0) {
						onlineRate = new BigDecimal(realtimeStatisticsAllEntity.getOnlinetime()).divide(
								new BigDecimal(realtimeStatisticsAllEntity.getOnlinetime())
										.add(new BigDecimal(realtimeStatisticsAllEntity.getOnlinetime())),
								4, BigDecimal.ROUND_HALF_UP);
					}
					centerOnlineVO.put(realtimeStatisticsAllEntity.getName(), onlineRate);
				}
			}
		}
	}

	private void createProvinceSecurityEventToSolvedNum(Map<String, String> toSolvedVO) {
		List<ProvinceSecurityEventToSolvedNumVO> toSolvedNum = lastSubSecurityEventDao
				.getSubSecurityEventToSolvedNumByProvince();
		if (toSolvedNum != null) {
			for (ProvinceSecurityEventToSolvedNumVO toSolvedNumVO : toSolvedNum) {
				toSolvedVO.put(toSolvedNumVO.getProvinceName(), toSolvedNumVO.getCount());
			}
		}
	}

	private void createSecretRateMapByCenter(Map<String, BigDecimal> centerSecretVO) {
		List<LowLevelMapEntity> lowLevelMapEntityDbs = screenDrawMapDataDao.getLowLevelMapEntityListByCenter();
		if (null != lowLevelMapEntityDbs) {
			try {
				for (LowLevelMapEntity lowLevelMapEntity : lowLevelMapEntityDbs) {
					// 明文流量
					BigDecimal decryptpackage = null == BigDecimal.valueOf(lowLevelMapEntity.getDecryptpackage())
							? new BigDecimal(0.00)
							: BigDecimal.valueOf(lowLevelMapEntity.getDecryptpackage());
					// 密文流量
					BigDecimal encryptpackage = null == BigDecimal.valueOf(lowLevelMapEntity.getEncryptpackage())
							? new BigDecimal(0.00)
							: BigDecimal.valueOf(lowLevelMapEntity.getEncryptpackage());
					// 密文明文流量总和
					BigDecimal totalPackage = decryptpackage.add(encryptpackage);
					// 密通率
					BigDecimal closeRate = new BigDecimal(1.0000);
					if (totalPackage.compareTo(new BigDecimal(0.0000)) != 0) {
						closeRate = encryptpackage.divide(totalPackage, 4, BigDecimal.ROUND_HALF_UP);
					}
					centerSecretVO.put(lowLevelMapEntity.getAreaname(), closeRate);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(ScreenDrawMapDataService.class, e.getMessage(), e);
			}
		}

	}

	private void createSafeRateMapByCenter(Map<String, BigDecimal> centerCheckVO) {
		List<SafetyRateSubEntity> safetyRateSubList = screenDrawMapDataDao.getSafetyRateSubByCenter();
		Map<String, SafetyRateSubEntity> map = new HashMap<>();
		int cvsLegalNumAll = 0;
		int cvsIlegalNumAll = 0;
		int vbsNumAll = 0;
		int vbsIlegalNumAll = 0;
		int userCheckNumAll = 0;
		int userCheckIlegalNumAll = 0;
		int veadTunnelNumAll = 0;
		int veadPolicyNumAll = 0;
		int veadIlegalTunnelNumAll = 0;
		int veadIlegalPolicyNumAll = 0;
		if (null != safetyRateSubList) {
			for (SafetyRateSubEntity safetyRateSubEntity : safetyRateSubList) {
				map.put(safetyRateSubEntity.getLocalName(), safetyRateSubEntity);

			}
			for (Entry<String, SafetyRateSubEntity> entry : map.entrySet()) {
				int cvsLegalNum = 0;
				int cvsIlegalNum = 0;
				int vbsNum = 0;
				int vbsIlegalNum = 0;
				int userCheckNum = 0;
				int userCheckIlegalNum = 0;
				int veadTunnelNum = 0;
				int veadPolicyNum = 0;
				int veadIlegalTunnelNum = 0;
				int veadIlegalPolicyNum = 0;
				for (SafetyRateSubEntity safetyRateSubEntity : safetyRateSubList) {
					if (entry.getKey().equals(safetyRateSubEntity.getLocalName())) {
						if (safetyRateSubEntity.getCheckDimen() == 1) {
							cvsLegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 2) {
							cvsIlegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 3) {
							vbsNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 4) {
							vbsIlegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 5) {
							userCheckNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 6) {
							userCheckIlegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 7) {
							veadTunnelNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 8) {
							veadIlegalTunnelNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 9) {
							veadPolicyNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 10) {
							veadIlegalPolicyNum += safetyRateSubEntity.getCheckCount();
						}
					}
				}
				// 安全配置合格率
				BigDecimal cvsLegalNumSubRate = new BigDecimal(0);
				if ((new BigDecimal(cvsLegalNum).add(new BigDecimal(cvsLegalNumAll))
						.add(new BigDecimal(cvsIlegalNum).add(new BigDecimal(cvsIlegalNumAll))))
								.compareTo(new BigDecimal(0)) > 0) {
					cvsLegalNumSubRate = (new BigDecimal(cvsLegalNum).add(new BigDecimal(cvsLegalNumAll)))
							.divide(new BigDecimal(cvsLegalNum).add(new BigDecimal(cvsLegalNumAll))
									.add(new BigDecimal(cvsIlegalNum).add(new BigDecimal(cvsIlegalNumAll))), 2,
									BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				// 风险合格率
				BigDecimal vbsNumSubRate = new BigDecimal(0);
				if (((new BigDecimal(vbsNumAll).add(new BigDecimal(vbsNum))
						.add(new BigDecimal(vbsIlegalNumAll).add(new BigDecimal(vbsIlegalNum))))
								.compareTo(new BigDecimal(0))) > 0) {
					vbsNumSubRate = (new BigDecimal(vbsNumAll).add(new BigDecimal(vbsNum)))
							.divide(new BigDecimal(vbsNumAll).add(new BigDecimal(vbsNum))
									.add(new BigDecimal(vbsIlegalNumAll).add(new BigDecimal(vbsIlegalNum))), 2,
									BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}

				// 弱口令扫描合格率
				BigDecimal userCheckNumSubRate = new BigDecimal(0);
				if (((new BigDecimal(userCheckNum).add(new BigDecimal(userCheckNumAll))
						.add(new BigDecimal(userCheckIlegalNumAll).add(new BigDecimal(userCheckIlegalNum))))
								.compareTo(new BigDecimal(0))) > 1) {
					userCheckNumSubRate = (new BigDecimal(userCheckNum).add(new BigDecimal(userCheckNumAll)))
							.divide(new BigDecimal(userCheckNum).add(new BigDecimal(userCheckNumAll))
									.add(new BigDecimal(userCheckIlegalNumAll).add(new BigDecimal(userCheckIlegalNum))),
									2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				// 隧道策略合格率
				// 核查纵向隧道数
				BigDecimal veadTunnelNumSubRate = new BigDecimal(0);
				if ((new BigDecimal(veadTunnelNum).add(new BigDecimal(veadTunnelNumAll))
						.add(new BigDecimal(veadPolicyNum)).add(new BigDecimal(veadPolicyNumAll))
						.add(new BigDecimal(veadIlegalTunnelNum).add(new BigDecimal(veadIlegalTunnelNumAll)))
						.add(new BigDecimal(veadIlegalPolicyNum)).add(new BigDecimal(veadIlegalPolicyNumAll)))
								.compareTo(new BigDecimal(0)) > 1) {
					veadTunnelNumSubRate = (new BigDecimal(veadTunnelNum).add(new BigDecimal(veadTunnelNumAll))
							.add(new BigDecimal(veadPolicyNum).add(new BigDecimal(veadPolicyNumAll))))
									.divide(new BigDecimal(veadTunnelNum).add(new BigDecimal(veadTunnelNumAll))
											.add(new BigDecimal(veadPolicyNum)).add(new BigDecimal(veadPolicyNumAll))
											.add(new BigDecimal(veadIlegalTunnelNum)
													.add(new BigDecimal(veadIlegalTunnelNumAll)))
											.add(new BigDecimal(veadIlegalPolicyNum))
											.add(new BigDecimal(veadIlegalPolicyNumAll)), 2, BigDecimal.ROUND_HALF_UP)
									.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				if ((((cvsLegalNumSubRate.add(vbsNumSubRate).add(userCheckNumSubRate).add(veadTunnelNumSubRate)))
						.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
								.compareTo(new BigDecimal(0.00)) == 0) {
					centerCheckVO.put(entry.getKey(), new BigDecimal(1));
				} else {
					centerCheckVO.put(entry.getKey(),
							((cvsLegalNumSubRate.add(vbsNumSubRate).add(userCheckNumSubRate).add(veadTunnelNumSubRate)))
									.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}

	}

	private void createCenterIndex(LowLevelMapEntity lowLevelMapEntity, MapFormatCenterIndexVO mapFormatCenterIndexVO,
			Map<String, BigDecimal> centerCheckVO, Map<String, BigDecimal> centerSecretVO,
			Map<String, BigDecimal> centerOnlineVO, Map<String, String> toSolvedCenterVO) {
		DecimalFormat dFormat = new DecimalFormat("0.00%");
		mapFormatCenterIndexVO.setName(StringFilterUtil.filterName(lowLevelMapEntity.getAreaname()));
		if (!centerCheckVO.containsKey(lowLevelMapEntity.getAreaname())) {
			mapFormatCenterIndexVO.setSafetyCheckRate(dFormat.format(1.0000));
		} else {
			mapFormatCenterIndexVO
					.setSafetyCheckRate(dFormat.format(centerCheckVO.get(lowLevelMapEntity.getAreaname())));
		}
		if (!centerSecretVO.containsKey(lowLevelMapEntity.getAreaname())) {
			mapFormatCenterIndexVO.setSecretRate(dFormat.format(1.0000));
		} else {
			mapFormatCenterIndexVO.setSecretRate(dFormat.format(centerSecretVO.get(lowLevelMapEntity.getAreaname())));
		}
		if (!centerOnlineVO.containsKey(lowLevelMapEntity.getAreaname())) {
			mapFormatCenterIndexVO.setOnlineRate(dFormat.format(1.0000));
		} else {
			mapFormatCenterIndexVO.setOnlineRate(dFormat.format(centerOnlineVO.get(lowLevelMapEntity.getAreaname())));
		}
		if (!toSolvedCenterVO.containsKey(lowLevelMapEntity.getAreaname())) {
			mapFormatCenterIndexVO.setSafetyEventNums("0");
		} else {
			mapFormatCenterIndexVO.setSafetyEventNums(toSolvedCenterVO.get(lowLevelMapEntity.getAreaname()));
		}

	}

	private void createSafeRateMap(Map<String, BigDecimal> subCheckVO, String name) {
		List<SafetyRateLocalEntity> safetyRateLocalList = new ArrayList<>();
		List<SafetyRateSubEntity> safetyRateSubList = new ArrayList<>();
		if (StringUtils.isBlank(name)) {
			safetyRateLocalList = screenDrawMapDataDao.getSafetyRateLocal();
		}
		safetyRateSubList = screenDrawMapDataDao.getSafetyRateSub(name);
		Map<String, SafetyRateSubEntity> map = new HashMap<>();
		int cvsLegalNumAll = 0;
		int cvsIlegalNumAll = 0;
		int vbsNumAll = 0;
		int vbsIlegalNumAll = 0;
		int userCheckNumAll = 0;
		int userCheckIlegalNumAll = 0;
		int veadTunnelNumAll = 0;
		int veadPolicyNumAll = 0;
		int veadIlegalTunnelNumAll = 0;
		int veadIlegalPolicyNumAll = 0;
		if (StringUtils.isBlank(name)) {
			if (null != safetyRateLocalList && safetyRateLocalList.size() > 0) {
				SafetyRateLocalEntity safetyRateLocalEntity = safetyRateLocalList.get(0);
				// 基线核查合规项个数
				cvsLegalNumAll = safetyRateLocalEntity.getCvsLegalNum();
				// 基线核查不合规项个数
				cvsIlegalNumAll = safetyRateLocalEntity.getCvsIlegalNum();
				// 漏洞扫描主机设备个数
				vbsNumAll = safetyRateLocalEntity.getVbsNum();
				// 漏洞扫描不合规主机设备个数
				vbsIlegalNumAll = safetyRateLocalEntity.getVbsIlegalNum();
				// 弱口令扫描主机数
				userCheckNumAll = safetyRateLocalEntity.getUserCheckNum();
				// 弱口令扫描不合规主机数
				userCheckIlegalNumAll = safetyRateLocalEntity.getUserCheckIlegalNum();
				// 核查纵向隧道数
				veadTunnelNumAll = safetyRateLocalEntity.getVeadTunnelNum();
				// 核查纵向策略数
				veadPolicyNumAll = safetyRateLocalEntity.getVeadPolicyNum();
				// 核查纵向问题隧道数
				veadIlegalTunnelNumAll = safetyRateLocalEntity.getVeadIlegalTunnelNum();
				// 核查纵向问题策略数
				veadIlegalPolicyNumAll = safetyRateLocalEntity.getVeadIlegalPolicyNum();
			}
		}
		if (null != safetyRateSubList) {
			for (SafetyRateSubEntity safetyRateSubEntity : safetyRateSubList) {
				map.put(safetyRateSubEntity.getLocalName(), safetyRateSubEntity);

			}
			for (Entry<String, SafetyRateSubEntity> entry : map.entrySet()) {
				int cvsLegalNum = 0;
				int cvsIlegalNum = 0;
				int vbsNum = 0;
				int vbsIlegalNum = 0;
				int userCheckNum = 0;
				int userCheckIlegalNum = 0;
				int veadTunnelNum = 0;
				int veadPolicyNum = 0;
				int veadIlegalTunnelNum = 0;
				int veadIlegalPolicyNum = 0;
				for (SafetyRateSubEntity safetyRateSubEntity : safetyRateSubList) {
					if (entry.getKey().equals(safetyRateSubEntity.getLocalName())) {
						if (safetyRateSubEntity.getCheckDimen() == 1) {
							cvsLegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 2) {
							cvsIlegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 3) {
							vbsNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 4) {
							vbsIlegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 5) {
							userCheckNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 6) {
							userCheckIlegalNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 7) {
							veadTunnelNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 8) {
							veadIlegalTunnelNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 9) {
							veadPolicyNum += safetyRateSubEntity.getCheckCount();
						}
						if (safetyRateSubEntity.getCheckDimen() == 10) {
							veadIlegalPolicyNum += safetyRateSubEntity.getCheckCount();
						}
					}
				}
				// 安全配置合格率
				BigDecimal cvsLegalNumSubRate = new BigDecimal(0);
				if ((new BigDecimal(cvsLegalNum).add(new BigDecimal(cvsLegalNumAll))
						.add(new BigDecimal(cvsIlegalNum).add(new BigDecimal(cvsIlegalNumAll))))
								.compareTo(new BigDecimal(0)) > 0) {
					cvsLegalNumSubRate = (new BigDecimal(cvsLegalNum).add(new BigDecimal(cvsLegalNumAll)))
							.divide(new BigDecimal(cvsLegalNum).add(new BigDecimal(cvsLegalNumAll))
									.add(new BigDecimal(cvsIlegalNum).add(new BigDecimal(cvsIlegalNumAll))), 2,
									BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				// 风险合格率
				BigDecimal vbsNumSubRate = new BigDecimal(0);
				if (((new BigDecimal(vbsNumAll).add(new BigDecimal(vbsNum))
						.add(new BigDecimal(vbsIlegalNumAll).add(new BigDecimal(vbsIlegalNum))))
								.compareTo(new BigDecimal(0))) > 0) {
					vbsNumSubRate = (new BigDecimal(vbsNumAll).add(new BigDecimal(vbsNum)))
							.divide(new BigDecimal(vbsNumAll).add(new BigDecimal(vbsNum))
									.add(new BigDecimal(vbsIlegalNumAll).add(new BigDecimal(vbsIlegalNum))), 2,
									BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}

				// 弱口令扫描合格率
				BigDecimal userCheckNumSubRate = new BigDecimal(0);
				if (((new BigDecimal(userCheckNum).add(new BigDecimal(userCheckNumAll))
						.add(new BigDecimal(userCheckIlegalNumAll).add(new BigDecimal(userCheckIlegalNum))))
								.compareTo(new BigDecimal(0))) > 1) {
					userCheckNumSubRate = (new BigDecimal(userCheckNum).add(new BigDecimal(userCheckNumAll)))
							.divide(new BigDecimal(userCheckNum).add(new BigDecimal(userCheckNumAll))
									.add(new BigDecimal(userCheckIlegalNumAll).add(new BigDecimal(userCheckIlegalNum))),
									2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				// 隧道策略合格率
				// 核查纵向隧道数
				BigDecimal veadTunnelNumSubRate = new BigDecimal(0);
				if ((new BigDecimal(veadTunnelNum).add(new BigDecimal(veadTunnelNumAll))
						.add(new BigDecimal(veadPolicyNum)).add(new BigDecimal(veadPolicyNumAll))
						.add(new BigDecimal(veadIlegalTunnelNum).add(new BigDecimal(veadIlegalTunnelNumAll)))
						.add(new BigDecimal(veadIlegalPolicyNum)).add(new BigDecimal(veadIlegalPolicyNumAll)))
								.compareTo(new BigDecimal(0)) > 1) {
					veadTunnelNumSubRate = (new BigDecimal(veadTunnelNum).add(new BigDecimal(veadTunnelNumAll))
							.add(new BigDecimal(veadPolicyNum).add(new BigDecimal(veadPolicyNumAll))))
									.divide(new BigDecimal(veadTunnelNum).add(new BigDecimal(veadTunnelNumAll))
											.add(new BigDecimal(veadPolicyNum)).add(new BigDecimal(veadPolicyNumAll))
											.add(new BigDecimal(veadIlegalTunnelNum)
													.add(new BigDecimal(veadIlegalTunnelNumAll)))
											.add(new BigDecimal(veadIlegalPolicyNum))
											.add(new BigDecimal(veadIlegalPolicyNumAll)), 2, BigDecimal.ROUND_HALF_UP)
									.multiply(new BigDecimal(25)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				subCheckVO.put(entry.getKey(),
						((cvsLegalNumSubRate.add(vbsNumSubRate).add(userCheckNumSubRate).add(veadTunnelNumSubRate)))
								.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
			}
		}

	}

	// 计算风险控制指标
	private String securityIndexNum(int UWARNINGLEVEL0, int UWARNINGLEVEL1, int UWARNINGLEVEL2, BigDecimal closeRate,
			BigDecimal onlineRate) {
		// 风险指标的计算公式= Min((未解决紧急告警数*0.5+未解决重要告警数*0.1+未解决一般告警数*0.05)，40)+（
		// 1-密通率）*30+（1-在线率）*30
		double min = (UWARNINGLEVEL0 * 0.5 + UWARNINGLEVEL1 * 0.1 + UWARNINGLEVEL2 * 0.05) > 40 ? 40
				: (UWARNINGLEVEL0 * 0.5 + UWARNINGLEVEL1 * 0.1 + UWARNINGLEVEL2 * 0.05);
		BigDecimal securityValue = new BigDecimal(min)
				.add((((new BigDecimal(1).subtract(closeRate).setScale(2, BigDecimal.ROUND_HALF_UP))
						.multiply(new BigDecimal(30)))
								.add(((new BigDecimal(1).subtract(onlineRate).setScale(2, BigDecimal.ROUND_HALF_UP))
										.multiply(new BigDecimal(30))))))
				.setScale(0, BigDecimal.ROUND_HALF_UP);
		if (securityValue.compareTo(new BigDecimal(100)) > 0) {
			securityValue = new BigDecimal(100);
		}
		return String.valueOf(securityValue);
	}

	// 计算安全控制指标
	private String securityIndexNum2(int UWARNINGLEVEL0, int UWARNINGLEVEL1, int UWARNINGLEVEL2, BigDecimal closeRate,
			BigDecimal onlineRate, BigDecimal checkValue) {
		// 安全指标公式：告警指标25分+在线指标25分+密通指标25分+核查指标25分
		// 核查指标=安全配置合格率*6.25+风险合格率*6.25+弱口令扫描合格率*6.25+隧道策略合格率*6.25
		// 告警指标
		double min = 25 - ((UWARNINGLEVEL0 * 0.05 + UWARNINGLEVEL1 * 0.01) > 25 ? 25
				: (UWARNINGLEVEL0 * 0.05 + UWARNINGLEVEL1 * 0.01));
		// 在线指标
		double onlineValueRate = (onlineRate.multiply(new BigDecimal(25))).doubleValue();
		// 密通指标
		double closeValueRate = (closeRate.multiply(new BigDecimal(25))).doubleValue();
		// 核查指标
		if (null == checkValue) {
			checkValue = new BigDecimal(0.00);
		}
		BigDecimal securityValue = new BigDecimal(min).add(new BigDecimal(onlineValueRate))
				.add(new BigDecimal(closeValueRate)).add(checkValue).setScale(0, BigDecimal.ROUND_HALF_UP);
		if (securityValue.compareTo(new BigDecimal(100)) > 0) {
			securityValue = new BigDecimal(100);
		}
		return String.valueOf(securityValue);
	}

	private BigDecimal returnBigDecimal(Class<? extends Object> clazz, String str, String value) {
		try {
			if (StringUtils.isBlank(value) || value.equals("null")) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值为空");
				str = String.valueOf(0);
			} else if (Integer.parseInt(value) <= 0) {
				Log4jUtil.fault("[" + clazz + "]" + str + "值小于等于0：" + value);
				str = String.valueOf(0);
			} else {
				str = value;
			}
		} catch (NumberFormatException e) {
			Log4jUtil.fault("[" + clazz + "]" + str + "值异常：" + value);
			str = String.valueOf(0);
		}
		return new BigDecimal(str);
	}
}

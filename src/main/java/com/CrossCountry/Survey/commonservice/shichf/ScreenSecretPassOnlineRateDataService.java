package com.CrossCountry.Survey.commonservice.shichf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.commonservice.serviceutil.DataChangePublicService;
import com.CrossCountry.Survey.mapper.shichf.ScreenSecretPassRateDao;
import com.CrossCountry.Survey.modelvo.CommonArgs;
import com.CrossCountry.Survey.modelvo.shichf.OnlineRateEntity;
import com.CrossCountry.Survey.modelvo.shichf.RealtimeStatisticsAllEntity;
import com.CrossCountry.Survey.modelvo.shichf.ScreenSecretPassOnlineRateForParametersVO;
import com.CrossCountry.Survey.modelvo.shichf.ScreenSecretPassOnlineRateProtobufVO;
import com.CrossCountry.Survey.modelvo.shichf.SecretPassRate;
import com.CrossCountry.Survey.thirdparty.GoogleCacheUtils;
import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class ScreenSecretPassOnlineRateDataService {
	@Autowired
	private ScreenSecretPassRateDao screenSecretPassRateDao;
	@Autowired
	private DataChangePublicService dataChangePublicService;
	@Autowired
	GoogleCacheUtils cacheUtils;

	public ScreenSecretPassOnlineRateForParametersVO getScreenSecretPassOnlineRateForParametersVO(Object object) {
		ScreenSecretPassOnlineRateForParametersVO screenSecretPassOnlineRateForParametersVO = new ScreenSecretPassOnlineRateForParametersVO();
		List<ScreenSecretPassOnlineRateProtobufVO> secretPassOnlineRateProtobufVOs = new ArrayList<>();
		CommonArgs commonArgs = (CommonArgs) object;
		String key = commonArgs.getKey();
		String[] keyarray = key.split(",");
		String secretkey = keyarray[0];
		String onlinekey = keyarray[1];
		String nameNew = dataChangePublicService.mapRegionNameChange(commonArgs.getName());
		// 计算密通率，返回全网密通率
		String allSecretPass = createSecretPassRate(secretPassOnlineRateProtobufVOs, nameNew, secretkey);
		// 计算在线率，返回全网在线率
		String allOnlineRate = createOnlineRate(secretPassOnlineRateProtobufVOs, nameNew, onlinekey);
		// 计算全网紧急告警数
		String urgentWarningsCount = createUrgentWarningCount(nameNew);
		// 计算全网重要告警数
		String importantWarningsCount = createImportantWarningCount(nameNew);
		// 计算安全运行天数
		String secrityDays = createSecrityDays();
		if (StringUtils.isBlank(commonArgs.getName())) {
			screenSecretPassOnlineRateForParametersVO.setOnlineName("全网设备在线率");
			screenSecretPassOnlineRateForParametersVO.setSecretPassName("全网纵向密通水平");
			screenSecretPassOnlineRateForParametersVO.setUrgentWarningsName("紧急告警数");
			screenSecretPassOnlineRateForParametersVO.setImportantWarningsName("重要告警数");
		} else {
			screenSecretPassOnlineRateForParametersVO.setOnlineName(commonArgs.getName() + "设备在线率");
			screenSecretPassOnlineRateForParametersVO.setSecretPassName(commonArgs.getName() + "纵向密通水平");
			screenSecretPassOnlineRateForParametersVO.setUrgentWarningsName(commonArgs.getName() + "紧急告警数");
			screenSecretPassOnlineRateForParametersVO.setImportantWarningsName(commonArgs.getName() + "重要告警数");
		}
		screenSecretPassOnlineRateForParametersVO.setAllOnlineRate(allOnlineRate);
		screenSecretPassOnlineRateForParametersVO.setAllSecretPass(allSecretPass);
		screenSecretPassOnlineRateForParametersVO.setUrgentWarningsCount(urgentWarningsCount);
		screenSecretPassOnlineRateForParametersVO.setImportantWarningsCount(importantWarningsCount);
		screenSecretPassOnlineRateForParametersVO.setSecrityDays(secrityDays);
		// 如果与上次数据一致在线率密通率返回空
		boolean secretboolean = true;
		boolean onlineboolean = true;
		if (cacheUtils.getCacheValue("secretboolean") != null) {
			secretboolean = (boolean) cacheUtils.getCacheValue("secretboolean");
		}
		if (cacheUtils.getCacheValue("onlineboolean") != null) {
			onlineboolean = (boolean) cacheUtils.getCacheValue("onlineboolean");
		}
		if (!secretboolean && !onlineboolean) {
			screenSecretPassOnlineRateForParametersVO.setList(null);
		} else {
			screenSecretPassOnlineRateForParametersVO.setList(secretPassOnlineRateProtobufVOs);
		}
		return screenSecretPassOnlineRateForParametersVO;
	}

	private String createSecrityDays() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String secrityDays = screenSecretPassRateDao.getSecrityDays();
		try {
			if (StringUtils.isBlank(secrityDays)) {
				return "0";
			}
			if (!"2019-01-01".equals(secrityDays)) {
				return "0";
			}
			Date sd = sf.parse(secrityDays);
			Date nowDate = new Date();
			long nowday = nowDate.getTime();
			long oldDay = sd.getTime();
			long time = nowday - oldDay;
			if (time < 0) {
				return "安全运行天数计算错误，请联系管理员";
			} else {
				return String.valueOf(new BigDecimal(time)
						.divide(new BigDecimal(1000 * 60 * 60 * 24), 0, BigDecimal.ROUND_DOWN).add(new BigDecimal(1)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			Log4jUtil.error(ScreenSecretPassOnlineRateDataService.class, e.getMessage(), e);
			return "安全运行天数计算错误，请联系管理员";
		}

	}

	private String createImportantWarningCount(String name) {
		Integer importWarningsLocalCount = screenSecretPassRateDao.getImportantWarningsLocalCount();
		Integer importWarningsLowCount = screenSecretPassRateDao.getImportantWarningsLowCount(name);
		importWarningsLocalCount = null == importWarningsLocalCount ? 0 : importWarningsLocalCount.intValue();
		importWarningsLowCount = null == importWarningsLowCount ? 0 : importWarningsLowCount.intValue();
		if (StringUtils.isBlank(name)) {
			return String.valueOf(importWarningsLocalCount + importWarningsLowCount);
		} else {
			return String.valueOf(importWarningsLowCount);
		}

	}

	private String createUrgentWarningCount(String name) {
		Integer urgentWarningsLocalCount = screenSecretPassRateDao.getUrgentWarningsLocalCount();
		Integer urgentWarningsLowCount = screenSecretPassRateDao.getUrgentWarningsLowCount(name);
		urgentWarningsLocalCount = null == urgentWarningsLocalCount ? 0 : urgentWarningsLocalCount.intValue();
		urgentWarningsLowCount = null == urgentWarningsLowCount ? 0 : urgentWarningsLowCount.intValue();
		if (StringUtils.isBlank(name)) {
			return String.valueOf(urgentWarningsLocalCount + urgentWarningsLowCount);
		} else {
			return String.valueOf(urgentWarningsLowCount);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String createOnlineRate(List<ScreenSecretPassOnlineRateProtobufVO> secretPassOnlineRateProtobufVOs,
			String name, String onlinekey) {
		if (StringUtils.isBlank(name)) {
			// 全网在线率
			BigDecimal allOnlineRate = new BigDecimal(0);
			// 全网在线数
			BigDecimal allOnline = new BigDecimal(0);
			// 全网在线数离线数总和
			BigDecimal allLine = new BigDecimal(0);
			DecimalFormat dFormat = new DecimalFormat("0.00%");
			ScreenSecretPassOnlineRateProtobufVO screenSecretPassOnlineRateProtobufVO = new ScreenSecretPassOnlineRateProtobufVO();
			List<Map> maps = new ArrayList<Map>();
			try {
				Calendar now = Calendar.getInstance(Locale.CANADA);
				int point = Integer.valueOf(
						String.valueOf(new BigDecimal(now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE))
								.divide(new BigDecimal(15), 0, BigDecimal.ROUND_DOWN)));
				List<OnlineRateEntity> list = screenSecretPassRateDao.getOnlineRateEntityList(name);
				// int point = list.size();
				String[] array = new String[point];
				for (OnlineRateEntity li : list) {
					Map map = new HashMap();
					map.put("stime", li.getStime());
					map.put("offline", li.getOffline());
					map.put("online", li.getOnline());
					map.put("traffic", li.getTotal());
					allLine = allLine.add(new BigDecimal(li.getTotal()));
					allOnline = allOnline.add(new BigDecimal(li.getOnline()));
					maps.add(map);
				}
				// 计算下级在线率
				List<Map> mapslow = new ArrayList<>();
				// createLowOnlineRate2(point, mapslow, name);
				createLowOnlineRate(mapslow, name);
				for (int i = 0; i < mapslow.size(); i++) {
					double onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
					double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
					allLine = allLine.add(new BigDecimal(trafficlow));
					allOnline = allOnline.add(new BigDecimal(onlinelow));
				}
				List<String> timeArrayEncrypt = new ArrayList<String>();
				List<String> encrryptpackageCurve = new ArrayList<String>();
				/**
				 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
				 * 第八行：911到96
				 */
				String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15",
						"02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45",
						"05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15",
						"07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45",
						"10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15",
						"12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45",
						"15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15",
						"17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45",
						"20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15",
						"22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
				final List<String> definedOrder = Arrays.asList(defined);
				if (maps.size() > 0) {
					int j = 1;
					for (int i = 0; i < maps.size(); i++) {
						for (; j <= point;) {
							int stime = Integer.parseInt(maps.get(i).get("stime").toString());
							double online = Double.parseDouble(maps.get(i).get("online").toString());
							double traffic = Double.parseDouble(maps.get(i).get("traffic").toString());
							double onlinelow = 0;
							double trafficlow = 0;
							if (mapslow.size() < maps.size()) {
								if (i < mapslow.size()) {
									onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
									trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
								}
							} else {
								onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
								trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
							}
							online = online + onlinelow;
							traffic = traffic + trafficlow;
							DecimalFormat formater = new DecimalFormat("#0.00");
							formater.setRoundingMode(RoundingMode.FLOOR);
							if (j == stime) {
								if (online == 0 && traffic == 0) {
									array[stime - 1] = "100.00";
								} else if (online != 0 && traffic == 0) {
									array[stime - 1] = "100.00";
								} else {
									array[stime - 1] = formater.format(new BigDecimal(online)
											.divide(new BigDecimal(traffic), 4, BigDecimal.ROUND_HALF_UP)
											.multiply(new BigDecimal(100)));

								}
								j++;
								break;
							}
							j++;
//							else {
//								if (trafficlow == 0) {
//									array[j - 1] = "100.00";
//								} else {
//									array[j - 1] = formater.format(new BigDecimal(onlinelow)
//											.divide(new BigDecimal(trafficlow), 4, BigDecimal.ROUND_HALF_UP)
//											.multiply(new BigDecimal(100)));
//
//								}
//								j++;
//								continue;
//							}
						}
					}
					for (int k = 0; k < array.length; k++) {
						if (array[k] != null) {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(array[k]);
						} else {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(createBeforeNextRate(array, k));

						}
					}
				} else {
					for (int j = 0; j < array.length; j++) {
						timeArrayEncrypt.add(definedOrder.get(j));
						encrryptpackageCurve.add("100.00");
					}
				}
				if (allLine.compareTo(new BigDecimal(0)) == 0) {
					allOnlineRate = new BigDecimal(1.0000);
				} else {
					allOnlineRate = allOnline.divide(allLine, 4, BigDecimal.ROUND_HALF_UP);
				}

				screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
				screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
				screenSecretPassOnlineRateProtobufVO.setName("网络安全设备在线率");
				screenSecretPassOnlineRateProtobufVO.setColor("#1BF7FF");
				secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
				ScreenSecretPassOnlineRateProtobufVO onlineValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
						.getCacheValue(onlinekey);
				if (onlineValueOrginal == null) {
					cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
					cacheUtils.putCacheValue("onlineboolean", true);
				} else {
					List<String> timeOrgial = onlineValueOrginal.getTimeArrayEncrypt();
					if(timeOrgial == null) {
						timeOrgial = new ArrayList<>();
					}
					List<String> secretOrginal = onlineValueOrginal.getEncrryptpackageCurve();
					if(secretOrginal == null) {
						secretOrginal = new ArrayList<>();
					}
					List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
					if(timeNew == null) {
						timeNew = new ArrayList<>();
					}
					List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
					if(secretNew == null) {
						secretNew = new ArrayList<>();
					}
					if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
							&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
						cacheUtils.putCacheValue("onlineboolean", false);
					} else {
						cacheUtils.putCacheValue("onlineboolean", true);
						cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(ScreenSecretPassOnlineRateDataService.class, e.getMessage(), e);
			}
			return String.valueOf(dFormat.format(allOnlineRate));
		} else {
			// 全省在线率
			BigDecimal allOnlineRate = new BigDecimal(0);
			// 省级在线数
			BigDecimal allOnline = new BigDecimal(0);
			// 省级在线数离线数总和
			BigDecimal allLine = new BigDecimal(0);
			DecimalFormat dFormat = new DecimalFormat("0.00%");
			ScreenSecretPassOnlineRateProtobufVO screenSecretPassOnlineRateProtobufVO = new ScreenSecretPassOnlineRateProtobufVO();
			try {
				Calendar now = Calendar.getInstance(Locale.CANADA);
				int point = Integer.valueOf(
						String.valueOf(new BigDecimal(now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE))
								.divide(new BigDecimal(15), 0, BigDecimal.ROUND_DOWN)));
				String[] array = new String[point];
				List<RealtimeStatisticsAllEntity> realtimeStatisticsAllEntitys = screenSecretPassRateDao
						.getRealtimeStatisticsAllEntityByName(name);
				List<Map> mapsProvnce = new ArrayList<>();
				if (realtimeStatisticsAllEntitys != null) {
					for (RealtimeStatisticsAllEntity realtimeStatistics : realtimeStatisticsAllEntitys) {
						Map mapProvnce = new HashMap();
						mapProvnce.put("stime", realtimeStatistics.getStime());
						mapProvnce.put("online", realtimeStatistics.getOnlinetime());
						mapProvnce.put("traffic", Double.valueOf(realtimeStatistics.getOnlinetime())
								+ Double.valueOf(realtimeStatistics.getOutlinetime()));
						allLine = allLine.add(new BigDecimal(realtimeStatistics.getOnlinetime())
								.add(new BigDecimal(realtimeStatistics.getOutlinetime())));
						allOnline = allOnline.add(new BigDecimal(realtimeStatistics.getOnlinetime()));
						mapsProvnce.add(mapProvnce);
					}
				}
				// 计算下级在线率
				List<Map> mapslow = new ArrayList<>();
				List<RealtimeStatisticsAllEntity> list = screenSecretPassRateDao
						.getRealtimeStatisticsAllListByStime(name);
				for (RealtimeStatisticsAllEntity realtimeStatisticsAllEntity : list) {
					Map maplow = new HashMap();
					maplow.put("stimelow", realtimeStatisticsAllEntity.getStime());
					maplow.put("onlinelow", realtimeStatisticsAllEntity.getOnlinetime());
					maplow.put("trafficlow", Double.valueOf(realtimeStatisticsAllEntity.getOnlinetime())
							+ Double.valueOf(realtimeStatisticsAllEntity.getOutlinetime()));
					mapslow.add(maplow);
				}
				// int point = list.size();
				// createLowOnlineRate2(point, mapslow, name);
				for (int i = 0; i < mapslow.size(); i++) {
					double onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
					double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
					allLine = allLine.add(new BigDecimal(trafficlow));
					allOnline = allOnline.add(new BigDecimal(onlinelow));
				}
				List<String> timeArrayEncrypt = new ArrayList<String>();
				List<String> encrryptpackageCurve = new ArrayList<String>();
				/**
				 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
				 * 第八行：911到96
				 */
				String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15",
						"02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45",
						"05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15",
						"07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45",
						"10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15",
						"12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45",
						"15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15",
						"17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45",
						"20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15",
						"22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
				final List<String> definedOrder = Arrays.asList(defined);
				if (mapslow.size() > 0) {
					int j = 1;
					for (int i = 0; i < mapslow.size(); i++) {
						for (; j <= point;) {
							int stime = Integer.parseInt(mapsProvnce.get(i).get("stime").toString());
							double online = Double.parseDouble(mapsProvnce.get(i).get("online").toString());
							double traffic = Double.parseDouble(mapsProvnce.get(i).get("traffic").toString());
							double onlinelow = 0;
							double trafficlow = 0;
							if (mapslow.size() < mapsProvnce.size()) {
								if (i < mapslow.size()) {
									onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
									trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
								}
							} else {
								onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
								trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
							}
							online = online + onlinelow;
							traffic = traffic + trafficlow;
							DecimalFormat formater = new DecimalFormat("#0.00");
							formater.setRoundingMode(RoundingMode.FLOOR);
							if (j == stime) {
								if (onlinelow == 0 && trafficlow == 0) {
									array[stime - 1] = "100.00";
								} else if (onlinelow != 0 && trafficlow == 0) {
									array[stime - 1] = "100.00";
								} else {
									array[stime - 1] = formater.format(new BigDecimal(onlinelow)
											.divide(new BigDecimal(trafficlow), 4, BigDecimal.ROUND_HALF_UP)
											.multiply(new BigDecimal(100)));

								}
								j++;
								break;
							} else {
								j++;
							}
//							else {
//								if (trafficlow == 0) {
//									array[j - 1] = "100.00";
//								} else {
//									array[j - 1] = formater.format(new BigDecimal(onlinelow)
//											.divide(new BigDecimal(trafficlow), 4, BigDecimal.ROUND_HALF_UP)
//											.multiply(new BigDecimal(100)));
//								}
//								j++;
//								continue;
//							}
						}
					}
					for (int k = 0; k < array.length; k++) {
						if (array[k] != null) {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(array[k]);
						} else {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(createBeforeNextRate(array, k));
						}
					}
				} else {
					for (int j = 0; j < array.length; j++) {
						timeArrayEncrypt.add(definedOrder.get(j));
						encrryptpackageCurve.add("100.00");
					}
				}
				if (allLine.compareTo(new BigDecimal(0)) == 0) {
					allOnlineRate = new BigDecimal(1.0000);
				} else {
					allOnlineRate = allOnline.divide(allLine, 4, BigDecimal.ROUND_HALF_UP);
				}

				screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
				screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
				screenSecretPassOnlineRateProtobufVO.setName("网络安全设备在线率");
				screenSecretPassOnlineRateProtobufVO.setColor("#1BF7FF");
				secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
				ScreenSecretPassOnlineRateProtobufVO onlineValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
						.getCacheValue(onlinekey);
				if (onlineValueOrginal == null) {
					cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
					cacheUtils.putCacheValue("onlineboolean", true);
				} else {
					List<String> timeOrgial = onlineValueOrginal.getTimeArrayEncrypt();
					if(timeOrgial == null) {
						timeOrgial = new ArrayList<>();
					}
					List<String> secretOrginal = onlineValueOrginal.getEncrryptpackageCurve();
					if(secretOrginal == null) {
						secretOrginal = new ArrayList<>();
					}
					List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
					if(timeNew == null) {
						timeNew = new ArrayList<>();
					}
					List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
					if(secretNew == null) {
						secretNew = new ArrayList<>();
					}
					if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
							&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
						cacheUtils.putCacheValue("onlineboolean", false);
					} else {
						cacheUtils.putCacheValue("onlineboolean", true);
						cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(ScreenSecretPassOnlineRateDataService.class, e.getMessage(), e);
			}
			return String.valueOf(dFormat.format(allOnlineRate));
//			// 下级在线率
//			BigDecimal allOnlineRate = new BigDecimal(0);
//			// 下级在线数
//			BigDecimal allOnline = new BigDecimal(0);
//			// 下级在线数离线数总和
//			BigDecimal allLine = new BigDecimal(0);
//			// 省级在线数
//			BigDecimal onlineProvince = new BigDecimal(0);
//			// 省级离线数
//			BigDecimal outlineProvince = new BigDecimal(0);
//			DecimalFormat dFormat = new DecimalFormat("0.00%");
//			ScreenSecretPassOnlineRateProtobufVO screenSecretPassOnlineRateProtobufVO = new ScreenSecretPassOnlineRateProtobufVO();
//			try {
//				List<String> list = screenSecretPassRateDao.getRealtimeStatisticsAllListLowByName(name);
//				if (list != null && list.size() > 0) {
//					String timebegin = list.get(0);
//					String timeend = list.get(list.size() - 1);
//					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Date timebeginDate = sf.parse(timebegin);
//					Date timeendDate = sf.parse(timeend);
//					long time = timeendDate.getTime() - timebeginDate.getTime();
//					int point = Integer.valueOf(String.valueOf(new BigDecimal(time)
//							.divide(new BigDecimal(1000 * 60 * 15), 0, BigDecimal.ROUND_DOWN).add(new BigDecimal(1))));
//					String[] array = new String[point];
//					// 计算下级在线率
//					List<Map> mapslow = new ArrayList<>();
//					// createLowOnlineRate2(point, mapslow, name);
//					createLowOnlineRate(mapslow, name);
//					RealtimeStatisticsAllEntity realtimeStatisticsAllEntity = screenSecretPassRateDao
//							.getRealtimeStatisticsAllEntityByName(name);
//					if (realtimeStatisticsAllEntity != null) {
//						onlineProvince = new BigDecimal(realtimeStatisticsAllEntity.getOnlinetime());
//						outlineProvince = new BigDecimal(realtimeStatisticsAllEntity.getOutlinetime());
//					}
//					for (int i = 0; i < mapslow.size(); i++) {
//						double onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString());
//						double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
//						allLine = allLine.add(new BigDecimal(trafficlow));
//						allOnline = allOnline.add(new BigDecimal(onlinelow));
//					}
//					allLine = allLine.add(onlineProvince).add(outlineProvince);
//					allOnline = allOnline.add(onlineProvince);
//					List<String> timeArrayEncrypt = new ArrayList<String>();
//					List<String> encrryptpackageCurve = new ArrayList<String>();
//					/**
//					 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
//					 * 第八行：911到96
//					 */
//					String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00",
//							"02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30",
//							"04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00",
//							"07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30",
//							"09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00",
//							"12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30",
//							"14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00",
//							"17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30",
//							"19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00",
//							"22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
//					final List<String> definedOrder = Arrays.asList(defined);
//					if (mapslow.size() > 0) {
//						int j = 1;
//						for (int i = 0; i < mapslow.size(); i++) {
//							for (; j <= point;) {
//								int stime = Integer.parseInt(mapslow.get(i).get("stimelow").toString());
//								double onlinelow = Double.parseDouble(mapslow.get(i).get("onlinelow").toString())
//										+ onlineProvince.doubleValue();
//								double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString())
//										+ onlineProvince.doubleValue() + outlineProvince.doubleValue();
//								DecimalFormat formater = new DecimalFormat("#0.00");
//								formater.setRoundingMode(RoundingMode.FLOOR);
//								if (j == stime) {
//									if (onlinelow == 0 && trafficlow == 0) {
//										array[stime - 1] = "100.00";
//									} else if (onlinelow != 0 && trafficlow == 0) {
//										array[stime - 1] = "-";
//									} else {
//										array[stime - 1] = formater.format(onlinelow / trafficlow * 100);
//									}
//									j++;
//									break;
//								} else {
//									array[j - 1] = formater.format(onlinelow / trafficlow * 100);
//									j++;
//									continue;
//								}
//							}
//						}
//						for (int k = 0; k < array.length; k++) {
//							if (array[k] != null) {
//								timeArrayEncrypt.add(definedOrder.get(k));
//								encrryptpackageCurve.add(array[k]);
//							} else {
//								timeArrayEncrypt.add(definedOrder.get(k));
//								encrryptpackageCurve.add("100.00");
//							}
//						}
//					} else {
//						for (int j = 0; j < array.length; j++) {
//							timeArrayEncrypt.add(definedOrder.get(j));
//							encrryptpackageCurve.add("100.00");
//						}
//					}
//					if (allLine.compareTo(new BigDecimal(0)) == 0) {
//						allOnlineRate = new BigDecimal(1.0000);
//					} else {
//						allOnlineRate = allOnline.divide(allLine, 4, BigDecimal.ROUND_HALF_UP);
//					}
//
//					screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
//					screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
//					screenSecretPassOnlineRateProtobufVO.setName("网络安全设备在线率");
//					screenSecretPassOnlineRateProtobufVO.setColor("#1BF7FF");
//					secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
//					ScreenSecretPassOnlineRateProtobufVO onlineValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
//							.getCacheValue(onlinekey);
//					if (onlineValueOrginal == null) {
//						cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
//						cacheUtils.putCacheValue("onlineboolean", true);
//					} else {
//						List<String> timeOrgial = onlineValueOrginal.getTimeArrayEncrypt();
//						List<String> secretOrginal = onlineValueOrginal.getEncrryptpackageCurve();
//						List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
//						List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
//						if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
//								&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
//							cacheUtils.putCacheValue("onlineboolean", false);
//						} else {
//							cacheUtils.putCacheValue("onlineboolean", true);
//							cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
//						}
//					}
//				} else {
//					Calendar now = Calendar.getInstance(Locale.CANADA);
//					int point = (now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE)) / 15 + 1;
//					List<String> timeArrayEncrypt = new ArrayList<String>();
//					List<String> encrryptpackageCurve = new ArrayList<String>();
//					/**
//					 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
//					 * 第八行：911到96
//					 */
//					String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00",
//							"02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30",
//							"04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00",
//							"07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30",
//							"09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00",
//							"12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30",
//							"14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00",
//							"17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30",
//							"19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00",
//							"22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
//					final List<String> definedOrder = Arrays.asList(defined);
//					for (int i = 0; i < point; i++) {
//						timeArrayEncrypt.add(definedOrder.get(i));
//						encrryptpackageCurve.add("100.00");
//					}
//					allOnlineRate = new BigDecimal(1.0000);
//					screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
//					screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
//					screenSecretPassOnlineRateProtobufVO.setName("网络安全设备在线率");
//					screenSecretPassOnlineRateProtobufVO.setColor("#1BF7FF");
//					secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
//					ScreenSecretPassOnlineRateProtobufVO onlineValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
//							.getCacheValue(onlinekey);
//					if (onlineValueOrginal == null) {
//						cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
//						cacheUtils.putCacheValue("onlineboolean", true);
//					} else {
//						List<String> timeOrgial = onlineValueOrginal.getTimeArrayEncrypt();
//						List<String> secretOrginal = onlineValueOrginal.getEncrryptpackageCurve();
//						List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
//						List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
//						if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
//								&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
//							cacheUtils.putCacheValue("onlineboolean", false);
//						} else {
//							cacheUtils.putCacheValue("onlineboolean", true);
//							cacheUtils.putCacheValue(onlinekey, screenSecretPassOnlineRateProtobufVO);
//						}
//					}
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				Log4jUtil.error(ScreenSecretPassOnlineRateDataService.class, e.getMessage(), e);
//			}
//			return String.valueOf(dFormat.format(allOnlineRate));
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createLowOnlineRate(List<Map> maps, String name) {
		List<RealtimeStatisticsAllEntity> list = screenSecretPassRateDao.getRealtimeStatisticsAllListByStime(name);
		if (list != null) {
			for (RealtimeStatisticsAllEntity realtimeStatisticsAllEntity : list) {
				Map maplow = new HashMap();
				maplow.put("stimelow", realtimeStatisticsAllEntity.getStime());
				maplow.put("onlinelow", realtimeStatisticsAllEntity.getOnlinetime());
				maplow.put("trafficlow", Double.valueOf(realtimeStatisticsAllEntity.getOnlinetime())
						+ Double.valueOf(realtimeStatisticsAllEntity.getOutlinetime()));
				maps.add(maplow);
			}
		}

	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private void createLowOnlineRate2(int point, List<Map> maps, String name) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String beginDate = "";
//		String endDate = "";
//		for (int i = 1; i <= point; i++) {
//			beginDate = sf.format(calendar.getTime());
//			calendar.add(Calendar.MINUTE, 15);
//			endDate = sf.format(calendar.getTime());
//			RealtimeStatisticsAllEntity realtimeStatisticsAllEntity = null;
//			if (StringUtils.isNotBlank(name)) {
//				realtimeStatisticsAllEntity = screenSecretPassRateDao.getRealtimeStatisticsAllEntityLowByName(beginDate,
//						endDate, name);
//			} else {
//				realtimeStatisticsAllEntity = screenSecretPassRateDao.getRealtimeStatisticsAllEntity(beginDate,
//						endDate);
//			}
//			if (realtimeStatisticsAllEntity != null) {
//				Map maplow = new HashMap();
//				maplow.put("stimelow", String.valueOf(i));
//				maplow.put("onlinelow", realtimeStatisticsAllEntity.getOnlinetime());
//				maplow.put("trafficlow", Double.valueOf(realtimeStatisticsAllEntity.getOnlinetime())
//						+ Double.valueOf(realtimeStatisticsAllEntity.getOutlinetime()));
//				maps.add(maplow);
//			}
//		}
//
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createLowSecreteRate(List<Map> maps, String name) {
		List<RealtimeStatisticsAllEntity> list = screenSecretPassRateDao.getRealtimeStatisticsAllListByStime(name);
		if (list != null) {
			for (RealtimeStatisticsAllEntity realtimeStatisticsAllEntity : list) {
				Map maplow = new HashMap();
				maplow.put("stimelow", realtimeStatisticsAllEntity.getStime());
				maplow.put("secretpassflowlow", realtimeStatisticsAllEntity.getSecretpassflow());
				maplow.put("trafficlow", realtimeStatisticsAllEntity.getTraffic());
				maps.add(maplow);
			}
		}

	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private void createLowSecreteRate2(int point, List<Map> maps, String name) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String beginDate = "";
//		String endDate = "";
//		for (int i = 1; i <= point; i++) {
//			beginDate = sf.format(calendar.getTime());
//			calendar.add(Calendar.MINUTE, 15);
//			endDate = sf.format(calendar.getTime());
//			RealtimeStatisticsAllEntity realtimeStatisticsAllEntity = null;
//			if (StringUtils.isNotBlank(name)) {
//				realtimeStatisticsAllEntity = screenSecretPassRateDao.getRealtimeStatisticsAllEntityLowByName(beginDate,
//						endDate, name);
//			} else {
//				realtimeStatisticsAllEntity = screenSecretPassRateDao.getRealtimeStatisticsAllEntity(beginDate,
//						endDate);
//			}
//			if (realtimeStatisticsAllEntity != null) {
//				Map maplow = new HashMap();
//				maplow.put("stimelow", String.valueOf(i));
//				maplow.put("secretpassflowlow", realtimeStatisticsAllEntity.getSecretpassflow());
//				maplow.put("trafficlow", realtimeStatisticsAllEntity.getTraffic());
//				maps.add(maplow);
//			}
//		}
//
//	}
	private String createBeforeNextRate(String[] arrayValue, int k) {
		arrayValue[k] = "99.21%";
		int length = arrayValue.length;
		if (length > 1) {
			if (k == 0) {
				for (int i = 0; i < length - 1; i++) {
					arrayValue[k] = arrayValue[k + i + 1];
					if (arrayValue[k] != null) {
						break;
					}
				}
				if (arrayValue[k] == null) {
					arrayValue[k] = "99.21%";
				}
			} else if (k == length - 1) {
				for (int i = 0; i < length - 1; i++) {
					arrayValue[k] = arrayValue[k - i - 1];
					if (arrayValue[k] != null) {
						break;
					}
				}
				if (arrayValue[k] == null) {
					arrayValue[k] = "99.21%";
				}
			} else {
				for (int i = 0; i < k; i++) {
					arrayValue[k] = arrayValue[k - i - 1];
					if (arrayValue[k] != null) {
						break;
					}
				}
				if (arrayValue[k] == null) {
					for (int i = 0; i < length - 1 - k; i++) {
						arrayValue[k] = arrayValue[i + 1 + k];
						if (arrayValue[k] != null) {
							break;
						}
					}
				}
			}
		}

		return arrayValue[k];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String createSecretPassRate(List<ScreenSecretPassOnlineRateProtobufVO> secretPassOnlineRateProtobufVOs,
			String name, String secretkey) {
		if (StringUtils.isBlank(name)) {
			// 全网密通率
			BigDecimal allSecretPass = new BigDecimal(0);
			// 全网密文流量
			BigDecimal allSecretpassflow = new BigDecimal(0);
			// 全网总流量
			BigDecimal allTraffic = new BigDecimal(0);
			DecimalFormat dFormat = new DecimalFormat("0.00%");
			ScreenSecretPassOnlineRateProtobufVO screenSecretPassOnlineRateProtobufVO = new ScreenSecretPassOnlineRateProtobufVO();
			List<Map> maps = new ArrayList<Map>();
			try {
				Calendar now = Calendar.getInstance(Locale.CANADA);
//				int point = Integer.valueOf(
//						String.valueOf(new BigDecimal(now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE))
//								.divide(new BigDecimal(15), 0, BigDecimal.ROUND_DOWN).add(new BigDecimal(1))));
				int point = Integer.valueOf(
						String.valueOf(new BigDecimal(now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE))
								.divide(new BigDecimal(15), 0, BigDecimal.ROUND_DOWN)));
				List<SecretPassRate> list = screenSecretPassRateDao.getSecretPassRate(name);
				String[] array = new String[point];
				for (SecretPassRate li : list) {
					Map map = new HashMap();
					map.put("stime", li.getStime());
					map.put("secretpassflow", li.getSecretpassflow());
					map.put("traffic", li.getTraffic());
					allSecretpassflow = allSecretpassflow.add(new BigDecimal(li.getSecretpassflow()));
					allTraffic = allTraffic.add(new BigDecimal(li.getCleartraffic()));
					maps.add(map);
				}
				// 计算下级密通率
				List<Map> mapslow = new ArrayList<>();
				// createLowSecreteRate(point, mapslow, name);
				createLowSecreteRate(mapslow, name);
				for (int i = 0; i < mapslow.size(); i++) {
					double secretpassflowlow = Double.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
					double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
					allSecretpassflow = allSecretpassflow.add(new BigDecimal(secretpassflowlow));
					allTraffic = allTraffic.add(new BigDecimal(trafficlow));
				}
				List<String> timeArrayEncrypt = new ArrayList<String>();
				List<String> encrryptpackageCurve = new ArrayList<String>();
				/**
				 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
				 * 第八行：911到96
				 */
				String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15",
						"02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45",
						"05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15",
						"07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45",
						"10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15",
						"12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45",
						"15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15",
						"17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45",
						"20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15",
						"22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
				final List<String> definedOrder = Arrays.asList(defined);
				if (maps.size() > 0) {
					int j = 1;
					for (int i = 0; i < maps.size(); i++) {
						for (; j <= point;) {
							int stime = Integer.parseInt(maps.get(i).get("stime").toString());
							double secretpassflow = Double.parseDouble(maps.get(i).get("secretpassflow").toString());
							double traffic = Double.parseDouble(maps.get(i).get("traffic").toString());
							double secretpassflowlow = 0;
							double trafficlow = 0;
							if (mapslow.size() < maps.size()) {
								if (i < mapslow.size()) {
									secretpassflowlow = Double
											.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
									trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
								}
							} else {
								secretpassflowlow = Double
										.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
								trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
							}
							secretpassflow = secretpassflow + secretpassflowlow;
							traffic = traffic + trafficlow;
							DecimalFormat formater = new DecimalFormat("#0.00");
							formater.setRoundingMode(RoundingMode.FLOOR);
							if (j == stime) {
								if (secretpassflow == 0 && traffic == 0) {
									array[stime - 1] = "100.00";
								} else if (secretpassflow != 0 && traffic == 0) {
									array[stime - 1] = "100.00";
								} else {
									array[stime - 1] = formater.format(new BigDecimal(secretpassflow)
											.divide(new BigDecimal(traffic), 4, BigDecimal.ROUND_HALF_UP)
											.multiply(new BigDecimal(100)));
								}
								j++;
								break;
							}
							j++;
//							else {
//								if (trafficlow == 0) {
//									array[j - 1] = "100.00";
//								} else {
//									array[j - 1] = formater.format(new BigDecimal(secretpassflowlow)
//											.divide(new BigDecimal(trafficlow), 4, BigDecimal.ROUND_HALF_UP)
//											.multiply(new BigDecimal(100)));
//								}
//								j++;
//								continue;
//							}
						}
					}
					for (int k = 0; k < array.length; k++) {
						if (array[k] != null) {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(array[k]);
						} else {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(createBeforeNextRate(array, k));
						}
					}
				} else {
					for (int j = 0; j < array.length; j++) {
						timeArrayEncrypt.add(definedOrder.get(j));
						encrryptpackageCurve.add("100.00");
					}
				}
				if (allTraffic.compareTo(new BigDecimal(0)) == 0) {
					allSecretPass = new BigDecimal(1.0000);
				} else {
					allSecretPass = allSecretpassflow.divide(allTraffic, 4, BigDecimal.ROUND_HALF_UP);
				}
				screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
				screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
				screenSecretPassOnlineRateProtobufVO.setColor("#33f843");
				screenSecretPassOnlineRateProtobufVO.setName("纵向密通水平");
				secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
				ScreenSecretPassOnlineRateProtobufVO secretValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
						.getCacheValue(secretkey);
				if (secretValueOrginal == null) {
					cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
					cacheUtils.putCacheValue("secretboolean", true);
				} else {
					List<String> timeOrgial = secretValueOrginal.getTimeArrayEncrypt();
					if(timeOrgial == null) {
						timeOrgial = new ArrayList<>();
					}
					List<String> secretOrginal = secretValueOrginal.getEncrryptpackageCurve();
					if(secretOrginal == null) {
						secretOrginal = new ArrayList<>();
					}
					List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
					if(timeNew == null) {
						timeNew = new ArrayList<>();
					}
					List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
					if(secretNew == null) {
						secretNew = new ArrayList<>();
					}
					if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
							&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
						cacheUtils.putCacheValue("secretboolean", false);
					} else {
						cacheUtils.putCacheValue("secretboolean", true);
						cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(ScreenSecretPassOnlineRateDataService.class, e.getMessage(), e);
			}
			return String.valueOf(dFormat.format(allSecretPass));
		} else {
			// 省级密通率
			BigDecimal allSecretPass = new BigDecimal(0);
			// 省级密文流量
			BigDecimal allSecretpassflow = new BigDecimal(0);
			// 省级总流量
			BigDecimal allTraffic = new BigDecimal(0);
			DecimalFormat dFormat = new DecimalFormat("0.00%");
			ScreenSecretPassOnlineRateProtobufVO screenSecretPassOnlineRateProtobufVO = new ScreenSecretPassOnlineRateProtobufVO();
			List<Map> maps = new ArrayList<Map>();
			try {
				Calendar now = Calendar.getInstance(Locale.CANADA);
				int point = Integer.valueOf(
						String.valueOf(new BigDecimal(now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE))
								.divide(new BigDecimal(15), 0, BigDecimal.ROUND_DOWN)));
				String[] array = new String[point];
				List<RealtimeStatisticsAllEntity> realtimeStatisticsAllEntitys = screenSecretPassRateDao
						.getRealtimeStatisticsAllEntityByName(name);
				for (RealtimeStatisticsAllEntity realtimeStatisticsAllEntity : realtimeStatisticsAllEntitys) {
					Map map = new HashMap();
					map.put("stime", realtimeStatisticsAllEntity.getStime());
					map.put("secretpassflow", realtimeStatisticsAllEntity.getSecretpassflow());
					map.put("traffic", realtimeStatisticsAllEntity.getTraffic());
					allSecretpassflow = allSecretpassflow
							.add(new BigDecimal(realtimeStatisticsAllEntity.getSecretpassflow()));
					allTraffic = allTraffic.add(new BigDecimal(realtimeStatisticsAllEntity.getCleartraffic()));
					maps.add(map);
				}
				// 计算下级密通率
				List<Map> mapslow = new ArrayList<>();
				List<RealtimeStatisticsAllEntity> list = screenSecretPassRateDao
						.getRealtimeStatisticsAllListByStime(name);
				for (RealtimeStatisticsAllEntity realtimeStatisticsAllEntity : list) {
					Map maplow = new HashMap();
					maplow.put("stimelow", realtimeStatisticsAllEntity.getStime());
					maplow.put("secretpassflowlow", realtimeStatisticsAllEntity.getSecretpassflow());
					maplow.put("trafficlow", realtimeStatisticsAllEntity.getTraffic());
					mapslow.add(maplow);
				}
				// int point = list.size();
				for (int i = 0; i < mapslow.size(); i++) {
					double secretPasslow = Double.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
					double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
					allSecretpassflow = allSecretpassflow.add(new BigDecimal(secretPasslow));
					allTraffic = allTraffic.add(new BigDecimal(trafficlow));
				}
				List<String> timeArrayEncrypt = new ArrayList<String>();
				List<String> encrryptpackageCurve = new ArrayList<String>();
				/**
				 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
				 * 第八行：911到96
				 */
				String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15",
						"02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45",
						"05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15",
						"07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45",
						"10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15",
						"12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45",
						"15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15",
						"17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45",
						"20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15",
						"22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
				final List<String> definedOrder = Arrays.asList(defined);
				if (mapslow.size() > 0) {
					int j = 1;
					for (int i = 0; i < mapslow.size(); i++) {
						for (; j <= point;) {
							int stime = Integer.parseInt(maps.get(i).get("stime").toString());
							double secretpassflow = Double.parseDouble(maps.get(i).get("secretpassflow").toString());
							double traffic = Double.parseDouble(maps.get(i).get("traffic").toString());
							double secretpassflowlow = 0;
							double trafficlow = 0;
							if (mapslow.size() < maps.size()) {
								if (i < mapslow.size()) {
									secretpassflowlow = Double
											.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
									trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
								}
							} else {
								secretpassflowlow = Double
										.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
								trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
							}
							secretpassflow = secretpassflow + secretpassflowlow;
							traffic = traffic + trafficlow;
							DecimalFormat formater = new DecimalFormat("#0.00");
							formater.setRoundingMode(RoundingMode.FLOOR);
							if (j == stime) {
								if (secretpassflowlow == 0 && trafficlow == 0) {
									array[stime - 1] = "100.00";
								} else if (secretpassflowlow != 0 && trafficlow == 0) {
									array[stime - 1] = "100.00";
								} else {
									array[stime - 1] = formater.format(new BigDecimal(secretpassflowlow)
											.divide(new BigDecimal(trafficlow), 4, BigDecimal.ROUND_HALF_UP)
											.multiply(new BigDecimal(100)));

								}
								j++;
								break;
							}
							j++;
//							else {
//								if (trafficlow == 0) {
//									array[j - 1] = "100.00";
//								} else {
//									array[j - 1] = formater.format(new BigDecimal(secretpassflowlow)
//											.divide(new BigDecimal(trafficlow), 4, BigDecimal.ROUND_HALF_UP)
//											.multiply(new BigDecimal(100)));
//								}
//								j++;
//								continue;
//							}
						}
					}
					for (int k = 0; k < array.length; k++) {
						if (array[k] != null) {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(array[k]);
						} else {
							timeArrayEncrypt.add(definedOrder.get(k));
							encrryptpackageCurve.add(createBeforeNextRate(array, k));

						}
					}
				} else {
					for (int j = 0; j < array.length; j++) {
						timeArrayEncrypt.add(definedOrder.get(j));
						encrryptpackageCurve.add("100.00");
					}
				}
				if (allTraffic.compareTo(new BigDecimal(0)) == 0) {
					allSecretPass = new BigDecimal(1.0000);
				} else {
					allSecretPass = allSecretpassflow.divide(allTraffic, 4, BigDecimal.ROUND_HALF_UP);
				}
				screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
				screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
				screenSecretPassOnlineRateProtobufVO.setColor("#33f843");
				screenSecretPassOnlineRateProtobufVO.setName("纵向密通水平");
				secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
				ScreenSecretPassOnlineRateProtobufVO secretValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
						.getCacheValue(secretkey);
				if (secretValueOrginal == null) {
					cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
					cacheUtils.putCacheValue("secretboolean", true);
				} else {
					List<String> timeOrgial = secretValueOrginal.getTimeArrayEncrypt();
					if(timeOrgial == null) {
						timeOrgial = new ArrayList<>();
					}
					List<String> secretOrginal = secretValueOrginal.getEncrryptpackageCurve();
					if(secretOrginal == null) {
						secretOrginal = new ArrayList<>();
					}
					List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
					if(timeNew == null) {
						timeNew = new ArrayList<>();
					}
					List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
					if(secretNew == null) {
						secretNew = new ArrayList<>();
					}
					if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
							&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
						cacheUtils.putCacheValue("secretboolean", false);
					} else {
						cacheUtils.putCacheValue("secretboolean", true);
						cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
					}
				}
//				if (list != null && list.size() > 0) {
//					String timebegin = list.get(0);
//					String timeend = list.get(list.size() - 1);
//					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Date timebeginDate = sf.parse(timebegin);
//					Date timeendDate = sf.parse(timeend);
//					long time = timeendDate.getTime() - timebeginDate.getTime();
//					int point = Integer.valueOf(String.valueOf(new BigDecimal(time)
//							.divide(new BigDecimal(1000 * 60 * 15), 0, BigDecimal.ROUND_DOWN).add(new BigDecimal(1))));
//					String[] array = new String[point];
//					// 计算下级在线率
//					List<Map> mapslow = new ArrayList<>();
//					createLowSecreteRate(mapslow, name);
//					RealtimeStatisticsAllEntity realtimeStatisticsAllEntity = screenSecretPassRateDao
//							.getRealtimeStatisticsAllEntityByName(name);
//					if (realtimeStatisticsAllEntity != null) {
//						secretPassProvince = new BigDecimal(realtimeStatisticsAllEntity.getSecretpassflow());
//						allTrafficProvince = new BigDecimal(realtimeStatisticsAllEntity.getTraffic());
//					}
//					for (int i = 0; i < mapslow.size(); i++) {
//						double secretPasslow = Double.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
//						double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
//						allSecretpassflow = allSecretpassflow.add(new BigDecimal(secretPasslow));
//						allTraffic = allTraffic.add(new BigDecimal(trafficlow));
//					}
//					allTraffic = allTraffic.add(allTrafficProvince);
//					allSecretpassflow = allSecretpassflow.add(secretPassProvince);
//					List<String> timeArrayEncrypt = new ArrayList<String>();
//					List<String> encrryptpackageCurve = new ArrayList<String>();
//					/**
//					 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
//					 * 第八行：911到96
//					 */
//					String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00",
//							"02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30",
//							"04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00",
//							"07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30",
//							"09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00",
//							"12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30",
//							"14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00",
//							"17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30",
//							"19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00",
//							"22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
//					final List<String> definedOrder = Arrays.asList(defined);
//					if (mapslow.size() > 0) {
//						int j = 1;
//						for (int i = 0; i < mapslow.size(); i++) {
//							for (; j <= point;) {
//								int stime = Integer.parseInt(mapslow.get(i).get("stimelow").toString());
//								double secretpassflowlow = Double
//										.parseDouble(mapslow.get(i).get("secretpassflowlow").toString());
//								double trafficlow = Double.parseDouble(mapslow.get(i).get("trafficlow").toString());
//								DecimalFormat formater = new DecimalFormat("#0.00");
//								formater.setRoundingMode(RoundingMode.FLOOR);
//								if (j == stime) {
//									if (secretpassflowlow == 0 && trafficlow == 0) {
//										array[stime - 1] = "100.00";
//									} else if (secretpassflowlow != 0 && trafficlow == 0) {
//										array[stime - 1] = "-";
//									} else {
//										array[stime - 1] = formater.format(secretpassflowlow / trafficlow * 100);
//									}
//									j++;
//									break;
//								} else {
//									array[j - 1] = formater.format(secretpassflowlow / trafficlow * 100);
//									j++;
//									continue;
//								}
//							}
//						}
//						for (int k = 0; k < array.length; k++) {
//							if (array[k] != null) {
//								timeArrayEncrypt.add(definedOrder.get(k));
//								encrryptpackageCurve.add(array[k]);
//							} else {
//								timeArrayEncrypt.add(definedOrder.get(k));
//								encrryptpackageCurve.add("100.00");
//							}
//						}
//					} else {
//						for (int j = 0; j < array.length; j++) {
//							timeArrayEncrypt.add(definedOrder.get(j));
//							encrryptpackageCurve.add("100.00");
//						}
//					}
//					if (allTraffic.compareTo(new BigDecimal(0)) == 0) {
//						allSecretPass = new BigDecimal(1.0000);
//					} else {
//						allSecretPass = allSecretpassflow.divide(allTraffic, 4, BigDecimal.ROUND_HALF_UP);
//					}
//					screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
//					screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
//					screenSecretPassOnlineRateProtobufVO.setColor("#33f843");
//					screenSecretPassOnlineRateProtobufVO.setName("纵向密通水平");
//					secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
//					ScreenSecretPassOnlineRateProtobufVO secretValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
//							.getCacheValue(secretkey);
//					if (secretValueOrginal == null) {
//						cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
//						cacheUtils.putCacheValue("secretboolean", true);
//					} else {
//						List<String> timeOrgial = secretValueOrginal.getTimeArrayEncrypt();
//						List<String> secretOrginal = secretValueOrginal.getEncrryptpackageCurve();
//						List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
//						List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
//						if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
//								&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
//							cacheUtils.putCacheValue("secretboolean", false);
//						} else {
//							cacheUtils.putCacheValue("secretboolean", true);
//							cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
//						}
//					}
//				} else {
//					Calendar now = Calendar.getInstance(Locale.CANADA);
//					int point = (now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE)) / 15 + 1;
//					Log4jUtil.warn(ScreenSecretPassOnlineRateDataService.class, "时间点个数：" + point);
//					point = list.size();
//					List<String> timeArrayEncrypt = new ArrayList<String>();
//					List<String> encrryptpackageCurve = new ArrayList<String>();
//					/**
//					 * 第一行：1到12 第二行：13到25 第三行：26到38 第四行：39到51 第五行：52到64 第六行：65到77 第七行：78到90
//					 * 第八行：911到96
//					 */
//					String[] defined = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00",
//							"02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30",
//							"04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00",
//							"07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30",
//							"09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00",
//							"12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30",
//							"14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00",
//							"17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30",
//							"19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00",
//							"22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
//					final List<String> definedOrder = Arrays.asList(defined);
//					for (int i = 0; i < point; i++) {
//						timeArrayEncrypt.add(definedOrder.get(i));
//						encrryptpackageCurve.add("100.00");
//					}
//					allSecretPass = new BigDecimal(1.0000);
//					screenSecretPassOnlineRateProtobufVO.setTimeArrayEncrypt(timeArrayEncrypt);
//					screenSecretPassOnlineRateProtobufVO.setEncrryptpackageCurve(encrryptpackageCurve);
//					screenSecretPassOnlineRateProtobufVO.setColor("#33f843");
//					screenSecretPassOnlineRateProtobufVO.setName("纵向密通水平");
//					secretPassOnlineRateProtobufVOs.add(screenSecretPassOnlineRateProtobufVO);
//					ScreenSecretPassOnlineRateProtobufVO secretValueOrginal = (ScreenSecretPassOnlineRateProtobufVO) cacheUtils
//							.getCacheValue(secretkey);
//					if (secretValueOrginal == null) {
//						cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
//						cacheUtils.putCacheValue("secretboolean", true);
//					} else {
//						List<String> timeOrgial = secretValueOrginal.getTimeArrayEncrypt();
//						List<String> secretOrginal = secretValueOrginal.getEncrryptpackageCurve();
//						List<String> timeNew = screenSecretPassOnlineRateProtobufVO.getTimeArrayEncrypt();
//						List<String> secretNew = screenSecretPassOnlineRateProtobufVO.getEncrryptpackageCurve();
//						if (timeOrgial.containsAll(timeNew) && timeNew.containsAll(timeOrgial)
//								&& secretOrginal.containsAll(secretNew) && secretNew.containsAll(secretOrginal)) {
//							cacheUtils.putCacheValue("secretboolean", false);
//						} else {
//							cacheUtils.putCacheValue("secretboolean", true);
//							cacheUtils.putCacheValue(secretkey, screenSecretPassOnlineRateProtobufVO);
//						}
//					}
//				}
			} catch (Exception e) {
				e.printStackTrace();
				Log4jUtil.error(ScreenSecretPassOnlineRateDataService.class, e.getMessage(), e);
			}
			return String.valueOf(dFormat.format(allSecretPass));
		}

	}
}

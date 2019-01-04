package com.CrossCountry.Survey.commonservice.serviceutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.CrossCountry.Survey.mapper.shichf.ScreenDrawMapDataDao;
import com.CrossCountry.Survey.modelvo.shichf.MapRegionName;
import com.CrossCountry.Survey.utils.shichf.StringFilterUtil;

@Component
public class DataChangePublicService {
	@Autowired
	private ScreenDrawMapDataDao screenDrawMapDataDao;

	public String mapRegionNameChange(String name) {
		if (StringUtils.isBlank(name)) {
			return "";
		} else {
			Map<String, String> map = new HashMap<>();
			List<MapRegionName> list = screenDrawMapDataDao.getMapRegionNameList();
			for (MapRegionName mapRegionName : list) {
				map.put(StringFilterUtil.filterName(mapRegionName.getName()), mapRegionName.getName());
			}
			return map.get(name);
		}

	}
}

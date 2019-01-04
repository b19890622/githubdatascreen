package com.CrossCountry.Survey.modelvo.donggs;

import java.util.List;

public class AlarmDistributionEquipmentAndSafe {
      List<AlarmDistributionEquipmentPo> equipment ;
      
      List<AlarmDistributionSafePo> safe ;

	public List<AlarmDistributionEquipmentPo> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<AlarmDistributionEquipmentPo> equipment) {
		this.equipment = equipment;
	}

	public List<AlarmDistributionSafePo> getSafe() {
		return safe;
	}

	public void setSafe(List<AlarmDistributionSafePo> safe) {
		this.safe = safe;
	}
      
}

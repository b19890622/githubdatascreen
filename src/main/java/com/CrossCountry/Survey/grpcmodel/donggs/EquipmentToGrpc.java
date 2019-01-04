package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.EquipmentNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.Equipment;
import com.datascreen.EquipmentNumberReply;

public class EquipmentToGrpc {

	public EquipmentNumberReply getEquipmentTogrpc(Object object) {

		EquipmentNumberReply.Builder response = EquipmentNumberReply.newBuilder();
		List<EquipmentNumber> equipmentNumbers = (List<EquipmentNumber>) object;
		List<Equipment> Numbers =  new ArrayList<>();
		
		try {
			
			for (EquipmentNumber equipmentNumber : equipmentNumbers) {
				String abc = "";
				if (equipmentNumber.getEquipmentName().contains("市调")) {
					
					 abc = equipmentNumber.getEquipmentName().replace("市调", "");
				}else if (equipmentNumber.getEquipmentName().contains("省调")) {
					 abc = equipmentNumber.getEquipmentName().replace("省调", "");
				}else if (equipmentNumber.getEquipmentName().contains("区调")) {
					 abc = equipmentNumber.getEquipmentName().replace("区调", "");
				}else if (equipmentNumber.getEquipmentName().contains("分中心")) {
					 abc = equipmentNumber.getEquipmentName().replace("分中心", "");
				
				}
				equipmentNumber.setEquipmentName(abc);
				Equipment change= (Equipment) TransformToGrpcPo
						.convertToPojo(Equipment.class, equipmentNumber);
				
					
				        Numbers.add(change);
				
				
			}
		if (null != Numbers && Numbers.size() > 0) {
			response.addAllEquipment(Numbers);
		}
			
		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

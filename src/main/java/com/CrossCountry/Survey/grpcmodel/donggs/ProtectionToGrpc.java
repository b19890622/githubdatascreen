package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.Mpcvf;
import com.CrossCountry.Survey.modelvo.donggs.ProtectionNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.Protection;
import com.datascreen.ProtectionNumberReply;

public class ProtectionToGrpc {

	public ProtectionNumberReply protectionTogrpc(Object object) {

		ProtectionNumberReply.Builder response = ProtectionNumberReply.newBuilder();
		Mpcvf protectionNumbers = (Mpcvf) object;
		List<ProtectionNumber> protectionNumber = new ArrayList<>();
		List<Protection> Numbers = new ArrayList<>();
		try {

		ProtectionNumber protection = new ProtectionNumber();
		protection.setProtectionName("主站安全区");
		protection.setProtectionNum(protectionNumbers.getMaster());
		protectionNumber.add(protection);
		
		ProtectionNumber protection2 = new ProtectionNumber();
		protection2.setProtectionName("调度数据网节点数");
		protection2.setProtectionNum(protectionNumbers.getCtrldata());
		protectionNumber.add(protection2);

		ProtectionNumber protection3 = new ProtectionNumber();
		protection3.setProtectionName("正向隔离数量");
		protection3.setProtectionNum(protectionNumbers.getFbidnum());
		protectionNumber.add(protection3);
		
		ProtectionNumber protection4 = new ProtectionNumber();
		protection4.setProtectionName("厂站安全区");
		protection4.setProtectionNum(protectionNumbers.getPlant());
		protectionNumber.add(protection4);
		
		ProtectionNumber protection5 = new ProtectionNumber();
		protection5.setProtectionName("纵向加密数量");
		protection5.setProtectionNum(protectionNumbers.getVeadnum());
		protectionNumber.add(protection5);
		
		ProtectionNumber protection6 = new ProtectionNumber();
		protection6.setProtectionName("反向隔离数量");
		protection6.setProtectionNum(protectionNumbers.getFxfbidnum());
		protectionNumber.add(protection6);
		
      for (ProtectionNumber Protection : protectionNumber) {
		
    	  Protection change = (Protection) TransformToGrpcPo.convertToPojo(
    			  Protection.class, Protection);
    	         Numbers.add(change);
	}
			

			if (null != Numbers && Numbers.size() > 0) {
			response.addAllProtection(Numbers);
			}
		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

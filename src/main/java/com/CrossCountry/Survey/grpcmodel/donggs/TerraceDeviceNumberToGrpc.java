package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceGuanKongService;
import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.JustableNumber;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.Justable;
import com.datascreen.TerraceDeviceNumberReply;

public class TerraceDeviceNumberToGrpc {
	@Autowired
	CommonServiceGuanKongService commonServiceGuanKongService;

	public TerraceDeviceNumberReply getTerraceDeviceNumberTogrpc(Object object) {

		TerraceDeviceNumberReply.Builder response = TerraceDeviceNumberReply.newBuilder();
		Justable.Builder justable  =Justable.newBuilder();
		List<JustableNumber> justableNumber = (List<JustableNumber>) object;
		
		List<Justable> Numbers =  new ArrayList<>();
		int sums = 0 ;
		int sum = 0 ;
		try {
			
			for (JustableNumber justableNumber2 : justableNumber) {	
				if (justableNumber2.getJustableName().equals("0")) {
					justableNumber2.setJustableName("国调平台");
				}else if ("1".equals(justableNumber2.getJustableName())) {
					justableNumber2.setJustableName("分中心平台");
				}else if ("2".equals(justableNumber2.getJustableName())) {
					justableNumber2.setJustableName("省调");
				}else if("3".equals(justableNumber2.getJustableName())){
					justableNumber2.setJustableName("地调");
				}
			 Justable change= (Justable) TransformToGrpcPo
						.convertToPojo(Justable.class, justableNumber2);
				        Numbers.add(change);
				       
				sum =justableNumber2.getJustableNum();
				if (sum != 0) {
					sums += sum;
				}
				
			}
			 if (null != Numbers && Numbers.size() > 0) {
				 response.addAllJustable(Numbers);
				}
			response.setTerraceDeviceNumber(sums);
		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();

	}
}

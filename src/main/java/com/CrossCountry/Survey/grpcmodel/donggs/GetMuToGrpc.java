package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.CrossCountry.Survey.commonservice.donggs.CommonServiceLagerService;
import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.grpcmodel.liujg.CommonConvertToGrpcTools;
import com.CrossCountry.Survey.modelvo.donggs.AllData;
import com.CrossCountry.Survey.modelvo.donggs.Largen;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.AllDateArrayProtobuf;
import com.datascreen.LargenArrayProtobuf;
import com.datascreen.Remoteprovince;

public class GetMuToGrpc {
	@Autowired
	CommonServiceLagerService commonServiceLagerService;

	

	public LargenArrayProtobuf getMuTogrpc(Object object) {

		LargenArrayProtobuf.Builder response = LargenArrayProtobuf.newBuilder();
		List<Largen> Number = (List<Largen>) object;
		List<Remoteprovince> Numbers =  new ArrayList<>();
			
			
		try {
			for (Largen num : Number) {

				Remoteprovince change= (Remoteprovince) TransformToGrpcPo
						.convertToPojo(Remoteprovince.class, num);
				Numbers.add(change);
			}
			if (null != Numbers && Numbers.size() > 0) {
				response.addAllRemoteprovince(Numbers);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(GetMuToGrpc.class, e.getMessage(), e);
		}
		return response.build();

	}
}

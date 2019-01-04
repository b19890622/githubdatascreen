package com.CrossCountry.Survey.grpcmodel.donggs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.donggs.ComplianceStatisticsPo;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.ComplianceStatistics;
import com.datascreen.ComplianceStatisticsReply;

public class ComplianceStatisticsToGrpc {

	public ComplianceStatisticsReply complianceStatisticsTogrpc(Object object) {

		ComplianceStatisticsReply.Builder response = ComplianceStatisticsReply.newBuilder();
		List<ComplianceStatisticsPo> complianceStatisticsNumbers = (List<ComplianceStatisticsPo>) object;
		List<ComplianceStatistics> Numbers = new ArrayList<>();
		 
		try {
			
			for (ComplianceStatisticsPo complianceStatisticsPo : complianceStatisticsNumbers) {
                  if ("2".equals(complianceStatisticsPo.getComplianceStatisticsState())) {
					
				}else  {
					complianceStatisticsPo.setComplianceStatisticsState("1");
				}
			}
			Collections.sort(complianceStatisticsNumbers,new Comparator<ComplianceStatisticsPo>() {
                 
				@Override
				public int compare(ComplianceStatisticsPo o1, ComplianceStatisticsPo o2) {
					 if (Double.parseDouble(o1.getComplianceStatisticsState())> Double.parseDouble(o2.getComplianceStatisticsState())) {  
		                    return -1;  
		                }  
		                if (Double.parseDouble(o1.getComplianceStatisticsState())== Double.parseDouble(o2.getComplianceStatisticsState())) {  
		                    return 0;  
		                }  
		                return 1;  
		            }  
			});  
		for (ComplianceStatisticsPo complianceStatistics: complianceStatisticsNumbers) {
			
			if (complianceStatistics.getComplianceStatisticsState().equals("2") ) {
				complianceStatistics.setComplianceStatisticsState("未解决");
				complianceStatistics.setComplianceStatisticsName(complianceStatistics.getComplianceStatisticsName().replace("\"", ""));	
			}else if(complianceStatistics.getComplianceStatisticsState() .equals("1")||complianceStatistics.getComplianceStatisticsState().equals("4") ){
				complianceStatistics.setComplianceStatisticsState("已解决");
				complianceStatistics.setComplianceStatisticsName(complianceStatistics.getComplianceStatisticsName().replace("\"", ""));	
			}
			ComplianceStatistics change = (ComplianceStatistics) TransformToGrpcPo.convertToPojo(ComplianceStatistics.class, complianceStatistics);
			
				Numbers.add(change);
				
		}
		if (null != Numbers && Numbers.size() > 0) {
			
			response.addAllComplianceStatistics(Numbers);
		
		   }

		} catch (Exception e) {

			e.printStackTrace();
			Log4jUtil.error(TerraceDeviceNumberToGrpc.class, e.getMessage(), e);
		}
		return response.build();
	}
}

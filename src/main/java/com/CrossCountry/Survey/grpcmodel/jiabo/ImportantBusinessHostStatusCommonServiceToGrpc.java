package com.CrossCountry.Survey.grpcmodel.jiabo;

import com.CrossCountry.Survey.encapsulation.common.TransformToGrpcPo;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import com.CrossCountry.Survey.utils.Log4jUtil;
import com.datascreen.*;

import java.util.ArrayList;
import java.util.List;

public class ImportantBusinessHostStatusCommonServiceToGrpc {
    public ImportantBusinessHostStatusReply getImportantBusinessHostStatus(Object object) {
        ImportantBusinessHostStatusReply.Builder response = ImportantBusinessHostStatusReply.newBuilder();
        List<ImportantBusinessHostStatusVO> importantBusinessHostStatusVOList = (List<ImportantBusinessHostStatusVO>) object;
        try {
            List<ImportantBusinessHostStatus> importantBusinessHostStatusListOne = new ArrayList<ImportantBusinessHostStatus>();
            List<ImportantBusinessHostStatus> importantBusinessHostStatusListTwo = new ArrayList<ImportantBusinessHostStatus>();
            List<ImportantBusinessHostStatus> importantBusinessHostStatusListThree = new ArrayList<ImportantBusinessHostStatus>();
            ImportantBusinessHostStatusOne.Builder importantBusinessHostStatusOne = ImportantBusinessHostStatusOne.newBuilder();
            ImportantBusinessHostStatusTwo.Builder importantBusinessHostStatusTwo = ImportantBusinessHostStatusTwo.newBuilder();
            ImportantBusinessHostStatusThree.Builder importantBusinessHostStatusThree = ImportantBusinessHostStatusThree.newBuilder();
            for(ImportantBusinessHostStatusPo ip : importantBusinessHostStatusVOList.get(0).getImportantBusinessHostStatusPoList()){
                ImportantBusinessHostStatus importantBusinessHostStatus = (ImportantBusinessHostStatus) TransformToGrpcPo.convertToPojo(ImportantBusinessHostStatus.class,ip);
                importantBusinessHostStatusListOne.add(importantBusinessHostStatus);
            }
            for(ImportantBusinessHostStatusPo ip : importantBusinessHostStatusVOList.get(1).getImportantBusinessHostStatusPoList()){
                ImportantBusinessHostStatus importantBusinessHostStatus = (ImportantBusinessHostStatus) TransformToGrpcPo.convertToPojo(ImportantBusinessHostStatus.class,ip);
                importantBusinessHostStatusListTwo.add(importantBusinessHostStatus);
            }
            for(ImportantBusinessHostStatusPo ip : importantBusinessHostStatusVOList.get(2).getImportantBusinessHostStatusPoList()){
                ImportantBusinessHostStatus importantBusinessHostStatus = (ImportantBusinessHostStatus) TransformToGrpcPo.convertToPojo(ImportantBusinessHostStatus.class,ip);
                importantBusinessHostStatusListThree.add(importantBusinessHostStatus);
            }
            response.setImportantBusinessHostStatusOne(importantBusinessHostStatusOne.addAllImportantBusinessHostStatus(importantBusinessHostStatusListOne))
                    .setImportantBusinessHostStatusTwo(importantBusinessHostStatusTwo.addAllImportantBusinessHostStatus(importantBusinessHostStatusListTwo))
                    .setImportantBusinessHostStatusThree(importantBusinessHostStatusThree.addAllImportantBusinessHostStatus(importantBusinessHostStatusListThree));
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(ImportantBusinessHostStatusCommonServiceToGrpc.class, e.getMessage(), e);
        }
        return response.build();
    }
}
